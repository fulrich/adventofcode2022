package ca.ulrichs.aoc.expedition.ship

import ca.ulrichs.aoc.core.*

case class Ship(containers: Map[Int, Seq[Char]], instructionRules: CraneInstructionSet = NineThousand) {
  lazy val topCrates: Seq[Char] = containers.keys.toSeq.sorted.foldLeft(Seq.empty) { (tops, key) =>
    tops :+ containers(key).last
  }

  def using(rules: CraneInstructionSet): Ship = copy(instructionRules = rules)

  def move(instruction: String): Ship = move(instruction.as[CraneInstruction])
  def move(instruction: CraneInstruction): Ship = instructionRules.move(this, instruction)

  def move(instructions: Seq[CraneInstruction]): Ship = instructions.foldLeft(this) {  (ship, instruction) =>
    ship.move(instruction)
  }
}
