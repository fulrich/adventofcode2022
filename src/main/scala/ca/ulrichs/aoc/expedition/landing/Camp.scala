package ca.ulrichs.aoc.expedition.landing

import _root_.ca.ulrichs.aoc.input.SourceInput

case class Camp(assignments: Seq[AssignmentGroup]):
  lazy val countFullWorkOverlap: Int = assignments.count(_.containedByOtherGroup)
  lazy val countAnyWorkOverlap: Int = assignments.count(_.overlapsOtherGroup)

object Camp:
  def parse(input: SourceInput): Camp =
    Camp(input.asStringList.map(AssignmentGroup.parse))
