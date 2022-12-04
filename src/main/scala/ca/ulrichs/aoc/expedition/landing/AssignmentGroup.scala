package ca.ulrichs.aoc.expedition.landing

case class AssignmentGroup(firstGroup: SectionAssignment, secondGroup: SectionAssignment):
  lazy val containedByOtherGroup: Boolean = firstGroup.contains(secondGroup) || secondGroup.contains(firstGroup)
  lazy val overlapsOtherGroup: Boolean = firstGroup.overlaps(secondGroup) || secondGroup.overlaps(firstGroup)

object AssignmentGroup:
  def parse(input: String): AssignmentGroup = input.split(',') match {
    case Array(rawFirstGroup, rawSecondGroup) => parse(rawFirstGroup, rawSecondGroup)
    case _ => throw Exception(s"Could not parse Section Assignment: ${input}")
  }

  def parse(firstGroup: String, secondGroup: String): AssignmentGroup = AssignmentGroup(
    SectionAssignment.parse(firstGroup),
    SectionAssignment.parse(secondGroup)
  )
