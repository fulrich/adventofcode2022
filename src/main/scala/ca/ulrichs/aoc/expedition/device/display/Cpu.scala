package ca.ulrichs.aoc.expedition.device.display

import ca.ulrichs.aoc.expedition.device.display.instructions.Instruction

import scala.annotation.tailrec

case class Cpu(registers: Registers, history: Map[Int, Registers] = Map.empty):
  lazy val currentCycle: Int = history.keys.max + 1
  lazy val next: Cpu = copy(history = history + (currentCycle -> registers))

  def signalStrengthsOver(range: Range): Seq[Long] = range.map(signalStrength)
  def signalStrength(cycle: Int): Long = history(cycle - 1).x * cycle

  def execute(instructions: Seq[Instruction]): Cpu = execute(instructions.head, instructions.tail)

  @tailrec
  final private def execute(current: Instruction, remaining: Seq[Instruction]): Cpu = remaining match {
    case Seq() => executeInstruction(current)
    case _ => executeInstruction(current).execute(remaining.head, remaining.tail)
  }

  private def executeInstruction(instruction: Instruction): Cpu =
    (1 to instruction.cycles).foldLeft(this) { (cpu, cycle) =>
      if cycle == instruction.cycles then instruction.execute(cpu).next else cpu.next
    }

object Cpu:
  val default: Cpu = Cpu(registers = Registers.default, history = Map(0 -> Registers.default))
