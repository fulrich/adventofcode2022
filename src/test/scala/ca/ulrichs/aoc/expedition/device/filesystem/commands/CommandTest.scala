package ca.ulrichs.aoc.expedition.device.filesystem.commands

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class CommandTest extends AnyFunSuite:
  test("Can determine if a string is a command") {
    Command.isCommand.pattern.matcher("$ cd another").matches shouldBe true
    Command.isCommand.pattern.matcher("$ cd ..").matches shouldBe true
    Command.isCommand.pattern.matcher("$ ls").matches shouldBe true
  }
