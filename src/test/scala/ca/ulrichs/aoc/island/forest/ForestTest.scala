package ca.ulrichs.aoc.island.forest

import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate
import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite


import scala.language.implicitConversions

class ForestTest extends AnyFunSuite:
  val exampleSource: SourceInput = SourceInput(
    "30373",
    "25512",
    "65332",
    "33549",
    "35390"
  )

  test("Can count the number of visible tress in an example input") {
    Forest(exampleSource).visibleTrees.length shouldBe 21
  }

  test("Can count the number of visible tress in the real forest") {
    val source = SourceInput.fromResource("day8_tree_map")
    Forest(source).visibleTrees.length shouldBe 1792
  }

  test("Can determine the scenic score for each point in the example forest") {
    val forest = Forest(exampleSource)
    forest.trees.keys.map(forest.scenicScore).max shouldBe 8

    forest.scenicScore(Coordinate(2, 1)) shouldBe 4
    forest.scenicScore(Coordinate(2, 3)) shouldBe 8
    forest.scenicScore(Coordinate(3, 2)) shouldBe 2
  }

  test("Can determine the scenic score for each point in the real forest") {
    val source = SourceInput.fromResource("day8_tree_map")
    val forest = Forest(source)
    forest.trees.keys.map(forest.scenicScore).max shouldBe 334880
  }

  test("Can count the number of visible trees in a given row") {
    val forest = Forest(exampleSource)

    forest.visibleTrees(Coordinate(0, 0), _.right) shouldBe Seq(Coordinate(0, 0), Coordinate(3, 0))
    forest.visibleTrees(Coordinate(4, 0), _.left) shouldBe Seq(Coordinate(4, 0), Coordinate(3, 0))

    forest.visibleTrees(Coordinate(0, 1), _.right) shouldBe Seq(Coordinate(0, 1), Coordinate(1, 1))
    forest.visibleTrees(Coordinate(4, 1), _.left) shouldBe Seq(Coordinate(4, 1), Coordinate(2, 1))

    forest.visibleTrees(Coordinate(0, 2), _.right) shouldBe Seq(Coordinate(0, 2))
    forest.visibleTrees(Coordinate(4, 2), _.left) shouldBe Seq(Coordinate(4, 2), Coordinate(3, 2), Coordinate(1, 2), Coordinate(0, 2))

    forest.visibleTrees(Coordinate(0, 3), _.right) shouldBe Seq(Coordinate(0, 3), Coordinate(2, 3), Coordinate(4, 3))
    forest.visibleTrees(Coordinate(4, 3), _.left) shouldBe Seq(Coordinate(4, 3))

    forest.visibleTrees(Coordinate(0, 4), _.right) shouldBe Seq(Coordinate(0, 4), Coordinate(1, 4), Coordinate(3, 4))
    forest.visibleTrees(Coordinate(4, 4), _.left) shouldBe Seq(Coordinate(4, 4), Coordinate(3, 4))
  }
