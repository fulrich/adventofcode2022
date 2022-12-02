package ca.ulrichs.aoc.expedition.rochambeau

import GameResult.*

enum Handsign(val score: Int, vsRock: GameResult, vsPaper: GameResult, vsScissors: GameResult):
  def vs(other: Handsign): GameResult = other match {
      case Rock => vsRock
      case Paper => vsPaper
      case Scissors => vsScissors
  }

  case Rock     extends Handsign(score = 1, vsRock = Draw,  vsPaper = Win, vsScissors = Lose)
  case Paper    extends Handsign(score = 2, vsRock = Lose,  vsPaper = Draw, vsScissors = Win)
  case Scissors extends Handsign(score = 3, vsRock = Win,  vsPaper = Lose, vsScissors = Draw)
