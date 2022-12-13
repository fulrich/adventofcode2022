package ca.ulrichs.aoc.core.algebra.pathing

import ca.ulrichs.aoc.core.algebra.{Coordinate, Grid}

import scala.collection.{mutable, *}
import scala.util.control.Breaks.{break, breakable}

case class PathFinding[A](grid: Grid[A], neighbours: Coordinate => Seq[Coordinate], costBetween: (A, A) => Option[Int]) {
  def dijkstras(start: Coordinate, goal: Coordinate): Int = {
    val distances: mutable.Map[Coordinate, Int] = setupDistances(start)
    val nodesToCheck = setupPriorityQueue(start)

    while nodesToCheck.nonEmpty do
      val current = nodesToCheck.dequeue
      val currentNeighbours = neighbours(current.coordinate).filter(grid.contains)

      currentNeighbours.foreach { destination =>
        for
          destinationCost <- distances.get(destination)
          addedCost <- costBetween(grid(current.coordinate), grid(destination))
          newCost = current.distance + addedCost
        do
          if newCost < destinationCost then
            distances.update(destination, newCost)
            nodesToCheck.enqueue(Node(destination, newCost))
          end if
      }
    end while

    distances(goal)
  }

  private def setupDistances(start: Coordinate): mutable.Map[Coordinate, Int] =
    mutable.Map.from[Coordinate, Int](
      grid.keys.map(coordinate => coordinate -> (if coordinate == start then 0 else Int.MaxValue))
    )

  private def setupPriorityQueue(start: Coordinate): mutable.PriorityQueue[Node] = mutable.PriorityQueue(Node(start, 0))
}
