package ca.ulrichs.aoc.expedition.rochambeau.strategies

import ca.ulrichs.aoc.expedition.rochambeau.*

object Simple extends Strategy:
  def playGame(gameInput: String): Game = Game(
    parseFirstThrow(gameInput.head), 
    parseSecondThrow(gameInput.last)
  )

  def parseFirstThrow(input: Char): Handsign = input match {
      case 'A' => Handsign.Rock
      case 'B' => Handsign.Paper
      case 'C' => Handsign.Scissors
  }

  def parseSecondThrow(input: Char): Handsign = input match {
      case 'X' => Handsign.Rock
      case 'Y' => Handsign.Paper
      case 'Z' => Handsign.Scissors
  }
