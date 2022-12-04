package ca.ulrichs.aoc.core.input

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
    def parse(input: String): Range = input.split('-') match {
      case Array(rawStart, rawEnd) => (rawStart.toInt to rawEnd.toInt)
      case _ => throw Exception(s"Could not parse Range from: ${input}")
    }
