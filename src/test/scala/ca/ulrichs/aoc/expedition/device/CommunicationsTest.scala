package ca.ulrichs.aoc.expedition.device

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class CommunicationsTest extends AnyFunSuite:
  test("Can determine the last index of the start of message marker from the communications source") {
    val source = SourceInput.fromResource("day6_communications_source")
    Communications(source).startOfMessageMarker shouldBe 3051
  }

  test("Can determine the last index of the start of message marker in example inputs") {
    Communications(SourceInput("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).startOfMessageMarker shouldBe 19
    Communications(SourceInput("bvwbjplbgvbhsrlpgdmjqwftvncz")).startOfMessageMarker shouldBe 23
    Communications(SourceInput("nppdvjthqldpwncqszvftbrmjlhg")).startOfMessageMarker shouldBe 23
    Communications(SourceInput("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).startOfMessageMarker shouldBe 29
    Communications(SourceInput("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).startOfMessageMarker shouldBe 26
  }

  test("Can determine the last index of the start of packet marker from the communications source") {
    val source = SourceInput.fromResource("day6_communications_source")
    Communications(source).startOfPacketMarker shouldBe 1235
  }

  test("Can determine the last index of the start of packet marker in example inputs") {
    Communications(SourceInput("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).startOfPacketMarker shouldBe 7
    Communications(SourceInput("bvwbjplbgvbhsrlpgdmjqwftvncz")).startOfPacketMarker shouldBe 5
    Communications(SourceInput("nppdvjthqldpwncqszvftbrmjlhg")).startOfPacketMarker shouldBe 6
    Communications(SourceInput("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).startOfPacketMarker shouldBe 10
    Communications(SourceInput("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).startOfPacketMarker shouldBe 11
  }
