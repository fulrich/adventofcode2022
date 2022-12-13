package ca.ulrichs.aoc.expedition.device.distress

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class PacketTest extends AnyFunSuite:
  test("Can compare packets simple use cases") {
    ("[1,1,3,1,1]".as[Packet] compare "[1,1,5,1,1]".as[Packet]) should be > 0
    ("[[1],[2,3,4]]".as[Packet] compare "[[1],4]".as[Packet]) should be > 0
    ("[9]".as[Packet] compare "[[8,7,6]]".as[Packet]) should be < 0
    ("[[4,4],4,4]".as[Packet] compare "[[4,4],4,4,4]".as[Packet]) should be > 0
    ("[7,7,7,7]".as[Packet] compare "[7,7,7]".as[Packet]) should be < 0
  }
  test("Can compare packets in empty use cases") {
    ("[]".as[Packet] compare "[3]".as[Packet]) should be > 0
    ("[[[]]]".as[Packet] compare "[[]]".as[Packet]) should be < 0
  }

  test("Can compare packets in complex use cases") {
    ("[1,[2,[3,[4,[5,6,7]]]],8,9]".as[Packet] compare "[1,[2,[3,[4,[5,6,0]]]],8,9]".as[Packet]) should be < 0
  }
