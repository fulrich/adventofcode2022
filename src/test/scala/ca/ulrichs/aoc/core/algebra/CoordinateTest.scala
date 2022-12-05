package ca.ulrichs.aoc.core.algebra

import org.scalatest._
import matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class CoordinateTest extends AnyFunSuite:
  test("Can use a String to create a Coordinate"){
    Coordinate.parse("1,2") shouldBe Coordinate(1, 2)
    Coordinate.parse("1, 2") shouldBe Coordinate(1, 2)
    Coordinate.parse("  1, 2 ") shouldBe Coordinate(1, 2)
  }

  test("Can use a Seq of Int to create a Coordinate") {
    Coordinate.parse(Seq(1, 2)) shouldBe Coordinate(1, 2)
    Coordinate.parse(Seq(55, 908)) shouldBe Coordinate(55, 908)
  }

  test("Can determine Coordinates adjacent to the Coordinate") {
    val coordinate = Coordinate(1, 1)

    coordinate.up shouldBe Coordinate(1, 0)
    coordinate.down shouldBe Coordinate(1, 2)
    coordinate.left shouldBe Coordinate(0, 1)
    coordinate.right shouldBe Coordinate(2, 1)

    coordinate.adjacent should contain theSameElementsAs Seq(
      Coordinate(1, 0),
      Coordinate(1, 2),
      Coordinate(0, 1),
      Coordinate(2, 1)
    )
  }

  test("Can determine Coordinates diagonal to the Coordinate") {
    val coordinate = Coordinate(1, 1)

    coordinate.upLeft shouldBe Coordinate(0, 0)
    coordinate.upRight shouldBe Coordinate(2, 0)
    coordinate.downLeft shouldBe Coordinate(0, 2)
    coordinate.downRight shouldBe Coordinate(2, 2)

    coordinate.diagonal should contain theSameElementsAs Seq(
      Coordinate(0, 0),
      Coordinate(0, 2),
      Coordinate(2, 0),
      Coordinate(2, 2)
    )
  }

  test("Can determine the 8 Coordinates surrounding the Coordinate") {
    Coordinate(1, 1).surrounding should contain theSameElementsAs Seq(
      Coordinate(1, 0),
      Coordinate(1, 2),
      Coordinate(0, 1),
      Coordinate(2, 1),
      Coordinate(0, 0),
      Coordinate(0, 2),
      Coordinate(2, 0),
      Coordinate(2, 2)
    )
  }
