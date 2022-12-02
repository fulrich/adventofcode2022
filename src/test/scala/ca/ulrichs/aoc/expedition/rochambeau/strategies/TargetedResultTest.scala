package ca.ulrichs.aoc.expedition.rochambeau.strategies

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.expedition.rochambeau.Handsign.{Paper, Rock, Scissors}
import ca.ulrichs.aoc.expedition.rochambeau.GameResult

class TargetedResultTest extends AnyFunSuite:
  test("Can determine the game to play to ensure it achieves the targeted result") {
    val game = TargetedResult.playGame("A Z")

    game.firstPlayerThrow shouldBe Rock
    game.secondPlayerThrow shouldBe Paper
  }

  test("Can determine the Handsign to throw to achieve the targeted result") {
    TargetedResult.findTargetedThrow(Rock, GameResult.Win) shouldBe Paper
    TargetedResult.findTargetedThrow(Rock, GameResult.Draw) shouldBe Rock
    TargetedResult.findTargetedThrow(Rock, GameResult.Lose) shouldBe Scissors
  }

  test("Can determine the first players Handsign") {
    TargetedResult.parseFirstThrow('A') shouldBe Rock
    TargetedResult.parseFirstThrow('B') shouldBe Paper
    TargetedResult.parseFirstThrow('C') shouldBe Scissors
  }

  test("Can determine the result to target") {
    TargetedResult.parseTargetedResult('X') shouldBe GameResult.Lose
    TargetedResult.parseTargetedResult('Y') shouldBe GameResult.Draw
    TargetedResult.parseTargetedResult('Z') shouldBe GameResult.Win
  }
