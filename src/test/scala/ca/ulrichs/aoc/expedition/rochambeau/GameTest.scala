package ca.ulrichs.aoc.expedition.rochambeau

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class GameTest extends AnyFunSuite:
  test("Can determine the result and score of a single Rochambeau Game") {
    val game = Game(firstPlayerThrow = Handsign.Paper, secondPlayerThrow = Handsign.Scissors)

    game.result shouldBe GameResult.Win
    game.score shouldBe 9
  }
