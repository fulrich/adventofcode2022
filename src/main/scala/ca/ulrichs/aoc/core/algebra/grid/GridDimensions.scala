package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate

case class GridDimensions(xStart: Int, xEnd: Int, yStart: Int, yEnd: Int):
  private val length: Int = width * height
  lazy val tabulate: Vector[Coordinate] = Vector.tabulate(length)(toCoordinate)

  lazy val height: Int = (yEnd - yStart) + 1
  lazy val width: Int = (xEnd - xStart) + 1

  def isCoordinateValid(coordinate: Coordinate): Boolean =
    (coordinate.x >= xStart && coordinate.x <= xEnd) && (coordinate.y >= yStart && coordinate.y <= yEnd)

  def toIndex(x: Int, y: Int): Int = toIndex(Coordinate(x, y))
  def toIndex(coordinate: Coordinate): Int = ((coordinate.y - yStart) * width) + (coordinate.x - xStart)

  def isIndexValid(index: Int): Boolean = index >= 0 && index <= length

  def toCoordinate(index: Int): Coordinate =
    if isIndexValid(index) then Coordinate( (index % width) + xStart, (index / width) + yStart)
    else throw IndexOutOfBoundsException(s"The index $index cannot be converted to a Coordinate on the Grid.")

  def toCoordinateOption(index: Int): Option[Coordinate] =
    if isIndexValid(index) then Some(toCoordinate(index))
    else None

object GridDimensions:
  def fromStrings(input: Seq[String]): GridDimensions =
    fromOrigin(input.filter(_.nonEmpty).map(_.length).max, input.length)

  def fromOrigin(width: Int, height: Int): GridDimensions =
    fromCoordinate(Coordinate.origin, width, height)

  def fromCoordinate(coordinate: Coordinate, width: Int, height: Int): GridDimensions =
    fromCoordinates(coordinate, coordinate.right(width - 1).down(height - 1))

  def fromCoordinates(first: Coordinate, second: Coordinate): GridDimensions =
    fromCoordinates(first ++ second)

  def fromCoordinates(coordinates: Seq[Coordinate]): GridDimensions =
    GridDimensions(xStart = coordinates.minX, xEnd = coordinates.maxX, yStart = coordinates.minY, yEnd = coordinates.maxY)
