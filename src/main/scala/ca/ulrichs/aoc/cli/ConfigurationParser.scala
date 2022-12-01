package ca.ulrichs.aoc.cli

import scopt.OParser
import ca.ulrichs.aoc.Configuration
import java.io.File
import ca.ulrichs.aoc.input.{ResourceRequest, FileRequest}

object ConfigurationParser:
  val builder = OParser.builder[Configuration]

  val configurationParser = {
    import builder._

    OParser.sequence(
      programName("aoc"),
      head("Advent of Code", "2022"),
      opt[Int]('d', "day")
        .validate(day => if day >= 1 && day <= 25 then success else failure("Day must be between 1 and 25."))
        .action((day, config) => config.copy(day = day))
        .text("Set the day of the puzzle you want to run"),
      opt[Int]('p', "part")
        .validate(day => if day >= 1 && day <= 2 then success else failure("There are only 2 possible parts."))
        .action((part, config) => config.copy(part = part))
        .text("Set the part of the day of the puzzle you want to run"),
      opt[File]('f', "file")
        .action((file, config) => config.copy(input = FileRequest(file)))
        .text("The input file to use as the input source"),
      opt[String]('r', "resource")
        .action((resource, config) => config.copy(input = ResourceRequest(resource)))
        .text("The resource name to use as the input source"),
    )
  }

  def parse(args: Seq[String]): Option[Configuration] =
    OParser.parse(configurationParser, args, Configuration.Default)
