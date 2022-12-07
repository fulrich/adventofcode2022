package ca.ulrichs.aoc.expedition.device.filesystem.files

import ca.ulrichs.aoc.expedition.device.filesystem.Path.*
import org.scalatest.LoneElement
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class DirectoryTest extends AnyFunSuite with LoneElement:
  val RootDirectory = Directory("/")

  test("Can determine if a string is a directory") {
    Directory.isDirectory.pattern.matcher("dir dpllhlcv").matches shouldBe true
  }

  test("Can add files and directories to a directory") {
    val firstDirectory = Directory("First")
    val firstFile = SingleFile("File.txt", 55)

    val result = RootDirectory.add(firstFile).add(firstDirectory)

    result.directories.loneElement shouldBe firstDirectory
    result.singleFiles.loneElement shouldBe firstFile
  }

  test("Can add directories at a specific path ") {
    val path = Root / "First" / "Second"
    val directoryStructure = RootDirectory.add(Directory("First")).add(Root / "First", Directory("Second"))
    val result = directoryStructure.add(path, SingleFile("Hello.txt", 2000))

    (result / "First" / "Second").files shouldBe Map("Hello.txt" -> SingleFile("Hello.txt", 2000))
  }

  test("Can add a file at a path and created necessary directories") {
    val directoryStructure = RootDirectory.add("First" / "Second" / "Third", SingleFile("Deeper.txt", 2800))

    (directoryStructure / "First" / "Second" / "Third").files shouldBe Map("Deeper.txt" -> SingleFile("Deeper.txt", 2800))
  }
