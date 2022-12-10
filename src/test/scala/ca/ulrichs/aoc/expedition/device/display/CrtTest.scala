package ca.ulrichs.aoc.expedition.device.display

import ca.ulrichs.aoc.core.input.SourceInput
import ca.ulrichs.aoc.expedition.device.display.instructions.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class CrtTest extends AnyFunSuite:
  val realInstructionSet: Seq[Instruction] = SourceInput.fromResource("day10_instruction_set").asSeq[Instruction]
  val exampleInstructionSet: Seq[Instruction] =
    SourceInput.fromResource("day10_example_instruction_set").asSeq[Instruction]

  test("Display example output") {
//    Crt.blank.execute(exampleInstructionSet).print()
  }

  test("Display real output") {
//    Crt.blank.execute(realInstructionSet).print()
  }
