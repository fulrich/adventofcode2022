package ca.ulrichs.aoc.expedition.device.filesystem.commands

import ca.ulrichs.aoc.expedition.device.filesystem.{FileSystem, Path}

import scala.util.matching.Regex

sealed trait ChangeDirectory extends Command {
  override def run(system: FileSystem): FileSystem =
    system.copy(currentPath = system.currentPath.changeDirectory(this))
}

case object ChangeToRoot extends ChangeDirectory
case object ChangeDirectoryUp extends ChangeDirectory
case class ChangeTo(directory: String) extends ChangeDirectory

object ChangeDirectory:
  val isChangeDirectory: Regex = "cd ([A-z/.]+)".r

  def apply(directory: String): ChangeDirectory = directory.trim match {
    case "/" => ChangeToRoot
    case ".." => ChangeDirectoryUp
    case other => ChangeTo(other)
  }
