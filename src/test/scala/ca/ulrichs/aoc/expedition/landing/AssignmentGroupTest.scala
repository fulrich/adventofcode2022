package ca.ulrichs.aoc.expedition.landing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class AssignmentGroupTest extends AnyFunSuite:
  test("Can determine if one group in an assignment group contains another section assignment") {
    AssignmentGroup.parse("2-4,6-8").containedByOtherGroup shouldBe false
    AssignmentGroup.parse("2-3,4-5").containedByOtherGroup shouldBe false
    AssignmentGroup.parse("5-7,7-9").containedByOtherGroup shouldBe false
    AssignmentGroup.parse("2-8,3-7").containedByOtherGroup shouldBe true
    AssignmentGroup.parse("6-6,4-6").containedByOtherGroup shouldBe true
    AssignmentGroup.parse("2-6,4-8").containedByOtherGroup shouldBe false
  }

  test("Can parse an assignment group from a string") {
    AssignmentGroup.parse("2-4,6-8") shouldBe AssignmentGroup(
      SectionAssignment(2, 4),
      SectionAssignment(6, 8)
    )
  }
