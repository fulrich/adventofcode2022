package ca.ulrichs.aoc.core.algebra.pathing

import ca.ulrichs.aoc.core.algebra.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable

class NodeTest extends AnyFunSuite:
  test("Can set the weight of a Node") {
    val node = Node.infinity(Coordinate.origin)

    node.distance shouldBe Int.MaxValue
    node.setDistance(50).distance shouldBe 50
  }

  test("Can add to the weight of a Node") {
    val node = Node(Coordinate.origin, 10)

    node.distance shouldBe 10
    node.addDistance(50).distance shouldBe 60
  }

  test("Can covert a sequence of nodes to a Priority Queue") {
    val nodes = Seq(
      Node(Coordinate.origin, 1),
      Node(Coordinate.origin.right, 2)
    )

    val priorityQueue = nodes.queue
    priorityQueue shouldBe a[mutable.PriorityQueue[_]]
    priorityQueue.dequeue shouldBe Node(Coordinate.origin, 1)
    priorityQueue.dequeue shouldBe Node(Coordinate.origin.right, 2)
  }

  test("When enqueueing a new node it is properly prioritized") {
    val addedNode = Node(Coordinate.origin.down, 15)
    val nodes = Seq(
      Node(Coordinate.origin, 10),
      Node(Coordinate.origin.right, 20)
    )

    val queue = nodes.queue
    queue.enqueue(addedNode)

    queue.dequeue shouldBe Node(Coordinate.origin, 10)
    queue.dequeue shouldBe addedNode
    queue.dequeue shouldBe Node(Coordinate.origin.right, 20)
  }
