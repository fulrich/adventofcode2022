package ca.ulrichs.aoc.core.algebra

sealed trait CoordinateDirection:
  def move(coordinate: Coordinate): Coordinate

case class Up(amount: Int = 1) extends CoordinateDirection:
  def move(coordinate: Coordinate): Coordinate = coordinate.copy(y = coordinate.y - amount)
case class Down(amount: Int = 1) extends CoordinateDirection:
  def move(coordinate: Coordinate): Coordinate = coordinate.copy(y = coordinate.y + amount)
case class Left(amount: Int = 1) extends CoordinateDirection:
  def move(coordinate: Coordinate): Coordinate = coordinate.copy(x = coordinate.x - amount)
case class Right(amount: Int = 1) extends CoordinateDirection:
  def move(coordinate: Coordinate): Coordinate = coordinate.copy(x = coordinate.x + amount)

object CoordinateDirection:
  val Adjacent: Seq[CoordinateDirection] = adjacent(amount = 1)
  def adjacent(amount: Int): Seq[CoordinateDirection] = Seq(Up(amount), Down(amount), Left(amount), Right(amount))
