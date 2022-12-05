package ca.ulrichs.aoc.core.algebra

case class Coordinate(x: Int, y: Int):
  def up(amount: Int): Coordinate = copy(y = y - amount)
  def down(amount: Int): Coordinate = copy(y = y + amount)
  def left(amount: Int): Coordinate = copy(x = x - amount)
  def right(amount: Int): Coordinate = copy(x = x + amount)

  lazy val up: Coordinate = up(1)
  lazy val down: Coordinate = down(1)
  lazy val left: Coordinate = left(1)
  lazy val right: Coordinate = right(1)

  lazy val upLeft: Coordinate = up.left
  lazy val upRight: Coordinate = up.right
  lazy val downLeft: Coordinate = down.left
  lazy val downRight: Coordinate = down.right

  lazy val adjacent: Seq[Coordinate] = Seq(up, down, left, right)
  lazy val diagonal: Seq[Coordinate] = Seq(upLeft, upRight, downLeft, downRight)

  lazy val surrounding: Seq[Coordinate]  = adjacent ++ diagonal

object Coordinate:
  val origin: Coordinate = Coordinate(0, 0)

  def parse(input: Seq[Int]): Coordinate = input match {
    case Seq(x, y) => Coordinate(x, y)
    case _ => throw IllegalArgumentException(s"Cannot build a Coordinate from $input")
  }

  def parse(input: String): Coordinate = input.split(",") match {
    case Array(x: String, y: String) => Coordinate(x.trim.toInt, y.trim.toInt)
    case _ => throw IllegalArgumentException(s"Cannot build a Coordinate from $input")
  }
