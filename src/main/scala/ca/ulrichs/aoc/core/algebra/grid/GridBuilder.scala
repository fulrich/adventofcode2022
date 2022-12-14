package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Coordinate

private trait GridBuilder:
  def create[A](width: Int, height: Int, fill: A): Grid[A] = create(width, height)(_ => fill)

  def create[A](width: Int, height: Int)(fill: Coordinate => A): Grid[A] = {
    val indexedList = for {
      x <- (0 until width)
      y <- (0 until height)
    } yield {
      (height * y) + x -> fill(Coordinate(x, y))
    }

    Grid(width = width, height = height, values = indexedList.sortBy(_._1).map(_._2).toVector)
  }
