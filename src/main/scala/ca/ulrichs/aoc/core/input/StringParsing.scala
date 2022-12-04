package ca.ulrichs.aoc.core.input

object StringParsing:
  extension (input: String)
    def as[A](using parsing: InputParsing[A]): A = parsing.parse(input)
