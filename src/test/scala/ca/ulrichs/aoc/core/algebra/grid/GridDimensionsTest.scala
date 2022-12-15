package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class GridDimensionsTest extends AnyFunSuite with LoneElement:
  test("Correctly defines width and height for various grid types") {
    val squareGridFromOrigin = GridDimensions.fromOrigin(width = 10, height = 10)
    val nonSquareGridFromOrigin = GridDimensions.fromOrigin(width = 8, height = 12)

    squareGridFromOrigin.width shouldBe 10
    squareGridFromOrigin.height shouldBe 10
    nonSquareGridFromOrigin.xStart shouldBe 0
    nonSquareGridFromOrigin.yStart shouldBe 0

    nonSquareGridFromOrigin.width shouldBe 8
    nonSquareGridFromOrigin.height shouldBe 12
    nonSquareGridFromOrigin.xStart shouldBe 0
    nonSquareGridFromOrigin.yStart shouldBe 0
  }

  test("Can determine the value index from a coordinate on a Grid starting from origin") {
    val dimensions = GridDimensions.fromOrigin(3, 3)

    dimensions.toIndex(0, 0) shouldBe 0
    dimensions.toIndex(1, 0) shouldBe 1
    dimensions.toIndex(2, 0) shouldBe 2

    dimensions.toIndex(0, 1) shouldBe 3
    dimensions.toIndex(1, 1) shouldBe 4
    dimensions.toIndex(2, 1) shouldBe 5

    dimensions.toIndex(0, 2) shouldBe 6
    dimensions.toIndex(1, 2) shouldBe 7
    dimensions.toIndex(2, 2) shouldBe 8
  }

  test("Can determine the value index from a coordinate on a Grid not starting from origin") {
    val dimensions = GridDimensions.fromCoordinate(Coordinate(3, 3), 3, 3)

    dimensions.toIndex(3, 3) shouldBe 0
    dimensions.toIndex(4, 3) shouldBe 1
    dimensions.toIndex(5, 3) shouldBe 2

    dimensions.toIndex(3, 4) shouldBe 3
    dimensions.toIndex(4, 4) shouldBe 4
    dimensions.toIndex(5, 4) shouldBe 5

    dimensions.toIndex(3, 5) shouldBe 6
    dimensions.toIndex(4, 5) shouldBe 7
    dimensions.toIndex(5, 5) shouldBe 8
  }

  test("Can determine the coordinate from a value index on a Grid starting from origin") {
    val dimensions = GridDimensions.fromOrigin(3, 3)

    dimensions.toCoordinate(0) shouldBe Coordinate(0, 0)
    dimensions.toCoordinate(1) shouldBe Coordinate(1, 0)
    dimensions.toCoordinate(2) shouldBe Coordinate(2, 0)

    dimensions.toCoordinate(3) shouldBe Coordinate(0, 1)
    dimensions.toCoordinate(4) shouldBe Coordinate(1, 1)
    dimensions.toCoordinate(5) shouldBe Coordinate(2, 1)

    dimensions.toCoordinate(6) shouldBe Coordinate(0, 2)
    dimensions.toCoordinate(7) shouldBe Coordinate(1, 2)
    dimensions.toCoordinate(8) shouldBe Coordinate(2, 2)
  }

  test("Can determine the coordinate from a value index on a Grid not starting from origin") {
    val dimensions = GridDimensions.fromCoordinate(Coordinate(3, 3), 3, 3)

    dimensions.toCoordinate(0) shouldBe Coordinate(3, 3)
    dimensions.toCoordinate(1) shouldBe Coordinate(4, 3)
    dimensions.toCoordinate(2) shouldBe Coordinate(5, 3)

    dimensions.toCoordinate(3) shouldBe Coordinate(3, 4)
    dimensions.toCoordinate(4) shouldBe Coordinate(4, 4)
    dimensions.toCoordinate(5) shouldBe Coordinate(5, 4)

    dimensions.toCoordinate(6) shouldBe Coordinate(3, 5)
    dimensions.toCoordinate(7) shouldBe Coordinate(4, 5)
    dimensions.toCoordinate(8) shouldBe Coordinate(5, 5)
  }

  test("Can create the dimensions of a grid from a sequence of strings") {
    val strings = Seq(
      "123",
      "456",
      "789"
    )
    val dimensions = GridDimensions.fromStrings(strings)

    dimensions.width shouldBe 3
    dimensions.height shouldBe 3
    dimensions.xStart shouldBe 0
    dimensions.xEnd shouldBe 2
    dimensions.yStart shouldBe 0
    dimensions.yEnd shouldBe 2
  }

  test("Can create the dimensions of a grid from an unbalanced sequence of strings") {
    val strings = Seq(
      "123",
      "456",
      "7891011"
    )
    val dimensions = GridDimensions.fromStrings(strings)

    dimensions.width shouldBe 7
    dimensions.height shouldBe 3
    dimensions.xStart shouldBe 0
    dimensions.xEnd shouldBe 6
    dimensions.yStart shouldBe 0
    dimensions.yEnd shouldBe 2
  }


  test("Can create the dimensions of a grid from any two coordinates in any order") {
    val coordinate1 = Coordinate(1, 2)
    val coordinate2 = Coordinate(4, 5)

    val dimensions = Vector(
      GridDimensions.fromCoordinates(coordinate1, coordinate2),
      GridDimensions.fromCoordinates(coordinate2, coordinate1)
    )

    dimensions.map(_.width).distinct.loneElement shouldBe 4
    dimensions.map(_.width).distinct.loneElement shouldBe 4
    dimensions.map(_.xStart).distinct.loneElement shouldBe 1
    dimensions.map(_.xEnd).distinct.loneElement shouldBe 4
    dimensions.map(_.yStart).distinct.loneElement shouldBe 2
    dimensions.map(_.yEnd).distinct.loneElement shouldBe 5
  }
