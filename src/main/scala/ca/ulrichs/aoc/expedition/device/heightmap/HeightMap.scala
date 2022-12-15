package ca.ulrichs.aoc.expedition.device.heightmap

import ca.ulrichs.aoc.core.algebra.pathing.PathFinding
import ca.ulrichs.aoc.core.algebra.Coordinate
import ca.ulrichs.aoc.core.algebra.grid.Grid
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
    val grid: Grid[Char] = Grid.simpleFromStrings(source.asSeq[String])

    val start = grid.find('S')
    val destination = grid.find('E')

    HeightMap(
      start = start,
      destination = destination,
      grid = grid.updated(start, 'a').updated(destination, 'z')
    )
  }
