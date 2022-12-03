package ca.ulrichs.aoc.expedition.packing

import ca.ulrichs.aoc.input.SourceInput

case class Rucksacks(packed: Seq[Rucksack]):
  lazy val priorityOfCommonItems: Seq[Int] = packed.flatMap(_.priorityOfCommonItems)

  lazy val elfBadges: Seq[Char] = packed.map(_.allItems).sliding(3, 3).map { (group) =>
    group.tail.fold(group.head) { (first, second) => first.intersect(second).distinct } 
  }.flatten.toVector

  lazy val elfBadgesPriority: Seq[Int] = elfBadges.map(PackingPriorities.value)

object Rucksacks:
  def pack(source: SourceInput): Rucksacks = Rucksacks(source.asStringList.map(Rucksack.pack))
