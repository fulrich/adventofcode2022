package ca.ulrichs.aoc.expedition.rochambeau.strategies

import ca.ulrichs.aoc.expedition.rochambeau.*

object TargetedResult extends Strategy:
  def playGame(gameInput: String): Game = {
    val firstThrow = parseFirstThrow(gameInput.head)
    val targetResult = parseTargetedResult(gameInput.last)
    
    Game(
      firstThrow, 
      findTargetedThrow(firstThrow, targetResult)
    )
  }

  def parseFirstThrow(input: Char): Handsign = input match {
      case 'A' => Handsign.Rock
      case 'B' => Handsign.Paper
      case 'C' => Handsign.Scissors
  }

  def parseTargetedResult(input: Char): GameResult = input match {
      case 'X' => GameResult.Lose
      case 'Y' => GameResult.Draw
      case 'Z' => GameResult.Win
  }

  def findTargetedThrow(firstThrow: Handsign, targetResult: GameResult): Handsign =
    Handsign.values.find(possibleThrow => (firstThrow vs possibleThrow) == targetResult).get
