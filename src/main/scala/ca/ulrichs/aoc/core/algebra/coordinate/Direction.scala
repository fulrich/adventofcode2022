package ca.ulrichs.aoc.core.algebra.coordinate

sealed case class Direction(coordinateDirection: Coordinate):
  def + (direction: Direction): Direction = Direction(move(direction.coordinateDirection))

  def move(coordinate: Coordinate): Coordinate = Coordinate(
    x = coordinate.x + coordinateDirection.x,
    y = coordinate.y + coordinateDirection.y
  )

  lazy val minimal: Direction = Direction(minimalDelta(coordinateDirection.x), minimalDelta(coordinateDirection.y))
  
  private def minimalDelta(delta: Int): Int = delta match {
    case 0 => 0
    case negativeDelta if negativeDelta < 0 => -1
    case positiveDelta if positiveDelta > 0 => 1
  }

object Direction:
  def apply(deltaX: Int, deltaY: Int): Direction = Direction(Coordinate(deltaX, deltaY))

  def up(amount: Int): Direction = Direction(0, -amount)
  def down(amount: Int): Direction = Direction(0, amount)
  def right(amount: Int): Direction = Direction(amount, 0)
  def left(amount: Int): Direction = Direction(-amount, 0)

  def upLeft(amount: Int): Direction = Direction(0, -amount)
  def upRight(amount: Int): Direction = Direction(0, amount)
  def downLeft(amount: Int): Direction = Direction(amount, 0)
  def downRight(amount: Int): Direction = Direction(-amount, 0)

  lazy val Up: Direction = up(1)
  lazy val Down: Direction = down(1)
  lazy val Left: Direction = left(1)
  lazy val Right: Direction = right(1)

  val Adjacent: Seq[Direction] = Vector(Up, Down, Left, Right)
  def adjacent(amount: Int): Seq[Direction] = Vector(up(amount), down(amount), left(amount), right(amount))
