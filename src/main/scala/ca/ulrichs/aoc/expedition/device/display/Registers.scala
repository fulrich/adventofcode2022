package ca.ulrichs.aoc.expedition.device.display

case class Registers(x: Long):
  def addX(value: Long): Registers = copy(x = x + value)

object Registers:
  val default: Registers = Registers(x = 1)
