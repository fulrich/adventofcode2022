package ca.ulrichs.aoc.island.forest

import ca.ulrichs.aoc.core.algebra.coordinate.{Coordinate, Direction}
import ca.ulrichs.aoc.core.algebra.Grid
import ca.ulrichs.aoc.core.input.SourceInput

import scala.annotation.tailrec

class Forest(val trees: Grid[Int]) {
  lazy val visibleTrees: Seq[Coordinate] = (visibleTreeRows ++ visibleTreeColumns).distinct

  lazy val visibleTreeRows: Seq[Coordinate] =
    (for {
      x <- 0 to trees.maximumColumn
    } yield {
      visibleTrees(Coordinate(x, 0), _.down) ++ visibleTrees(Coordinate(x, trees.maximumRow), _.up)
    }).flatten.distinct

  lazy val visibleTreeColumns: Seq[Coordinate] =
    (for {
      y <- 0 to trees.maximumRow
    } yield {
      visibleTrees(Coordinate(0, y), _.right) ++ visibleTrees(Coordinate(trees.maximumColumn, y), _.left)
    }).flatten.distinct

  def visibleTrees(start: Coordinate, direction: Coordinate => Coordinate): Seq[Coordinate] =
    if trees.contains(start) then visibleTrees(direction(start), Seq(start), direction) else Seq.empty

  @tailrec
  final def visibleTrees(coordinate: Coordinate, visible: Seq[Coordinate], direction: Coordinate => Coordinate): Seq[Coordinate] = {
    val maximumPreviousHeight = visible.flatMap(trees.at).max

    trees.at(coordinate) match {
      case Some(height) if height > maximumPreviousHeight => visibleTrees(direction(coordinate), visible :+ coordinate, direction)
      case Some(_) => visibleTrees(direction(coordinate), visible, direction)
      case None => visible
    }
  }


  def scenicScore(at: Coordinate): Int = Direction.Adjacent.map(d => scenicScore(at, d)).product

  final def scenicScore(coordinate: Coordinate, direction: Direction): Int =
    trees.at(coordinate).map(height => scenicScore(height, coordinate.move(direction), 0, direction)).getOrElse(0)

  final def scenicScore(referenceHeight: Int, coordinate: Coordinate, score: Int, direction: Direction): Int =
    trees.at(coordinate) match {
      case Some(height) if height < referenceHeight => scenicScore(referenceHeight, coordinate.move(direction), score + 1, direction)
      case Some(_) => score + 1
      case None => score
    }
}

object Forest:
  def apply(source: SourceInput): Forest =
    new Forest(Grid.parse(source.asSeq[String]) { (coordinate, height) => height.asDigit })
