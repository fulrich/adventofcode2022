package ca.ulrichs.aoc.expedition.packing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.LoneElement

class RucksackTest extends AnyFunSuite with LoneElement:
  test("Can pack a Rucksack from an item list") {
    val rucksack = Rucksack.pack("vJrwpWtwJgWrhcsFMMfFFhFp")

    rucksack.compartments.head.mkString shouldBe "vJrwpWtwJgWr"
    rucksack.compartments.last.mkString shouldBe "hcsFMMfFFhFp"
  }

  test("Can find the common items between the compartments in a Rucksack") {
    Rucksack.pack("vJrwpWtwJgWrhcsFMMfFFhFp").commonItems.loneElement shouldBe 'p'
    Rucksack.pack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL").commonItems.loneElement shouldBe 'L'
    Rucksack.pack("PmmdzqPrVvPwwTWBwg").commonItems.loneElement shouldBe 'P'
    Rucksack.pack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn").commonItems.loneElement shouldBe 'v'
    Rucksack.pack("ttgJtRGJQctTZtZT").commonItems.loneElement shouldBe 't'
    Rucksack.pack("CrZsJsPPZsGzwwsLwLmpwMDw").commonItems.loneElement shouldBe 's'
  }
