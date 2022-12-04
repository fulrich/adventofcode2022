package ca.ulrichs.aoc.core.algebra

object RangeHelpers:
  extension(range: Range)
    def includes(other: Range): Boolean = other.start >= range.start && other.end <= range.end
    def overlaps(other: Range): Boolean =
      (range.start >= other.start && range.start <= other.end) ||
      (range.end >= other.start && range.end <= other.end)
