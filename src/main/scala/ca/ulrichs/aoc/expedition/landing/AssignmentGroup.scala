package ca.ulrichs.aoc.expedition.landing

import ca.ulrichs.aoc.core.algebra.RangeHelpers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

case class AssignmentGroup(firstGroup: Range, secondGroup: Range):
  lazy val containedByOtherGroup: Boolean = firstGroup.includes(secondGroup) || secondGroup.includes(firstGroup)
  lazy val overlapsOtherGroup: Boolean = firstGroup.overlaps(secondGroup) || secondGroup.overlaps(firstGroup)

object AssignmentGroup:
  def parse(input: Seq[Range]): AssignmentGroup = input match {
    case Seq(rawFirstGroup, rawSecondGroup) => AssignmentGroup(rawFirstGroup, rawSecondGroup)
    case _ => throw Exception(s"Could not parse Section Assignment: ${input}")
  }
