package ca.ulrichs.aoc.expedition.device.display

import ca.ulrichs.aoc.core.input.SourceInput
import ca.ulrichs.aoc.expedition.device.display.instructions.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class CpuTest extends AnyFunSuite:
  val realInstructionSet: Seq[Instruction] = SourceInput.fromResource("day10_instruction_set").asSeq[Instruction]
  val exampleInstructionSet: Seq[Instruction] =
    SourceInput.fromResource("day10_example_instruction_set").asSeq[Instruction]

  test("Can run instructions on the CPU") {
    val result = Cpu.default.execute(Seq(Add(10)))

    result.currentCycle shouldBe 3

    result.history(1) shouldBe Registers(1)
    result.history(2) shouldBe Registers(11)
  }

  test("Can run a simple program") {
    val result = Cpu.default.execute(Seq(
      Noop,
      Add(3),
      Add(-5)
    ))

    result.history(1) shouldBe Registers(1)
    result.history(2) shouldBe Registers(1)
    result.history(3) shouldBe Registers(4)
    result.history(4) shouldBe Registers(4)
    result.history(5) shouldBe Registers(-1)
  }

  test("Can run an example instruction set") {
    Map(-1 -> 10).keys.max shouldBe -1
    val result = Cpu.default.execute(exampleInstructionSet)

    result.signalStrength(20) shouldBe 420
    result.signalStrength(60) shouldBe 1140
    result.signalStrength(100) shouldBe 1800
    result.signalStrength(140) shouldBe 2940
    result.signalStrength(180) shouldBe 2880
    result.signalStrength(220) shouldBe 3960

    result.signalStrengthsOver(20 to 220 by 40).sum shouldBe 13140
  }

  test("Can run a real instruction set") {
    val result = Cpu.default.execute(realInstructionSet)

    result.signalStrengthsOver(20 to 220 by 40).sum shouldBe 14860
  }
