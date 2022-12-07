package ca.ulrichs.aoc.expedition.device.filesystem.files

import ca.ulrichs.aoc.expedition.device.filesystem.{Path, PathTraversal}

import scala.util.matching.Regex

case class Directory(name: String, files: Map[String, File]) extends File {
  override val size: Long = files.values.map(_.size).sum

  lazy val directoryMap: Map[String, Directory] = files.collect { case (key, directory: Directory) => key -> directory }
  lazy val directories: Seq[Directory] = directoryMap.values.toVector
  lazy val singleFileMap: Map[String, SingleFile] = files.collect { case (key, file: SingleFile) => key -> file }
  lazy val singleFiles: Seq[SingleFile] = singleFileMap.values.toVector

  def / (directoryName: String): Directory = directoryMap(directoryName)

  def findDirectories(condition: Directory => Boolean): Seq[Directory] =
    (directories.filter(condition) ++ directories.flatMap(_.findDirectories(condition))).distinct

  def add(file: File): Directory = copy(files = files + (file.name -> file))
  def add(path: Path, file: File): Directory = add(PathTraversal(path), file)
  final def add(path: PathTraversal, file: File): Directory = path match {
    case _ if path.current != name => this
    case _ if path.current == name && path.isEnd => add(file)
    case _ => addMissingDirectory(path).transformDirectories(_.add(path.down, file))
  }

  private  def addMissingDirectory(path: PathTraversal): Directory =
    if directoryMap.contains(path.down.current) then this else add(Directory(path.down.current))

  private def transformDirectories(transform: Directory => Directory): Directory =
    copy(files = singleFileMap ++ directoryMap.view.mapValues(transform).toMap)

  def print(spacing: Int = 0): Unit = {
    println(s"${(" " * spacing)} - ${name} (dir)")
    files.values.foreach(_.print(spacing + 2))
  }
}

object Directory:
  val root: Directory = Directory(Path.Root.current)
  val isDirectory: Regex = "^dir ([A-z]+)".r
  def apply(name: String): Directory = Directory(name, Map.empty)
