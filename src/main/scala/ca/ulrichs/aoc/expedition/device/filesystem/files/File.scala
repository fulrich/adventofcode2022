package ca.ulrichs.aoc.expedition.device.filesystem.files

import ca.ulrichs.aoc.core.input.InputParsing

trait File:
  def name: String
  def size: Long
  def print(spacing: Int): Unit

object File:
  given InputParsing[File] with
    def parse(input: String): File = input match {
      case Directory.isDirectory(name) => Directory(name)
      case SingleFile.isSingleFile(size, name) => SingleFile(name, size.toLong)
      case _ => throw Exception(s"Could not parse the File: $input")
    }
