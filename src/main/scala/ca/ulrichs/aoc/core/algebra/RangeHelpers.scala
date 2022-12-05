package ca.ulrichs.aoc.core.algebra

object RangeHelpers:
  extension(range: Range)
    def includes(other: Range): Boolean = other.start >= range.start && other.end <= range.end
    def overlaps(other: Range): Boolean =
      (range.start >= other.start && range.start <= other.end) ||
      (range.end >= other.start && range.end <= other.end)

  def parse(input: String): Range = input.split('-') match {
    case Array(rawStart, rawEnd) => (rawStart.toInt to rawEnd.toInt)
    case _ => throw Exception(s"Could not parse Range from: ${input}")
  }
