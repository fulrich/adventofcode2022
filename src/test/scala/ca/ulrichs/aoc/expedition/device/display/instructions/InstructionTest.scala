package ca.ulrichs.aoc.expedition.device.display.instructions

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class InstructionTest extends AnyFunSuite:
  test("Can parse Noop instructions") {
    "addx -20".as[Instruction] shouldBe Add(-20)
    "addx -5".as[Instruction] shouldBe Add(-5)
    "addx 18".as[Instruction] shouldBe Add(18)
    "addx 3".as[Instruction] shouldBe Add(3)
  }

  test("Can parse add instructions") {
    "noop".as[Instruction] shouldBe Noop
  }
