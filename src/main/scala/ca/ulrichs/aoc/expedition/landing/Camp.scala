package ca.ulrichs.aoc.expedition.landing

import ca.ulrichs.aoc.core.input.SourceInput

case class Camp(assignments: Seq[AssignmentGroup]):
  lazy val countFullWorkOverlap: Int = assignments.count(_.containedByOtherGroup)
  lazy val countAnyWorkOverlap: Int = assignments.count(_.overlapsOtherGroup)

object Camp:
  def parse(input: SourceInput): Camp = Camp(input.asSeq[String].map(AssignmentGroup.parse))
