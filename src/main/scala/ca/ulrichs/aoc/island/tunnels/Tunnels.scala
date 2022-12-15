package ca.ulrichs.aoc.island.tunnels

import ca.ulrichs.aoc.core.algebra.Coordinate
import ca.ulrichs.aoc.core.input.{InputParsing, SourceInput}

import scala.collection.parallel.CollectionConverters.*
import scala.annotation.tailrec
import scala.collection.parallel.immutable.ParVector

case class Tunnels(sensors: Seq[Sensor]):
  def coveredSpaces(row: Int): Int =
    countCovered(
      current = Coordinate(minimumCoveredX(row), row),
      endX = maximumCoveredX(row)
    ) - beaconsInRow(row).length

  @tailrec
  private final def countCovered(current: Coordinate, endX: Int, count: Int = 0): Int =
    sensors.find(_.inRange(current)) match {
      case Some(sensor) => countCovered(nextCoordinate(current, sensor), endX, count + increment(current, sensor))
      case None if current.x < endX => countCovered(current.right, endX, count)
      case None => count
    }

  def distressSignalTuningFrequency(searchAreaMaximum: Int): Long = search(searchAreaMaximum) match {
    case Seq(distressSignalLocation) => (distressSignalLocation.x * 4000000L) + distressSignalLocation.y
    case Nil => throw InternalError("No distress signals could be found")
    case _ => throw InternalError("Found multiple possible distress signals")
  }

  def search(maximum: Int): Seq[Coordinate] =
    (0 to maximum).toVector.par.flatMap { row =>
      search(Coordinate(0, row), maximum)
    }.toVector

  @tailrec
  private final def search(current: Coordinate, maximum: Int): Option[Coordinate] =
    sensors.find(_.inRange(current)) match {
      case Some(sensor) => search(nextCoordinate(current, sensor), maximum)
      case None if current.x <= maximum => Some(current)
      case None => None
    }

  private def nextCoordinate(current: Coordinate, sensor: Sensor): Coordinate =
    Coordinate(sensor.maxOfArea(current.y) + 1, current.y)

  private def increment(current: Coordinate, sensor: Sensor): Int =
    (sensor.maxOfArea(current.y) - current.x) + 1

  private def sensorsInRow(row: Int): Seq[Sensor] = sensors.filter(_.includesRow(row))
  private def beaconsInRow(row: Int): Seq[Coordinate] = sensors.map(_.closestBeacon).filter(_.y == row).distinct
  private def minimumCoveredX(row: Int): Int = sensorsInRow(row).map(_.minOfArea(row)).min
  private def maximumCoveredX(row: Int): Int = sensorsInRow(row).map(_.maxOfArea(row)).min

object Tunnels:
  def apply(source: SourceInput): Tunnels = Tunnels(source.asSeq[Sensor])
