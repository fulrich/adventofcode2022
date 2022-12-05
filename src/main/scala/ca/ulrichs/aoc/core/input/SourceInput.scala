package ca.ulrichs.aoc.core.input

import scala.io.Source
import scala.annotation.targetName
import java.io.File
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._
import StringParsing.*

case class SourceInput(private val raw: Seq[String], private val separator: Char) {
  def splitOn(newSeparator: Char): SourceInput = copy(separator = newSeparator)

  def as[A](using parsing: InputParsing[A]): A = raw.mkString(System.lineSeparator).as[A]
  def asSeq[A](using parsing: InputParsing[A]): Seq[A] = raw.map(parsing.parse)
  def asNestedSeq[A](using parsing: InputParsing[A]): Seq[Seq[A]] = raw.map(_.asSeqUsing[A](separator))
}

object SourceInput:
  val DefaultSeparator = ','
  val empty: SourceInput = SourceInput()

  @targetName("variable")
  def apply(rawStrings: String*): SourceInput = SourceInput(rawStrings, DefaultSeparator)

  def fromSeq(rawStrings: Seq[String]): SourceInput = SourceInput(rawStrings, DefaultSeparator)

  def apply(inputRequest: InputRequest): SourceInput = inputRequest match {
    case ResourceRequest(name) => fromResource(name)
    case FileRequest(file) =>  fromFile(file)
    case NoInput => empty
  }

  def fromResource(name: String): SourceInput = fromSeq(Source.fromResource(name).getLines.toVector)

  def fromFile(file: File): SourceInput = fromSeq(Source.fromFile(file).getLines.toVector)
