package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.{Coordinate, Grid}

case class Grid[+A](val width: Int, val height: Int, values: IndexedSeq[A]):
  private lazy val indexed: IndexedSeq[(A, Int)] = values.zipWithIndex
//  lazy val keys =

  def apply(x: Int, y: Int): A = apply(Coordinate(x, y))
  def apply(coordinate: Coordinate): A = values(toIndex(coordinate))

  def at(x: Int, y: Int): Option[A] = at(Coordinate(x, y))
  def at(coordinate: Coordinate): Option[A] = values.lift(toIndex(coordinate))

  def contains(coordinate: Coordinate): Boolean = coordinate.x < width && coordinate.y < height
  def containsValue[B >: A](value: B): Boolean = values.contains(value)



//  def toString(stringify: (Coordinate, A) => String, default: String = " "): String = {
//    val maximumY = keys.map(_.y).max
//
//    (0 to maximumY).map { y =>
//      keys.filter(_.y == y).sortBy(_.x).map(coord => at(coord).map(value => stringify(coord, value)).getOrElse(default)).mkString
//    }.mkString(sys.props("line.separator"))
//  }
//
//  def print(stringify: (Coordinate, A) => String, default: String = " "): Unit =
//    println(toString(stringify, default))

  def toIndex(coordinate: Coordinate): Int = (coordinate.y * width) + coordinate.x
  def toCoordinate(index: Int): Coordinate = Coordinate(index / width, index % width)

object Grid extends GridBuilder
