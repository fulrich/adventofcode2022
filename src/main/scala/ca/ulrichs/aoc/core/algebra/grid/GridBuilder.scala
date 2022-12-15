package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate

private trait GridBuilder:
  def simpleFromStrings(input: Seq[String], default: Char = ' '): Grid[Char] =
    fromStrings(input, default)(GridBuilder.simpleFill)

  def fromStrings[A](input: Seq[String], default: A)(parser: (Coordinate, Char) => A): Grid[A] = {
    val dimensions = GridDimensions.fromStrings(input)
    val values = dimensions.tabulate.map { coordinate =>
      input.apply(coordinate.y).lift(coordinate.x).map(parser(coordinate, _)).getOrElse(default)
    }

    Grid(dimensions, values)
  }

  def fromCoordinates[A](coordinates: Seq[Coordinate], fill: A): Grid[A] =
    fromCoordinates(coordinates)(GridBuilder.staticFill(fill))

  def fromCoordinates[A](coordinates: Seq[Coordinate])(fill: Coordinate => A): Grid[A] =
    fromDimensions(GridDimensions.fromCoordinates(coordinates))(fill)

  def fromDimensions[A](width: Int, height: Int, fill: A): Grid[A] =
    fromDimensions(width, height)(GridBuilder.staticFill(fill))

  def fromDimensions[A](width: Int, height: Int)(fill: Coordinate => A): Grid[A] =
    fromDimensions(GridDimensions.fromOrigin(width, height))(fill)

  def fromDimensions[A](dimensions: GridDimensions)(fill: Coordinate => A): Grid[A] =
    Grid(dimensions = dimensions, values = dimensions.tabulate.map(fill))

object GridBuilder:
  private def staticFill[A](value: A): Coordinate => A = _ => value
  private def simpleFill: (Coordinate, Char) => Char = (_, value) => value
