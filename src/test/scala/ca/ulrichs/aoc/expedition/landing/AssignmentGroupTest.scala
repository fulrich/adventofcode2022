package ca.ulrichs.aoc.expedition.landing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class AssignmentGroupTest extends AnyFunSuite:
  test("Can determine if one group in an assignment group contains another section assignment") {
    AssignmentGroup(2 to 4, 6 to 8).containedByOtherGroup shouldBe false
    AssignmentGroup(2 to 3, 4 to 5).containedByOtherGroup shouldBe false
    AssignmentGroup(5 to 7, 7 to 9).containedByOtherGroup shouldBe false
    AssignmentGroup(2 to 8, 3 to 7).containedByOtherGroup shouldBe true
    AssignmentGroup(6 to 6, 4 to 6).containedByOtherGroup shouldBe true
    AssignmentGroup(2 to 6, 4 to 8).containedByOtherGroup shouldBe false
  }

  test("Can parse an assignment group from a string") {
    AssignmentGroup.parse(Seq(2 to 4, 6 to 8)) shouldBe AssignmentGroup(2 to 4, 6 to 8)
  }
