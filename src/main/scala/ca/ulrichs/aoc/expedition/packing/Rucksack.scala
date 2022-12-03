package ca.ulrichs.aoc.expedition.packing

case class Rucksack(compartments: Seq[Seq[Char]]):
  lazy val allItems: Seq[Char] = compartments.flatten
  lazy val commonItems: Seq[Char] = compartments.head.intersect(compartments.last).distinct
  lazy val priorityOfCommonItems: Seq[Int] = commonItems.map(PackingPriorities.value)

object Rucksack:
  val DefaultCompartments = 2

  def pack(itemString: String): Rucksack = pack(itemString, DefaultCompartments)

  def pack(itemString: String, numberOfCompartments: Int): Rucksack = {
    val compartmentSize = (itemString.length / numberOfCompartments).toInt
    val compartments = itemString.sliding(compartmentSize).foldLeft(Vector.empty[Seq[Char]]) { (total, compartmentContents) =>
      total :+ compartmentContents.toVector
    }

    Rucksack(compartments)
  }
