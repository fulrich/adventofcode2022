package ca.ulrichs.aoc.core.algebra

import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class GridTest extends AnyFunSuite with LoneElement:
  val testInput = Seq(
    "123",
    "456",
    "789"
  )

  val grid  = Grid.parse(testInput) { (key, value) => value.asDigit }

  test("Can create a grid from a list of strings") {
    grid.at(0, 0) should contain (1)
    grid.at(1, 0) should contain (2)
    grid.at(2, 0) should contain (3)

    grid.at(0, 1) should contain (4)
    grid.at(1, 1) should contain (5)
    grid.at(2, 1) should contain (6)

    grid.at(0, 2) should contain (7)
    grid.at(1, 2) should contain (8)
    grid.at(2, 2) should contain (9)
  }

  test("Can transform the values at specific positions in a Grid") {
    val positions = Seq(Coordinate(1, 1), Coordinate(2, 2))
    val transformed = grid.transformAt(positions)(_ + 10)

    transformed.at(0, 0) should contain (1)
    transformed.at(1, 0) should contain (2)
    transformed.at(2, 0) should contain (3)

    transformed.at(0, 1) should contain (4)
    transformed.at(1, 1) should contain (15)
    transformed.at(2, 1) should contain (6)

    transformed.at(0, 2) should contain (7)
    transformed.at(1, 2) should contain (8)
    transformed.at(2, 2) should contain (19)
  }

  test("transformAt allows the the same position to be transformed multiple times") {
    val positions = Seq(Coordinate(1, 1), Coordinate(1, 1))
    val transformed = grid.transformAt(positions)(_ + 10)

    transformed.at(1, 1) should contain (25)
  }

  test("Can map the values of the grid") {
    val mapped = grid.mapValues(_ + 10)

    mapped.at(0, 0) should contain (11)
    mapped.at(1, 0) should contain (12)
    mapped.at(2, 0) should contain (13)

    mapped.at(0, 1) should contain (14)
    mapped.at(1, 1) should contain (15)
    mapped.at(2, 1) should contain (16)

    mapped.at(0, 2) should contain (17)
    mapped.at(1, 2) should contain (18)
    mapped.at(2, 2) should contain (19)
  }

  test("Can get all the keys of the grid") {
    grid.keys should contain theSameElementsAs Seq(
      Coordinate(0, 0),
      Coordinate(1, 0),
      Coordinate(2, 0),
      Coordinate(0, 1),
      Coordinate(1, 1),
      Coordinate(2, 1),
      Coordinate(0, 2),
      Coordinate(1, 2),
      Coordinate(2, 2)
    )
  }

  test("Can get all the values of the grid") {
    grid.values should contain theSameElementsAs Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  test("Can create a grid of a given size") {
    val grid = Grid.create(10, 10, fill = 5)

    grid.height shouldBe 10
    grid.width shouldBe 10
    grid.keys should have length 100
    grid.values.distinct.loneElement shouldBe 5
  }

  test("Drop row can drop a given row") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    grid.height shouldBe 5
    grid.width shouldBe 5

    val droppedRow = grid.dropRow(1)
    droppedRow.width shouldBe 5
    droppedRow.height shouldBe 4
    droppedRow.at(0, 0) should contain (0)
    droppedRow.at(0, 1) should contain (2)
  }

  test("Drop column can drop a given column") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    grid.height shouldBe 5
    grid.width shouldBe 5

    val droppedColumn = grid.dropColumn(1)
    droppedColumn.width shouldBe 4
    droppedColumn.height shouldBe 5
    droppedColumn.at(0, 0) should contain (0)
    droppedColumn.at(1, 0) should contain (2)
  }

  test("Can create a grid given a list of coordinates") {
    val grid = Grid.fromCoordinates(Seq("3, 3", "5, 5"), default = 0)(_.x)

    grid.height shouldBe 6
    grid.width shouldBe 6
    grid.keys should have length 36
    grid.values.distinct should contain theSameElementsAs Seq(3, 5, 0)

    grid.at(3, 3) should contain (3)
    grid.at(5, 5) should contain (5)
  }

  test("Can split a grid across a horizontal line") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    val (grid1, grid2) = grid.splitOnRow(row = 2)

    grid1.width shouldBe 5
    grid1.height shouldBe 3
    grid1.at(0, 0) should contain (0)
    grid1.at(4, 2) should contain (6)

    grid2.width shouldBe 5
    grid2.height shouldBe 2
    grid2.at(0, 0) should contain (3)
    grid2.at(4, 1) should contain (8)
  }

  test("Can split a grid accross a vertical line") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    val (grid1, grid2) = grid.splitOnColumn(column = 2)

    grid1.width shouldBe 3
    grid1.height shouldBe 5
    grid1.at(0, 0) should contain (0)
    grid1.at(2, 4) should contain (6)

    grid2.width shouldBe 2
    grid2.height shouldBe 5
    grid2.at(0, 0) should contain (3)
    grid2.at(1, 4) should contain (8)
  }

  test("Can split a grid across a horizontal line removing the split line") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    val (top, bottom) = grid.splitOnRow(row = 2, dropSplitRow = true)

    top.width shouldBe 5
    top.height shouldBe 2
    top.at(0, 0) should contain (0)
    top.at(4, 1) should contain (5)

    bottom.width shouldBe 5
    bottom.height shouldBe 2
    bottom.at(0, 0) should contain (3)
    bottom.at(4, 1) should contain (8)
  }

  test("Can split a grid across a vertical line removing the split line") {
    val grid = Grid.create(width = 5, height = 5)(coordinate => coordinate.x + coordinate.y)
    val (left, right) = grid.splitOnColumn(column = 2, dropSplitColumn = true)

    left.width shouldBe 2
    left.height shouldBe 5
    left.at(0, 0) should contain (0)
    left.at(1, 4) should contain (5)

    right.width shouldBe 2
    right.height shouldBe 5
    right.at(0, 0) should contain (3)
    right.at(1, 4) should contain (8)
  }

  test("Can merge two grids together") {
    val grid1 = Grid.create(width = 2, height = 2, fill = 2)
    val grid2 = Grid.create(width = 4, height = 4, fill = 4)

    val merged = grid1.merge(grid2)(_ + _)

    merged.height shouldBe 4
    merged.width shouldBe 4
    merged.at(0, 0) should contain (6)
    merged.at(1, 1) should contain (6)
    merged.at(2, 2) should contain (4)
    merged.at(3, 3) should contain (4)
  }
