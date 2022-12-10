package ca.ulrichs.aoc.island.bridge

import ca.ulrichs.aoc.core.algebra.{Coordinate, Direction}
import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

import scala.language.implicitConversions

class RopeTest extends AnyFunSuite:
  val exampleInput = SourceInput(
    "R 4",
    "U 4",
    "L 3",
    "D 1",
    "R 4",
    "D 1",
    "L 5",
    "R 2"
  )

  test("Can run the rope through the real input") {
    val realMoves: Seq[Direction] = SourceInput
      .fromResource("day9_knot_movement").asSeq[String]
      .flatMap(Rope.parseDirections)

    val result = realMoves.foldLeft(Rope(knots = 2)) { (rope, direction) =>
      rope.move(direction)
    }

    result.tailTouched.length shouldBe 6175
  }

  test("Can run the rope through the real input with 10 knots") {
    val realMoves: Seq[Direction] = SourceInput
      .fromResource("day9_knot_movement").asSeq[String]
      .flatMap(Rope.parseDirections)

    val result = realMoves.foldLeft(Rope(knots = 10)) { (rope, direction) =>
      rope.move(direction)
    }

    result.tailTouched.length shouldBe 2578
  }

  test("Can run the rope through the example input") {
    val exampleMoves: Seq[Direction] = exampleInput.asSeq[String].flatMap(Rope.parseDirections)
    val result = exampleMoves.foldLeft(Rope(knots = 2)) { (rope, direction) =>
      rope.move(direction)
    }

    result.tailTouched.length shouldBe 13
  }

  test("Can run the rope through the example input with 10 knots") {
    val exampleMoves: Seq[Direction] = exampleInput.asSeq[String].flatMap(Rope.parseDirections)
    val result = exampleMoves.foldLeft(Rope(knots = 10)) { (rope, direction) =>
      rope.move(direction)
    }

    result.tailTouched.length shouldBe 1
  }

  test("Can run the rope through a larger example input with 10 knots") {
    val source = SourceInput(
      "R 5",
      "U 8",
      "L 8",
      "D 3",
      "R 17",
      "D 10",
      "L 25",
      "U 20"
    )
    val exampleMoves: Seq[Direction] = source.asSeq[String].flatMap(Rope.parseDirections)
    val result = exampleMoves.foldLeft(Rope(knots = 10)) { (rope, direction) =>
      rope.move(direction)
    }

    result.tailTouched.length shouldBe 36
  }

  test("Can try to move the rope right 4") {
    val result = Rope.at(Coordinate(0, 0)).move(Direction.Right).move(Direction.Right).move(Direction.Right).move(Direction.Right)
    result.tailTouched.length shouldBe 4
  }

  test("Can parse directions") {
    Rope.parseDirections("R 2") shouldBe Seq(Direction.Right, Direction.Right)
    Rope.parseDirections("D 2") shouldBe Seq(Direction.Down, Direction.Down)
    Rope.parseDirections("U 3") shouldBe Seq(Direction.Up, Direction.Up, Direction.Up)
    Rope.parseDirections("L 4") shouldBe Seq(Direction.Left, Direction.Left, Direction.Left, Direction.Left)
  }

