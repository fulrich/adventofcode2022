package ca.ulrichs.aoc.expedition.device.display.instructions
import ca.ulrichs.aoc.expedition.device.display.Cpu

import scala.util.matching.Regex

case object Noop extends Instruction:
  val isNoop: Regex = "noop".r

  override val cycles: Int = 1
  override def execute(cpu: Cpu): Cpu = cpu
