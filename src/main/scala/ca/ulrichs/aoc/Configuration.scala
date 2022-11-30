package ca.ulrichs.aoc

final case class Configuration(
  day: Int,
  part: Int
)

object Configuration:
  val Default = Configuration(day = 1, part = 1)
