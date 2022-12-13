package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class PacketPairTest extends AnyFunSuite:
  test("Can parse a set of packet pairs") {
    val pairString1 = s"[1,1,3,1,1]${System.lineSeparator}[1,1,5,1,1]"
    val pairString2 = s"[[1],[2,3,4]]${System.lineSeparator}[[1],4]"

    pairString1.as[PacketPair] shouldBe PacketPair("[1,1,3,1,1]".as[Packet], "[1,1,5,1,1]".as[Packet])
    pairString2.as[PacketPair] shouldBe PacketPair("[[1],[2,3,4]]".as[Packet], "[[1],4]".as[Packet])
  }

  test("Can determine if a packet pair is in the right order") {
    PacketPair("[1,1,3,1,1]".as[Packet], "[1,1,5,1,1]".as[Packet]).isCorrectOrder shouldBe true
    PacketPair("[[1],[2,3,4]]".as[Packet], "[[1],4]".as[Packet]).isCorrectOrder shouldBe true
    PacketPair("[9]".as[Packet], "[[8,7,6]]".as[Packet]).isCorrectOrder shouldBe false
  }
