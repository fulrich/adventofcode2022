package ca.ulrichs.aoc.expedition.packing

import ca.ulrichs.aoc.core.input.SourceInput

case class Rucksacks(packed: Seq[Rucksack]):
  lazy val priorityOfCommonItems: Seq[Int] = packed.flatMap(_.priorityOfCommonItems)
  lazy val elfBadges: Seq[Char] = packed.sliding(3, 3).flatMap(intersectionOf).toVector
  lazy val elfBadgesPriority: Seq[Int] = elfBadges.map(PackingPriorities.value)

  private def intersectionOf(rucksacks: Seq[Rucksack]): Seq[Char] = rucksacks.tail.foldLeft(rucksacks.head.allItems) {
    (first, second) => first.intersect(second.allItems).distinct
  }

object Rucksacks:
  def pack(source: SourceInput): Rucksacks = Rucksacks(source.asSeq[Rucksack])
