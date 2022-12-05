package ca.ulrichs.aoc.expedition.packing

import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.expedition.landing.AssignmentGroup

case class Rucksack(leftCompartment: Seq[Char], rightCompartment: Seq[Char]):
  lazy val allItems: Seq[Char] = leftCompartment ++ rightCompartment
  lazy val commonItems: Seq[Char] = leftCompartment.intersect(rightCompartment).distinct
  lazy val priorityOfCommonItems: Seq[Int] = commonItems.map(PackingPriorities.value)

object Rucksack:
  given InputParsing[Rucksack] with
    def parse(input: String): Rucksack = {
      val (leftCompartment, rightCompartment) = input.splitAt(input.length / 2)
      Rucksack(leftCompartment, rightCompartment)
    }
