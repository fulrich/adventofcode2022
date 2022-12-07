package ca.ulrichs.aoc.expedition.device.filesystem

import ca.ulrichs.aoc.core.input.SourceInput
import ca.ulrichs.aoc.expedition.device.filesystem.commands.{ChangeDirectory, Command, ListDirectory}
import ca.ulrichs.aoc.core.input.StringParsing.*
import ca.ulrichs.aoc.expedition.device.filesystem.files.{Directory, File}

case class FileSystem(root: Directory, currentPath: Path) {
  val totalSpace: Long = 70000000
  lazy val usedSpace: Long = root.size
  lazy val availableSpace = totalSpace - usedSpace

  def run(output: String): FileSystem = output match {
    case Command.isCommand(rawCommand) => rawCommand.as[Command].run(this)
    case  _ => copy(root = root.add(currentPath, output.as[File]))
  }
}

object FileSystem:
  val empty: FileSystem = FileSystem(Directory.root, Path.Root)

  def load(input: SourceInput): FileSystem = input.asSeq[String].foldLeft(empty) { (system, input) =>
    system.run(input)
  }
