package ca.ulrichs.aoc.island.waterfall

import ca.ulrichs.aoc.core.algebra.Coordinate
import ca.ulrichs.aoc.core.algebra.grid.Grid
import ca.ulrichs.aoc.core.input.SourceInput
import ca.ulrichs.aoc.core.input.StringParsing.*
import ca.ulrichs.aoc.island.waterfall.CaveSystem.SandStart

import scala.annotation.tailrec

case class CaveSystem(grid: Grid[Char]):
  lazy val sandUnits: Seq[Coordinate] = grid.findAll('o')

  lazy val afterSandfall: CaveSystem = sandFall(SandStart, grid)

  @tailrec
  final def sandFall(sand: Coordinate, innerGrid: Grid[Char]): CaveSystem = {
    val availableSpots = (innerGrid.at(sand.down), innerGrid.at(sand.downLeft), innerGrid.at(sand.downRight))
    val maxY = Seq(sand.down, sand.downLeft, sand.downLeft).map(_.y).max
    //    val maxX = Seq(sand.down, sand.downLeft, sand.downLeft).map(_.x).max

    if maxY >= innerGrid.dimensions.height then return CaveSystem(innerGrid)
    //    if maxX >= innerGrid.width then return CaveSystem(innerGrid)

    availableSpots match {
      case (Some('.'), _, _) => sandFall(sand.down, innerGrid)
      case (_, Some('.'), _) => sandFall(sand.downLeft, innerGrid)
      case (_, _, Some('.')) => sandFall(sand.downRight, innerGrid)
      case _ =>
        if sand == SandStart then CaveSystem(innerGrid.updated(SandStart, 'o'))
        else sandFall(SandStart, innerGrid.updated(sand, 'o'))
    }
  }


object CaveSystem:
  val SandStart: Coordinate = Coordinate(500, 0)

  def parse(input: SourceInput): CaveSystem = {
    val all = input.asSeq[String].flatMap { rawRockLine =>
      val coordinateRangeList = rawRockLine.split(" -> ").map(_.as[Coordinate])

      coordinateRangeList.foldLeft(Seq.empty[Coordinate]) {
        case (Nil, target) => Seq(target)
        case (head :: Nil, target) => head to target
        case (init :+ last, target) => init ++ (last to target)
      }
    }

    val grid = Grid.fromCoordinates(all)(value => if all.contains(value) then '#' else '.').updated(SandStart, '+')
    CaveSystem(grid)
  }

  def parseWithFloor(input: SourceInput): CaveSystem = {
    val coordinateRanges: Seq[Seq[Coordinate]] = input.asSeq[String].map(_.split(" -> ").map(_.as[Coordinate]))

    val coordinates: Seq[Coordinate] = coordinateRanges.flatMap { coordinateList =>
      coordinateList.foldLeft(Seq.empty[Coordinate]) {
        case (Nil, target) => Seq(target)
        case (head :: Nil, target) => head to target
        case (init :+ last, target) => init ++ (last to target)
      }
    }

    val maxX = coordinates.map(_.x).max
    val floorY = coordinates.map(_.y).max + 2

    val floorToSandStart = (floorY - SandStart.y)
    val floor = Coordinate(SandStart.x - floorToSandStart, floorY) to Coordinate(SandStart.x + floorToSandStart, floorY)
    val all = coordinates ++ floor :+ SandStart

    val grid = Grid.fromCoordinates(all)(value => if all.contains(value) then '#' else '.').updated(SandStart, '+')
    CaveSystem(grid)
  }
