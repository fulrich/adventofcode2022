package ca.ulrichs.aoc.expedition.device.filesystem.commands

import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.expedition.device.filesystem.FileSystem

import scala.util.matching.Regex

trait Command {
  def run(system: FileSystem): FileSystem
}

object Command:
  val isCommand: Regex = "^\\$ ([A-z/. ]+)".r

  given InputParsing[Command] with
    def parse(input: String): Command = input match {
      case ChangeDirectory.isChangeDirectory(directory) => ChangeDirectory(directory)
      case ListDirectory.isListDirectory() => ListDirectory
      case _ => throw Exception(s"Could not parse te Command: ${input}")
    }
