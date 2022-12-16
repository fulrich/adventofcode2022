package ca.ulrichs.aoc.island.tunnels

import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate
import ca.ulrichs.aoc.core.input.InputParsing

import scala.util.matching.Regex

case class Sensor(position: Coordinate, closestBeacon: Coordinate):
  private lazy val distance: Int = Math.abs(position.x - closestBeacon.x) + Math.abs(position.y - closestBeacon.y)
  private lazy val minimumRow: Int = position.y - distance
  private lazy val maximumRow: Int = position.y + distance

  def inRange(coordinate: Coordinate): Boolean =
    includesRow(coordinate.y) && coordinate.x >= minOfArea(coordinate.y) && coordinate.x <= maxOfArea(coordinate.y)

  def includesRow(row: Int): Boolean = row >= minimumRow && row <= maximumRow

  def rowRange(row: Int): Range = minOfArea(row) to maxOfArea(row)
  def minOfArea(row: Int): Int = position.x - (distance - Math.abs(position.y - row))
  def maxOfArea(row: Int): Int = position.x + (distance - Math.abs(position.y - row))

  override def toString =
    s"Sensor at x=${position.x}, y=${position.y}: closest beacon is at x=${closestBeacon.x}, y=${closestBeacon.y}"

object Sensor:
  private val sensorParser: Regex = "Sensor at x=([0-9-]+), y=([0-9-]+): closest beacon is at x=([0-9-]+), y=([0-9-]+)".r

  given InputParsing[Sensor] with
    def parse(input: String): Sensor = input match  {
      case sensorParser(x, y, beaconX, beaconY) => Sensor(Coordinate(x.toInt, y.toInt), Coordinate(beaconX.toInt, beaconY.toInt))
      case _ => throw IllegalArgumentException(s"Could not parse the sensor defined by: $input")
    }
