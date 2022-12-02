package ca.ulrichs.aoc.expedition.rochambeau.strategies

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.expedition.rochambeau.Handsign.{Paper, Rock, Scissors}

class SimpleTest extends AnyFunSuite:
  test("Can create a game from a game input") {
    val game = Simple.playGame("A Y")

    game.firstPlayerThrow shouldBe Rock
    game.secondPlayerThrow shouldBe Paper 
  }

  test("Can determine the first players Handsign") {
    Simple.parseFirstThrow('A') shouldBe Rock
    Simple.parseFirstThrow('B') shouldBe Paper
    Simple.parseFirstThrow('C') shouldBe Scissors
  }

  test("Can determine the second players Handsign") {
    Simple.parseSecondThrow('X') shouldBe Rock
    Simple.parseSecondThrow('Y') shouldBe Paper
    Simple.parseSecondThrow('Z') shouldBe Scissors
  }
