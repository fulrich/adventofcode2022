package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate
import scala.util.chaining.*

case class DynamicGridSizing[A](private val originalGrid: Grid[A], private val resizeTo: Coordinate, fill: Coordinate => A):
  private val goalDimensions = GridDimensions.fromCoordinates(originalGrid.dimensions.corners :+ resizeTo)
  private val originalWidth = originalGrid.dimensions.width

  def resize: Grid[A] =
    originalGrid  pipe
    addRight      pipe
    addLeft       pipe
    addTop        pipe
    addBottom

  private def addTop(grid: Grid[A]): Grid[A] = resizeIfNotEqual(grid, _.yStart) { numberOfRows =>
    val newDimensions = grid.dimensions.copy(yStart = grid.dimensions.yStart - numberOfRows)
    val newValues = Vector.tabulate(grid.dimensions.width * numberOfRows) { index =>
      fill(newDimensions.toCoordinate(index))
    }

    grid.copy(dimensions = newDimensions, values = newValues ++ grid.values)
  }

  private def addBottom(grid: Grid[A]): Grid[A] = resizeIfNotEqual(grid, _.yEnd) { numberOfRows =>
    val newDimensions = grid.dimensions.copy(yEnd = grid.dimensions.yEnd + numberOfRows)
    val newValues = Vector.tabulate(grid.dimensions.width * numberOfRows) { index =>
      fill(newDimensions.toCoordinate(index + grid.dimensions.length))
    }

    grid.copy(dimensions = newDimensions, values = grid.values ++ newValues)
  }

  private def addRight(grid: Grid[A]): Grid[A] = resizeIfNotEqual(grid, _.xEnd) { numberOfRows =>
    val newDimensions = grid.dimensions.copy(xEnd = grid.dimensions.xEnd + numberOfRows)
    val newValues = originalGrid.values.sliding(originalWidth, originalWidth).zipWithIndex.flatMap { (row, rowIndex) =>
      row ++ Vector.tabulate(numberOfRows) { index => fill(newDimensions.toCoordinate((newDimensions.width * rowIndex) + originalWidth + index)) }
    }

    grid.copy(dimensions = newDimensions, values = newValues.toVector)
  }

  private def addLeft(grid: Grid[A]): Grid[A] = resizeIfNotEqual(grid, _.xStart) { numberOfRows =>
    val newDimensions = grid.dimensions.copy(xStart = grid.dimensions.xStart - numberOfRows)
    val newValues = originalGrid.values.sliding(originalWidth, originalWidth).zipWithIndex.flatMap { (row, rowIndex) =>
      Vector.tabulate(numberOfRows) { index => fill(newDimensions.toCoordinate((newDimensions.width * rowIndex) + index)) } ++ row
    }

    grid.copy(dimensions = newDimensions, values = newValues.toVector)
  }

  private def resizeIfNotEqual(grid: Grid[A], getDimension: GridDimensions => Int)(resize: Int => Grid[A]): Grid[A] = {
    val numberOfNewRows = Math.abs(getDimension(grid.dimensions) - getDimension(goalDimensions))

    if numberOfNewRows == 0 then grid else resize(numberOfNewRows)
  }
