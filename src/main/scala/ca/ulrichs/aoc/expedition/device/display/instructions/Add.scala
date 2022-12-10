package ca.ulrichs.aoc.expedition.device.display.instructions

import ca.ulrichs.aoc.expedition.device.display.Cpu

import scala.util.matching.Regex

case class Add(value: Long) extends Instruction:
  override val cycles: Int = 2
  override def execute(cpu: Cpu): Cpu = cpu.copy(registers = cpu.registers.addX(value))

object Add:
  val isAdd: Regex = "addx ([0-9-]+)".r
