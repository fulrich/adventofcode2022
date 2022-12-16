package ca.ulrichs.aoc.island.tunnels

import ca.ulrichs.aoc.core.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class SensorTest extends AnyFunSuite:
  val position: Coordinate = Coordinate(8, 7)
  val closestBeacon: Coordinate = Coordinate(2, 10)
  val sensor: Sensor = Sensor(position = position, closestBeacon = closestBeacon)

  test("Can determine the sensor covered area by row") {
    sensor.rowRange(-2) shouldBe (8 to 8)
    sensor.rowRange(-1) shouldBe (7 to 9)
    sensor.rowRange(0) shouldBe (6 to 10)

    sensor.rowRange(6) shouldBe (0 to 16)
    sensor.rowRange(7) shouldBe (-1 to 17)
    sensor.rowRange(8) shouldBe (0 to 16)

    sensor.rowRange(14) shouldBe (6 to 10)
    sensor.rowRange(15) shouldBe (7 to 9)
    sensor.rowRange(16) shouldBe (8 to 8)
  }

  test("Can detect when a given coordinate is not in the sensor range") {
    sensor.inRange(Coordinate(7, -2)) shouldBe false
    sensor.inRange(Coordinate(8, -3)) shouldBe false
    sensor.inRange(Coordinate(9, -2)) shouldBe false

    sensor.inRange(Coordinate(-1, 6)) shouldBe false
    sensor.inRange(Coordinate(-2, 7)) shouldBe false
    sensor.inRange(Coordinate(-1, 8)) shouldBe false

    sensor.inRange(Coordinate(7, 16)) shouldBe false
    sensor.inRange(Coordinate(8, 17)) shouldBe false
    sensor.inRange(Coordinate(9, 18)) shouldBe false

    sensor.inRange(Coordinate(17, 6)) shouldBe false
    sensor.inRange(Coordinate(18, 7)) shouldBe false
    sensor.inRange(Coordinate(17, 8)) shouldBe false
  }

  test("Can detect when a given coordinate is in the sensor range") {
    sensor.inRange(position) shouldBe true
    sensor.inRange(closestBeacon) shouldBe true

    sensor.inRange(position.up) shouldBe true
    sensor.inRange(position.down) shouldBe true
    sensor.inRange(position.left) shouldBe true
    sensor.inRange(position.right) shouldBe true

    sensor.inRange(Coordinate(8, -2)) shouldBe true
    sensor.inRange(Coordinate(-1, 7)) shouldBe true
    sensor.inRange(Coordinate(8, 16)) shouldBe true
    sensor.inRange(Coordinate(17, 7)) shouldBe true
  }

  test("Can parse a sensor from a string") {
    val sensorInput = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15"

    sensorInput.as[Sensor] shouldBe Sensor(Coordinate(2, 18), Coordinate(-2, 15))
  }
