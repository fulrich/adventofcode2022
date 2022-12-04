package ca.ulrichs.aoc.expedition.landing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class SectionAssignmentTest extends AnyFunSuite:
  test("Can determine if another section assignment is contained by itself") {
    SectionAssignment(2, 4).contains(SectionAssignment(6, 8)) shouldBe false

    SectionAssignment(2, 8).contains(SectionAssignment(3, 7)) shouldBe true
    SectionAssignment(4, 6).contains(SectionAssignment(6, 6)) shouldBe true
  }

  test("Can determine if another section assignment overlaps") {
    SectionAssignment(2, 4).overlaps(SectionAssignment(6, 8)) shouldBe false
    SectionAssignment(2, 3).overlaps(SectionAssignment(4, 5)) shouldBe false
    SectionAssignment(5, 7).overlaps(SectionAssignment(7, 9)) shouldBe true
    SectionAssignment(3, 7).overlaps(SectionAssignment(2, 8)) shouldBe true
    SectionAssignment(6, 6).overlaps(SectionAssignment(4, 6)) shouldBe true
    SectionAssignment(2, 6).overlaps(SectionAssignment(4, 8)) shouldBe true
  }

  test("Can parse a Section Assignment from a string") {
    SectionAssignment.parse("2-4") shouldBe SectionAssignment(2, 4)
    SectionAssignment.parse("56-79") shouldBe SectionAssignment(56, 79)
  }
