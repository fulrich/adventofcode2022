package ca.ulrichs.aoc.island.waterfall

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class CaveSystemTest extends AnyFunSuite:
  val exampleSource = SourceInput(
    "498,4 -> 498,6 -> 496,6",
    "503,4 -> 502,4 -> 502,9 -> 494,9"
  )
//  test("Can parse the ranges of rocks into a Cave System") {
//    CaveSystem.parseWithFloor(exampleSource).afterSandfall.grid.print((c, v) => v.toString)
//    CaveSystem.parseWithFloor(exampleSource).afterSandfall.sandUnits.length shouldBe 93
//  }

  test("Can find when the cave system fills up for a real cave system with a floor") {
    val source = SourceInput.fromResource("day14_cave_system")
    val afterSandfall = CaveSystem.parseWithFloor(source).afterSandfall

    afterSandfall.grid.print((c, v) => v.toString)
    afterSandfall.sandUnits.length shouldBe 28145
  }

//  test("Do some sandfall") {
//    CaveSystem.parse(exampleSource).afterSandfall.grid.print((c, v) => v.toString)
//    CaveSystem.parse(exampleSource).afterSandfall.sandUnits.length shouldBe 24
//  }
//
//  test("Do some sandfall for real") {
//    val source = SourceInput.fromResource("day14_cave_system")
//    CaveSystem.parse(source).afterSandfall.sandUnits.length shouldBe 24
//  }
