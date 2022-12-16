package ca.ulrichs.aoc.island.bridge

import ca.ulrichs.aoc.core.algebra.coordinate.{Coordinate, Direction}
import ca.ulrichs.aoc.core.input.InputParsing

import java.lang
import scala.annotation.tailrec
import scala.util.matching.Regex

case class Rope(knots: Seq[Coordinate], movingKnots: Seq[Coordinate] = Seq.empty, tailTouched: Seq[Coordinate]):
  def move(direction: Direction): Rope = {
    val result = follow(
      copy(
        knots = Seq(direction.move(knots.head)),
        movingKnots = knots.tail
      )
    )
//    println(s"HEAD: ${knots.head} TAIL: ${knots.tail}")
    result
  }

  @tailrec
  private final def follow(rope: Rope): Rope =
    (rope.knots.lastOption, rope.movingKnots.headOption) match {
      case (Some(first), Some(second)) => follow(rope.copy(knots = rope.knots :+ follow(first, second), movingKnots = rope.movingKnots.tail))
      case (Some(tail), None) => rope.copy(tailTouched = (tailTouched :+ tail).distinct)
      case (_, _) => throw IllegalArgumentException("Rope moved in an unexpected way")
    }

  private def follow(first: Coordinate, second:Coordinate): Coordinate = {
    if first.surrounding.contains(second) then
      second
    else
      second.slope(first).minimal.move(second)
  }

object Rope:
  def apply(knots: Int): Rope = Rope(Vector.tabulate(knots) { _ => Coordinate.origin}, tailTouched = Seq(Coordinate.origin))
  def at(coordinate: Coordinate): Rope = Rope(Vector(coordinate, coordinate), tailTouched = Seq(coordinate))

  val directionRegex: Regex = "([RLUD]+) ([0-9]+)".r

  def parseDirections(input: String): Seq[Direction] = input match {
    case directionRegex(direction, amount) => Vector.tabulate(amount.toInt) { _ => parseDirection(direction) }
    case _ => throw IllegalArgumentException(s"Could not parse direction ${input}")
  }

  def parseDirection(direction: String): Direction = direction match {
    case "R" => Direction.Right
    case "L" => Direction.Left
    case "U" => Direction.Up
    case "D" => Direction.Down
  }

