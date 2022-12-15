package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class GridBuilderTest extends AnyFunSuite with LoneElement:
  test("Can create a simple grid from a sequence of strings") {
    val strings = Seq(
      "123",
      "456",
      "789"
    )

    val grid = Grid.simpleFromStrings(strings)

    grid.dimensions.height shouldBe 3
    grid.dimensions.width shouldBe 3
    grid.keys should have length 9

    grid(0, 0) shouldBe '1'
    grid(1, 1) shouldBe '5'
    grid(2, 2) shouldBe '9'
  }

  test("Can create a custom grid from a sequence of strings") {
    val strings = Seq(
      "123",
      "456",
      "789"
    )

    val grid = Grid.fromStrings(strings, default = 0) { (coordinate, character) =>
      character.asDigit
    }

    grid.dimensions.height shouldBe 3
    grid.dimensions.width shouldBe 3
    grid.keys should have length 9

    grid(0, 0) shouldBe 1
    grid(1, 1) shouldBe 5
    grid(2, 2) shouldBe 9
  }

  test("Can create a custom grid from an unbalanced sequence of strings") {
    val strings = Seq(
      "12345",
      "456",
      "789"
    )

    val grid = Grid.fromStrings(strings, default = 0) { (coordinate, character) =>
      character.asDigit
    }

    grid.dimensions.height shouldBe 3
    grid.dimensions.width shouldBe 5
    grid.keys should have length 15

    grid(0, 0) shouldBe 1
    grid(3, 0) shouldBe 4
    grid(4, 0) shouldBe 5

    grid(1, 1) shouldBe 5
    grid(3, 1) shouldBe 0
    grid(4, 1) shouldBe 0

    grid(2, 2) shouldBe 9
    grid(3, 2) shouldBe 0
    grid(4, 2) shouldBe 0
  }

  test("Can create a grid from width and height with a static fill value") {
    val grid = Grid.fromDimensions(10, 10, fill = 5)

    grid.dimensions.height shouldBe 10
    grid.dimensions.width shouldBe 10
    grid.keys should have length 100
    grid.values.distinct.loneElement shouldBe 5
  }

  test("Can create a grid from width and height with a dynamic fill value") {
    val grid = Grid.fromDimensions(10, 10)(coordinate => coordinate.x + coordinate.y)

    grid.dimensions.height shouldBe 10
    grid.dimensions.width shouldBe 10
    grid.keys should have length 100
    grid.values.distinct should contain theSameElementsAs (0 to 18).toSeq

    grid.at(5, 5) should contain(10)
    grid.at(9, 9) should contain(18)
  }

  test("Can create a grid from a sequence of coordinates with a static fill value") {
    val grid = Grid.fromCoordinates(Coordinate(0, 0) ++ Coordinate(5, 5), 25)

    grid.dimensions.height shouldBe 6
    grid.dimensions.width shouldBe 6
    grid.keys should have length 36
    grid.values.distinct.loneElement shouldBe 25
  }

  test("Can create a grid from a sequence of coordinates with a dynamic fill value") {
    val grid = Grid.fromCoordinates(Coordinate(0, 0) ++ Coordinate(5, 5))(coordinate => coordinate.x + coordinate.y)

    grid.dimensions.height shouldBe 6
    grid.dimensions.width shouldBe 6
    grid.keys should have length 36
    grid.values.distinct should contain theSameElementsAs (0 to 10).toSeq

    grid.at(3, 3) should contain(6)
    grid.at(5, 5) should contain(10)
  }

  test("Can create a grid from grid dimensions with a dynamic fill value") {
    val dimensions = GridDimensions.fromOrigin(6, 6)
    val grid = Grid.fromDimensions(dimensions)(coordinate => coordinate.x + coordinate.y)

    grid.dimensions.height shouldBe 6
    grid.dimensions.width shouldBe 6
    grid.keys should have length 36
    grid.values.distinct should contain theSameElementsAs (0 to 10).toSeq

    grid.at(3, 3) should contain(6)
    grid.at(5, 5) should contain(10)
  }
