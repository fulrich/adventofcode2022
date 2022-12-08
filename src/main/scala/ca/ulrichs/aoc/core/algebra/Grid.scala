package ca.ulrichs.aoc.core.algebra

case class Grid[+A](private val points: Map[Coordinate, A]):
  lazy val maximumColumn = keys.map(_.x).max
  lazy val maximumRow = keys.map(_.y).max

  lazy val width = maximumColumn + 1
  lazy val height = maximumRow + 1

  def apply(x: Int, y: Int): A = apply(Coordinate(x, y))
  def apply(coordinate: Coordinate): A = points(coordinate)

  def at(x: Int, y: Int): Option[A] = at(Coordinate(x, y))
  def at(coordinate: Coordinate): Option[A] = points.get(coordinate)

  def contains(key: Coordinate): Boolean = points.contains(key)

  def updated[B >: A](key: Coordinate, value: B): Grid[B] = copy(points = points.updated(key, value))

  lazy val keys: Seq[Coordinate] = points.keys.toSeq
  lazy val values: Seq[A] = points.values.toSeq

  def map[B](f: ((Coordinate, A)) => (Coordinate, B)): Grid[B] = copy(points = points.map(f))

  def splitOnRow(row: Int, dropSplitRow: Boolean = false): (Grid[A], Grid[A]) = {
    val (top, bottom) = partition { case(key, value) => key.y < (row + 1) }
    val topDropped = if dropSplitRow then top.dropRow(top.height - 1) else top
    val bottomShifted =  bottom.mapKeys(key => key.up(row + 1))

    (topDropped, bottomShifted)
  }

  def splitOnColumn(column: Int, dropSplitColumn: Boolean = false): (Grid[A], Grid[A]) = {
    val (left, right) = partition { case(key, value) => key.x < (column + 1) }
    val leftDropped = if dropSplitColumn then left.dropColumn(left.width - 1) else left
    val rightShifted =  right.mapKeys(_.left(column + 1))

    (leftDropped, rightShifted)
  }

  def partition(p: ((Coordinate, A)) => Boolean): (Grid[A], Grid[A]) = {
    val (left, right) = points.partition(p)
    (Grid(left), Grid(right))
  }

  def mapValues[B](f: A => B): Grid[B] = Grid(points.view.mapValues(f).toMap)
  def mapKeys(f: Coordinate => Coordinate): Grid[A] = Grid(points.map { case(coordinate, value) => f(coordinate) -> value })

  def dropColumn(column: Int): Grid[A] = Grid(points.collect {
    case(key, value) if key.x != column => (if key.x > column then key.left else key) -> value
  })

  def dropRow(row: Int): Grid[A] = Grid(points.collect {
    case(key, value) if key.y != row => (if key.y > row then key.up else key) -> value
  })

  def flipHorizontal: Grid[A] = mapKeys { coordinate =>
    coordinate.copy(y = coordinate.y + (maximumRow - (coordinate.y * 2)))
  }

  def flipVertical: Grid[A] = mapKeys { coordinate =>
    coordinate.copy(x = coordinate.x + (maximumColumn - (coordinate.x * 2)))
  }

  def moveDown(movement: Int): Grid[A] = mapKeys(_.down(movement))
  def moveUp(movement: Int): Grid[A] = mapKeys(_.up(movement))
  def moveRight(movement: Int): Grid[A] = mapKeys(_.right(movement))
  def moveLeft(movement: Int): Grid[A] = mapKeys(_.left(movement))

  def transformAt[B >: A](positions: Seq[Coordinate])(f: B => B): Grid[B] =
    if positions.isEmpty then this
    else Grid(
      positions.foldLeft(Map.from[Coordinate, B](points)) { case (accumulation, key) => accumulation.get(key) match {
        case Some(value) => accumulation.updated[B](key, f(value))
        case None => accumulation
      } }
    )

  def merge[B >: A](other: Grid[B]): Grid[B] = Grid(points ++ other.points)
  def merge[B >: A](grid: Grid[B])(f: (B, B) => B): Grid[B] = Grid(
    (keys ++ grid.keys).distinct.collect { coordinate =>  (at(coordinate), grid.at(coordinate)) match {
      case (Some(value), None) => coordinate -> value
      case (None, Some(value)) => coordinate -> value
      case (Some(value1), Some(value2)) => coordinate -> f(value1, value2)
    } }
  )

  def toString(stringify: (Coordinate, A) => String, default: String = " "): String = {
    val maximumY = keys.map(_.y).max

    (0 to maximumY).map { y =>
      keys.filter(_.y == y).sortBy(_.x).map(coord => at(coord).map(value => stringify(coord, value)).getOrElse(default)).mkString
    }.mkString(sys.props("line.separator"))
  }

  def print(stringify: (Coordinate, A) => String, default: String = " "): Unit =
    println(toString(stringify, default))

object Grid:
  def apply[A](input: Seq[(Coordinate, A)]): Grid[A] = Grid(input.toMap)

  def parse[A](input: Seq[String])(parser: (Coordinate, Char) => A): Grid[A] = Grid(
    input.zipWithIndex.flatMap { (row, y) =>
      row.toSeq.zipWithIndex.map { (value, x) =>
        Coordinate(x, y) -> parser(Coordinate(x, y), value)
      }
    }
  )

  def fromCoordinates[A](input: Seq[String], default: A)(fill: Coordinate => A): Grid[A] = {
    val coordinates = input.map(Coordinate.parse)
    val width = coordinates.map(_.x).max + 1
    val height = coordinates.map(_.y).max + 1

    create(width = width, height = height) { coordinate =>
      coordinates.find(_  == coordinate).map(fill).getOrElse(default)
    }
  }

  def create[A](width: Int, height: Int, fill: A): Grid[A] =
    create(width, height)(_ => fill)

  def create[A](width: Int, height: Int)(fill: Coordinate => A): Grid[A] = Grid(
    (0 until width).flatMap { x =>
      (0 until height).map { y =>
        val coordinate = Coordinate(x, y)
        coordinate -> fill(coordinate)
      }
    }
  )
