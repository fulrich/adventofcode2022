package ca.ulrichs.aoc.expedition.ship

import scala.annotation.tailrec

sealed abstract class CraneInstructionSet(val version: String):
  def move(ship: Ship, instruction: CraneInstruction): Ship

case object NineThousand extends CraneInstructionSet("9000"):
  @tailrec
  override final def move(ship: Ship, instruction: CraneInstruction): Ship = {
    val fromContainer = ship.containers(instruction.from)
    val toContainer = ship.containers(instruction.to)

    val updatedShip = ship.copy(containers =
      ship.containers
        .updated(instruction.from, fromContainer.init)
        .updated(instruction.to, toContainer :+ fromContainer.last)
    )
    if instruction.isLastMove then updatedShip else move(updatedShip, instruction.nextMove)
  }

case object NineThousandAndOne extends CraneInstructionSet("9001"):
  override def move(ship: Ship, instruction: CraneInstruction): Ship = {
    val fromContainer = ship.containers(instruction.from)
    val toContainer = ship.containers(instruction.to)

    ship.copy(containers =
      ship.containers
        .updated(instruction.from, fromContainer.dropRight(instruction.move))
        .updated(instruction.to, toContainer ++ fromContainer.takeRight(instruction.move))
    )
  }
