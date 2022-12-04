package ca.ulrichs.aoc.expedition.landing

case class SectionAssignment(start: Int, end: Int):
  def contains(other: SectionAssignment): Boolean = other.start >= start && other.end <= end
  def overlaps(other: SectionAssignment): Boolean = (start >= other.start && start <= other.end) || (end >= other.start && end <= other.end)

object SectionAssignment:
  def parse(input: String): SectionAssignment = input.split('-') match {
    case Array(rawStart, rawEnd) => SectionAssignment(rawStart.toInt, rawEnd.toInt)
    case _ => throw Exception(s"Could not parse Section Assignment: ${input}")
  }
