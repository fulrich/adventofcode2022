package ca.ulrichs.aoc.expedition.device.filesystem.commands

import ca.ulrichs.aoc.expedition.device.filesystem.FileSystem

case object ListDirectory extends Command:
  val isListDirectory = "^ls".r

  override def run(system: FileSystem): FileSystem = system
