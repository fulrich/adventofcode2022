package ca.ulrichs.aoc.expedition.landing

import ca.ulrichs.aoc.core.algebra.RangeHelpers.*
import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.core.*

case class AssignmentGroup(firstGroup: Range, secondGroup: Range):
  lazy val containedByOtherGroup: Boolean = firstGroup.includes(secondGroup) || secondGroup.includes(firstGroup)
  lazy val overlapsOtherGroup: Boolean = firstGroup.overlaps(secondGroup) || secondGroup.overlaps(firstGroup)

object AssignmentGroup:
  given InputParsing[AssignmentGroup] with
    def parse(input: String): AssignmentGroup = input.asSeq[Range] match {
      case Seq(firstGroup, secondGroup) => AssignmentGroup(firstGroup, secondGroup)
      case _ => throw Exception(s"Could not parse Section Assignment: ${input}")
    }
