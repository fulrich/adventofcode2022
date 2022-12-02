package ca.ulrichs.aoc.expedition.rochambeau

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.input.SourceInput

class RochambeauTest extends AnyFunSuite:
  val source = SourceInput(
    "A Y",
    "B X",
    "C Z"
  )

  test("Can play all the elves following the simple strategy") {
    val puzzleSource = SourceInput.fromResource("day2_rock_paper_scissors")
    Rochambeau.simple(puzzleSource).scoreOfAll shouldBe 10310
  }

  test("Can play all the elves following the targeted strategy") {
    val puzzleSource = SourceInput.fromResource("day2_rock_paper_scissors")
    Rochambeau.targetedResult(puzzleSource).scoreOfAll shouldBe 14859
  }

  test("Can play test games following the simple strategy") {
    Rochambeau.simple(source).scoreOfAll shouldBe 15
  }

  test("Can play test games following the targeted strategy") {
    Rochambeau.targetedResult(source).scoreOfAll shouldBe 12
  }
