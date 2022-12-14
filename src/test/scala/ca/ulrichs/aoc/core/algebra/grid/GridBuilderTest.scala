package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class GridBuilderTest extends AnyFunSuite with LoneElement:
  test("Can create a grid of a given size") {
    val grid = Grid.create(10, 10, fill = 5)

    grid.height shouldBe 10
    grid.width shouldBe 10
//    grid.keys should have length 100
    grid.values.distinct.loneElement shouldBe 5
  }

  test("Testing coordinate to index") {
    val grid = Grid.create(10, 10, fill = 5)

    grid.toCoordinate(11) shouldBe Coordinate(1, 1)
    grid.toIndex(Coordinate(1, 1)) shouldBe 11
  }
