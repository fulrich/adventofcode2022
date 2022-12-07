package ca.ulrichs.aoc.expedition.ship

import ca.ulrichs.aoc.core.input.InputParsing

import scala.util.matching.Regex

case class CraneInstruction(move: Int, from: Int, to: Int):
  lazy val isLastMove: Boolean = move == 1
  lazy val nextMove: CraneInstruction = copy(move = move - 1)

object CraneInstruction:
  val craneInstruction: Regex = raw"move (\d+) from (\d+) to (\d+)".r

  given InputParsing[CraneInstruction] with
    def parse(input: String): CraneInstruction = input match {
      case craneInstruction(move, from, to) => CraneInstruction(move.toInt, from.toInt, to.toInt)
      case _ => throw IllegalArgumentException(s"Could not parse a Crane Instruction from ${input}")
    }
