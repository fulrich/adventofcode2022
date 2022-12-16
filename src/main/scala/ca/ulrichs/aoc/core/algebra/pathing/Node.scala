package ca.ulrichs.aoc.core.algebra.pathing

import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate
import scala.collection.mutable

case class Node(coordinate: Coordinate, distance: Int):
  def setDistance(newWeight: Int): Node = copy(distance = newWeight)
  def addDistance(addedWeight: Int): Node = setDistance(distance + addedWeight)

object Node:
  def infinity(coordinate: Coordinate): Node = Node(coordinate, Int.MaxValue)
  def start(coordinate: Coordinate): Node = Node(coordinate, 0)

  given Ordering[Node] with
    override def compare(first: Node, second: Node): Int = second.distance.compare(first.distance)

  extension (nodes: Seq[Node])
    def queue: mutable.PriorityQueue[Node] = mutable.PriorityQueue.from(nodes)
