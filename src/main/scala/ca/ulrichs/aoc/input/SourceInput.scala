package ca.ulrichs.aoc.input

import scala.io.Source
import scala.annotation.targetName
import java.io.File
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._

case class SourceInput(raw: Seq[String])

object SourceInput:
  val empty: SourceInput = SourceInput()

  @targetName("variable")
  def apply(rawStrings: String*): SourceInput = SourceInput(rawStrings)

  def apply(inputRequest: InputRequest): SourceInput = inputRequest match {
    case ResourceRequest(name) => fromResource(name)
    case FileRequest(file) =>  fromFile(file)
    case NoInput => empty
  }

  def fromResource(name: String): SourceInput =
    SourceInput(Source.fromResource(name).getLines.toVector)

  def fromFile(file: File): SourceInput =
    SourceInput(Files.lines(Paths.get(file.getPath)).iterator.asScala.toVector)
