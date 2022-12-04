package ca.ulrichs.aoc.expedition.landing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import _root_.ca.ulrichs.aoc.input.SourceInput

class CampTest extends AnyFunSuite:
  val input = SourceInput(
    "2-4,6-8",
    "2-3,4-5",
    "5-7,7-9",
    "2-8,3-7",
    "6-6,4-6",
    "2-6,4-8"
  )

  test("Can find inefficiencies in all elf assignment groups") {
    val resource = SourceInput.fromResource("day4_elf_camp_assignments")

    Camp.parse(resource).countFullWorkOverlap shouldBe 595
  }

  test("Can find inefficiencies in a small subset of elf assignment groups") {
    Camp.parse(input).countFullWorkOverlap shouldBe 2
  }

  test("Can find all inefficiencies in all elf assignment groups") {
    val resource = SourceInput.fromResource("day4_elf_camp_assignments")

    Camp.parse(resource).countAnyWorkOverlap shouldBe 952
  }

  test("Can find all inefficiencies in a small subset of elf assignment groups") {
    Camp.parse(input).countAnyWorkOverlap shouldBe 4
  }
