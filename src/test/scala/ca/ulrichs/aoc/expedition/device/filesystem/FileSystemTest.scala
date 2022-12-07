package ca.ulrichs.aoc.expedition.device.filesystem

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class FileSystemTest extends AnyFunSuite:
  val exampleSource = SourceInput(
    "$ cd /",
    "$ ls",
    "dir a",
    "14848514 b.txt",
    "8504156 c.dat",
    "dir d",
    "$ cd a",
    "$ ls",
    "dir e",
    "29116 f",
    "2557 g",
    "62596 h.lst",
    "$ cd e",
    "$ ls",
    "584 i",
    "$ cd ..",
    "$ cd ..",
    "$ cd d",
    "$ ls",
    "4060174 j",
    "8033020 d.log",
    "5626152 d.ext",
    "7214296 k"
  )

  test("Can process an example input of a file system") {
    val system = FileSystem.load(exampleSource)
    system.root.findDirectories(_.size <= 100000).map(_.size).sum shouldBe 95437
  }

  test("Can process a real input") {
    val source = SourceInput.fromResource("day7_file_system")
    val system = FileSystem.load(source)

    system.root.findDirectories(_.size <= 100000).map(_.size).sum shouldBe 1648397
  }

  test("Can find the smallest directory to free up enough space in the example") {
    val system = FileSystem.load(exampleSource)

    val targetAvailableSpace = 30000000
    val requiredToFreeUp = targetAvailableSpace - system.availableSpace

    system.root.findDirectories(_.size >= requiredToFreeUp).map(_.size).min shouldBe 24933642
  }

  test("Can find the smallest directory to free up enough space in the real") {
    val source = SourceInput.fromResource("day7_file_system")
    val system = FileSystem.load(source)

    val targetAvailableSpace = 30000000
    val requiredToFreeUp = targetAvailableSpace - system.availableSpace

    system.root.findDirectories(_.size >= requiredToFreeUp).map(_.size).min shouldBe 1815525
  }
