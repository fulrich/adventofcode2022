package ca.ulrichs.aoc.expedition.packing

object PackingPriorities {
  def value(item: Char): Int = item.toInt match {
    case item if isLowerAlpha(item) => item - 96
    case item if isUpperAlpha(item) => item - 38
  }

  private def isLowerAlpha(item: Int): Boolean = item >= 97 && item <= 122
  private def isUpperAlpha(item: Int): Boolean = item >=65 && item <= 90
}
