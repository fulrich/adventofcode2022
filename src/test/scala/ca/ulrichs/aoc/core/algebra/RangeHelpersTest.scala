package ca.ulrichs.aoc.core.algebra

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.core.algebra.RangeHelpers.*

class RangeHelpersTest extends AnyFunSuite:
  test("Can determine if another Range is fully contained by the Range") {
    (2 to 4).includes(6 to 8) shouldBe false

    (2 to 8).includes(3 to 7) shouldBe true
    (4 to 6).includes(6 to 6) shouldBe true
  }

  test("Can determine if another section assignment overlaps") {
    (2 to 4).overlaps(6 to 8) shouldBe false
    (2 to 3).overlaps(4 to 5) shouldBe false
    (5 to 7).overlaps(7 to 9) shouldBe true
    (3 to 7).overlaps(2 to 8) shouldBe true
    (6 to 6).overlaps(4 to 6) shouldBe true
    (2 to 6).overlaps(4 to 8) shouldBe true
  }

  test("Can parse a range from a String") {
    RangeHelpers.parse("2-10") shouldBe (2 to 10)
  }
