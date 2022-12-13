package ca.ulrichs.aoc.expedition.device.heightmap

import ca.ulrichs.aoc.core.algebra.pathing.PathFinding
import ca.ulrichs.aoc.core.algebra.{Coordinate, Grid}
import ca.ulrichs.aoc.core.input.SourceInput

case class HeightMap(start: Coordinate, destination: Coordinate, grid: Grid[Char]) {
  lazy val fastestRouteLength: Long = fastestRouteFrom(start)

  def fastestRouteFrom(otherStart: Coordinate) =
    PathFinding[Char](grid = grid, neighbours = _.adjacent, costBetween = costBetween)
      .dijkstras(otherStart, destination)

  def costBetween(current: Char, next: Char): Option[Int] =
    if current.toInt >= (next.toInt - 1) then Some(1) else None
}

object HeightMap:
  def apply(source: SourceInput): HeightMap = {
    val grid: Grid[Char] = Grid.parse(source.asSeq[String])((_, char) => char)

    val start = grid.findIndex('S').get
    val destination = grid.findIndex('E').get

    HeightMap(
      start = start,
      destination = destination,
      grid = grid.updated(start, 'a').updated(destination, 'z')
    )
  }
