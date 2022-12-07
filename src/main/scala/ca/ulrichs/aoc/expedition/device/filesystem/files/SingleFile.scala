package ca.ulrichs.aoc.expedition.device.filesystem.files

import scala.util.matching.Regex

case class SingleFile(name: String, size: Long) extends File:
  override def print(spacing: Int): Unit = println(s"${(" " * spacing)} - ${name} (file, size=${size})")

object SingleFile:
  val isSingleFile: Regex = "([0-9]+) ([A-z0-9.]+)".r
