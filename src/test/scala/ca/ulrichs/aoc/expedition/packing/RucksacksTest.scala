package ca.ulrichs.aoc.expedition.packing

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class RucksacksTest extends AnyFunSuite:
  val source = SourceInput(
    "vJrwpWtwJgWrhcsFMMfFFhFp",
    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
    "PmmdzqPrVvPwwTWBwg",
    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
    "ttgJtRGJQctTZtZT",
    "CrZsJsPPZsGzwwsLwLmpwMDw"
  )

  test("Can determine the sum of priorities of common items in all of the elf rucksacks") {
    val rucksacks = Rucksacks.pack(SourceInput.fromResource("day3_rucksack_contents"))

    rucksacks.priorityOfCommonItems.sum shouldBe 7446
  }

  test("Can determine the sum of priorities of common items in a small set of rucksacks") {
    val rucksacks = Rucksacks.pack(source)

    rucksacks.priorityOfCommonItems.sum shouldBe 157
  }

  test("Can find the elf badges in all of the elf rucksacks") {
    val rucksacks = Rucksacks.pack(SourceInput.fromResource("day3_rucksack_contents"))

    rucksacks.elfBadgesPriority.sum shouldBe 2646
  }

  test("Can find the elf badges in a small set of rucksacks") {
    val rucksacks = Rucksacks.pack(source)

    rucksacks.elfBadges should contain theSameElementsAs Seq('r', 'Z')
    rucksacks.elfBadgesPriority.sum shouldBe 70
  }
