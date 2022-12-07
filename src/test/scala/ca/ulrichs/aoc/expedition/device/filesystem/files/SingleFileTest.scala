package ca.ulrichs.aoc.expedition.device.filesystem.files

import ca.ulrichs.aoc.expedition.device.filesystem.Path.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class SingleFileTest extends AnyFunSuite:
  test("Can determine if a string is a Single File") {
    SingleFile.isSingleFile.pattern.matcher("14848514 b.txt").matches shouldBe true
  }
