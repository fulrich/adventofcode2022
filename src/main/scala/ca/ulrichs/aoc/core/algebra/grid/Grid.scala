package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.Grid
import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate

case class Grid[+A](dimensions: GridDimensions, values: IndexedSeq[A]):
  private lazy val indexed: IndexedSeq[(A, Int)] = values.zipWithIndex
  lazy val keys: Seq[Coordinate] = values.indices.map(dimensions.toCoordinate)

  def apply(x: Int, y: Int): A = apply(Coordinate(x, y))
  def apply(coordinate: Coordinate): A = values(dimensions.toIndex(coordinate))

  def at(x: Int, y: Int): Option[A] = at(Coordinate(x, y))
  def at(coordinate: Coordinate): Option[A] = values.lift(dimensions.toIndex(coordinate))

  def contains(coordinate: Coordinate): Boolean = dimensions.isCoordinateValid(coordinate)
  def contains(x: Int, y: Int): Boolean = contains(Coordinate(x, y))
  def containsValue[B >: A](value: B): Boolean = values.contains(value)

  def find[B >: A](value: B): Coordinate = dimensions.toCoordinate(values.indexOf(value))
  def findOption[B >: A](value: B): Option[Coordinate] = dimensions.toCoordinateOption(values.indexOf(value))
  def findAll[B >: A](value: B): Seq[Coordinate] = indexed.collect {
    case (gridValue, index) if gridValue == value => dimensions.toCoordinate(index)
  }

  def mapValues[B](f: A => B): Grid[B] = copy(values = values.map(f))
  def map[B](f: (Coordinate, A) => B): Grid[B] = copy(values =
    indexed.map { (value, index) => f(dimensions.toCoordinate(index), value) }
  )

  def updated[B >: A](coordinate: Coordinate, value: B): Grid[B] = copy(values = values.updated(dimensions.toIndex(coordinate), value))
  def updated[B >: A](coordinate: Coordinate, f: A => B): Grid[B] = {
    val index = dimensions.toIndex(coordinate)
    copy(values = values.updated(index, f(apply(coordinate))))
  }

  def print(stringify: (Coordinate, A) => String = Grid.defaultStringify): Unit = println(toString(stringify))

  override def toString: String = toString(Grid.defaultStringify)
  def toString(stringify: (Coordinate, A) => String): String =
    indexed.sliding(dimensions.width, dimensions.width).map { row =>
      row.map { (value, index) => stringify(dimensions.toCoordinate(index), value) }.mkString(" ")
    }.mkString(System.lineSeparator)

object Grid extends GridBuilder:
  private def defaultStringify[A]: (Coordinate, A) => String = (_, value) => value.toString
