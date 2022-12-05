package ca.ulrichs.aoc.expedition.packing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.LoneElement
import ca.ulrichs.aoc.core.input.StringParsing.*

class RucksackTest extends AnyFunSuite with LoneElement:
  test("Can pack a Rucksack from an item list") {
    val rucksack = "vJrwpWtwJgWrhcsFMMfFFhFp".as[Rucksack]

    rucksack.leftCompartment.mkString shouldBe "vJrwpWtwJgWr"
    rucksack.rightCompartment.mkString shouldBe "hcsFMMfFFhFp"
  }

  test("Can find the common items between the compartments in a Rucksack") {
    "vJrwpWtwJgWrhcsFMMfFFhFp".as[Rucksack].commonItems.loneElement shouldBe 'p'
    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL".as[Rucksack].commonItems.loneElement shouldBe 'L'
    "PmmdzqPrVvPwwTWBwg".as[Rucksack].commonItems.loneElement shouldBe 'P'
    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn".as[Rucksack].commonItems.loneElement shouldBe 'v'
    "ttgJtRGJQctTZtZT".as[Rucksack].commonItems.loneElement shouldBe 't'
    "CrZsJsPPZsGzwwsLwLmpwMDw".as[Rucksack].commonItems.loneElement shouldBe 's'
  }
