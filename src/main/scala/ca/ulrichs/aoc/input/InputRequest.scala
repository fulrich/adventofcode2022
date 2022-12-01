package ca.ulrichs.aoc.input

import java.io.File

sealed trait InputRequest
case object NoInput extends InputRequest
case class ResourceRequest(name: String) extends InputRequest
case class FileRequest(file: File) extends InputRequest
