package ca.ulrichs.aoc.expedition.device.filesystem

import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.expedition.device.filesystem.commands.*

import scala.annotation.targetName

case class Path(segments: Seq[String]) {
  lazy val current = segments.last

  def traverse: PathTraversal = PathTraversal(this)

  def changeDirectory(command: ChangeDirectory): Path = command match {
    case ChangeDirectoryUp => copy(segments = segments.init)
    case ChangeToRoot => Path.Root
    case ChangeTo(directory) => this / directory
  }

  @targetName("addSegment")
  def / (segment: String): Path = copy(segments = segments :+ segment)
}

object Path:
  val Root: Path = Path(Seq("/"))

  extension (startSegment: String)
    @targetName("addSegmentBuilder")
    def / (segment: String): Path = Root / startSegment / segment
