package ca.ulrichs.aoc.expedition.device.display.instructions

import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.expedition.device.display.Cpu

trait Instruction:
  def cycles: Int
  def execute(cpu: Cpu): Cpu

object Instruction:
  given InputParsing[Instruction] with
    def parse(input: String): Instruction = input match {
      case Add.isAdd(value) => Add(value.toLong)
      case Noop.isNoop() => Noop
      case _ => throw Exception(s"Could not parse the Instruction: ${input}")
    }
