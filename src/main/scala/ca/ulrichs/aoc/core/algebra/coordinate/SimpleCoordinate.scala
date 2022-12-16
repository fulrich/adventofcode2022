package ca.ulrichs.aoc.core.algebra.coordinate

case class SimpleCoordinate(x: Int, y: Int) extends Coordinate(x, y):
  def +(coordinate: Coordinate): Coordinate = Coordinate(x = coordinate.x + x, y = coordinate.y + y)
  def -(coordinate: Coordinate): Coordinate = Coordinate(x = coordinate.x - x, y = coordinate.y - y)
  def ++(coordinate: Coordinate): Seq[Coordinate] = Vector(this, coordinate)

  def move(direction: Direction): Coordinate = direction.move(this)
  def slope(coordinate: Coordinate): Direction = Direction(deltaX = coordinate.x - x, deltaY = coordinate.y - y)

  def up(amount: Int): Coordinate = move(Direction.up(amount))
  def down(amount: Int): Coordinate = move(Direction.down(amount))
  def left(amount: Int): Coordinate = move(Direction.left(amount))
  def right(amount: Int): Coordinate = move(Direction.right(amount))

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

  override def toString: String = s"($x, $y)"