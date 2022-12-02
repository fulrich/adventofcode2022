package ca.ulrichs.aoc.expedition.rochambeau

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.expedition.rochambeau.Handsign.{Paper, Rock, Scissors}

class HandsignTest extends AnyFunSuite:
  test("Paper can determine the game result of each Handsign") {
    (Paper vs Paper) shouldBe GameResult.Draw
    (Paper vs Rock) shouldBe GameResult.Lose
    (Paper vs Scissors) shouldBe GameResult.Win
  }

  test("Rock can determine the game result of each Handsign") {
    (Rock vs Paper) shouldBe GameResult.Win
    (Rock vs Rock) shouldBe GameResult.Draw
    (Rock vs Scissors) shouldBe GameResult.Lose
  }

  test("Scissors can determine the game result of each Handsign") {
    (Scissors vs Paper) shouldBe GameResult.Lose
    (Scissors vs Rock) shouldBe GameResult.Win
    (Scissors vs Scissors) shouldBe GameResult.Draw
  }
