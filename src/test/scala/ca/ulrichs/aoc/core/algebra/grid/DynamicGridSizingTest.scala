package ca.ulrichs.aoc.core.algebra.grid
import ca.ulrichs.aoc.core.algebra.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class DynamicGridSizingTest extends AnyFunSuite with LoneElement:
  val testInput: Seq[String] = Seq(
    "12",
    "34",
  )

  val grid: Grid[Int] = Grid.fromStrings(testInput, default = 0) { (_, value) => value.asDigit }

//  test("Can add a new row to the top of the Grid") {
//    grid.print()
//
//    println()
//
//    DynamicGridSizing(grid, resizeTo = Coordinate(0, -3), fill = coord => coord.x + coord.y).resize.print()
//  }
//
//  test("Can add a new row to the bottom of the Grid") {
//
//    grid.print()
//
//    println()
//
//    DynamicGridSizing(grid, resizeTo = Coordinate(0, 5)).resize(coord => 0 + coord.x + coord.y).print()
//  }

//  test("Can add a new column to the right of the Grid") {
//    grid.print()
//
//    println()
//
//    DynamicGridSizing(grid, resizeTo = Coordinate(5, 0), fill = coord => coord.x + coord.y).resize.print()
//  }

//  test("Can add a new column to the left of the Grid") {
//    grid.print()
//
//    println()
//
//    DynamicGridSizing(grid, resizeTo = Coordinate(-4, 0), fill = coord => coord.x + coord.y).resize.print()
//  }

  test("Can resize a grid both horizontally and vertically") {
    val originalGrid = Grid.simpleFromStrings(testInput)

    DynamicGridSizing(originalGrid, resizeTo = Coordinate(4, 4), _ => 'X').resize.print()
    println
    DynamicGridSizing(originalGrid, resizeTo = Coordinate(-3, -3), _ => 'X').resize.print()
  }