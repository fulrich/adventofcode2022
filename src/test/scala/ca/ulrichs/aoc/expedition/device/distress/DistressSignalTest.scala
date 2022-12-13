package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class DistressSignalTest extends AnyFunSuite:
  test("Can determine the index of all pairs already in order in the real distress signal") {
    val source = SourceInput.fromResource("day13_packet_pairs")

    source.as[DistressSignal].inOrderIndexes.sum shouldBe 6076
  }

  test("Can determine the index of all pairs already in order in the example distress signal") {
    val source = SourceInput.fromResource("day13_example_packet_pairs")

    source.as[DistressSignal].inOrderIndexes.sum shouldBe 13
  }

  test("Can determine the decoder key of the real distress signal") {
    val source = SourceInput.fromResource("day13_packet_pairs")

    source.as[DistressSignal].decoderKey shouldBe 24805
  }

  test("Can determine the decoder key of the example distress signal") {
    val source = SourceInput.fromResource("day13_example_packet_pairs")

    source.as[DistressSignal].decoderKey shouldBe 140
  }
