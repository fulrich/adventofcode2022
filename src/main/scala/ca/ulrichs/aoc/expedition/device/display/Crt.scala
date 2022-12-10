package ca.ulrichs.aoc.expedition.device.display

import ca.ulrichs.aoc.expedition.device.display.instructions.Instruction

case class Crt(cpu: Cpu = Cpu.default):
  def execute(instructions: Seq[Instruction]): Crt = copy(cpu = cpu.execute(instructions))

  def print(): Unit = (0 until Crt.Height).foreach(row => println(rowString(Crt.Width * row)))

  private def rowString(start: Int) = (start until (start + Crt.Width)).map(pixelAt(start)).mkString

  private def pixelAt(start: Int)(cycle: Int): String = if shouldBeLit(cycle, start) then lit else dark

  private def shouldBeLit(cycle: Int, start: Int): Boolean = {
    val middle = cpu.history(cycle).x
    Seq(middle - 1, middle, middle + 1).contains(cycle - start)
  }

  private val lit: String = square(Console.GREEN)
  private val dark: String = square(Console.BLACK)
  private def square(color: String): String = s"${color}\u2588${Console.RESET}"

object Crt:
  val blank: Crt = Crt()

  private val Height: Int = 6
  private val Width: Int = 40
