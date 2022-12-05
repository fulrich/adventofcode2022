package ca.ulrichs.aoc.expedition.ship

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.core.input.StringParsing.*

class CraneInstructionTest extends AnyFunSuite:
  test("Can parse a string to a Crane Instruction") {
    "move 3 from 1 to 3".as[CraneInstruction] shouldBe CraneInstruction(move = 3, from = 1, to = 3)
    "move 12 from 1 to 9".as[CraneInstruction] shouldBe CraneInstruction(move = 12, from = 1, to = 9)
  }
