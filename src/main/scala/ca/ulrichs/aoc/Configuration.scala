package ca.ulrichs.aoc

import ca.ulrichs.aoc.core.input.{InputRequest, NoInput, SourceInput}

final case class Configuration(
  day: Int,
  part: Int,
  input: InputRequest = NoInput
):
  def source: SourceInput = SourceInput.apply(input)

object Configuration:
  val Default = Configuration(day = 1, part = 1)
