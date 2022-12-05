package ca.ulrichs.aoc.core.input

import ca.ulrichs.aoc.core.algebra.{Coordinate, RangeHelpers}

trait InputParsing[A]:
  def parse(input: String): A

object InputParsing:
  given InputParsing[String] with
    def parse(input: String): String = input

  given InputParsing[Int] with
    def parse(input: String): Int = input.toInt

  given InputParsing[Long] with
    def parse(input: String): Long = input.toLong

  given InputParsing[Range] with
    def parse(input: String): Range = RangeHelpers.parse(input)

  given InputParsing[Coordinate] with
    def parse(input: String): Coordinate = Coordinate.parse(input)
