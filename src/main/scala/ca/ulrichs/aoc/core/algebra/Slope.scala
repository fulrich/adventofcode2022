package ca.ulrichs.aoc.core.algebra

case class Slope(deltaX: Int, deltaY: Int):
  def direction: Direction = (deltaX, deltaY) match {
    case (x, y) => Direction.up(1)
  }

object Slope:
  def apply(first: Coordinate, second: Coordinate): Slope =
    Slope(deltaX = second.y - first.y, deltaY = second.x - first.x)
