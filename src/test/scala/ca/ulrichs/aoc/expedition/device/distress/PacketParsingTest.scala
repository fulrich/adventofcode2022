package ca.ulrichs.aoc.expedition.device.distress

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class PacketParsingTest extends AnyFunSuite:
  test("Can parse a simple input") {
    "[1,1,3,1,1]".as[Packet].toString shouldBe "[1,1,3,1,1]"
    "[1,1,5,1,1]".as[Packet].toString shouldBe "[1,1,5,1,1]"
  }

  test("Can parse a moderately complex input") {
    "[[1],[2,3,4]]".as[Packet].toString shouldBe "[[1],[2,3,4]]"
    "[[1],4]".as[Packet].toString shouldBe "[[1],4]"
  }

  test("Can parse empty input sets") {
    "[]".as[Packet].toString shouldBe "[]"
    "[[]]".as[Packet].toString shouldBe "[[]]"
    "[[[]]]".as[Packet].toString shouldBe "[[[]]]"
    "[[[]],[[]]]".as[Packet].toString shouldBe "[[[]],[[]]]"
  }

  test("Can parse complex input sets") {
    "[1,[2,[3,[4,[5,6,7]]]],8,9]".as[Packet].toString shouldBe "[1,[2,[3,[4,[5,6,7]]]],8,9]"
    "[1,[2,[3,[4,[5,6,0]]]],8,9]".as[Packet].toString shouldBe "[1,[2,[3,[4,[5,6,0]]]],8,9]"
  }

  test("Can parse complex input sets with multi-digit numbers") {
    "[12,[2,[3,[4,[51,6,7]]]],82,9]".as[Packet].toString shouldBe "[12,[2,[3,[4,[51,6,7]]]],82,9]"
  }
