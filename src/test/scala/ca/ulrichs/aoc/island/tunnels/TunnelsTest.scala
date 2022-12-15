package ca.ulrichs.aoc.island.tunnels

import ca.ulrichs.aoc.core.algebra.Coordinate
import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import ca.ulrichs.aoc.core.input.StringParsing.*

class TunnelsTest extends AnyFunSuite:
  val exampleSource: SourceInput = SourceInput.fromResource("day15_example_sensors_and_beacons")
  val realSource: SourceInput = SourceInput.fromResource("day15_sensors_and_beacons")

  test("Can find the number of covered spaces in the example input") {
    Tunnels(exampleSource).coveredSpaces(10) shouldBe 26
  }

  test("Can find the number of covered spaces in the real input") {
    Tunnels(realSource).coveredSpaces(2000000) shouldBe 5716881
  }

  test("Can find the only possible distress signal position in the example input") {
    Tunnels(exampleSource).distressSignalTuningFrequency(20) shouldBe 56000011
  }

  test("Can find the only possible distress signal position in the real input") {
    Tunnels(realSource).distressSignalTuningFrequency(4000000) shouldBe 10852583132904L
  }
