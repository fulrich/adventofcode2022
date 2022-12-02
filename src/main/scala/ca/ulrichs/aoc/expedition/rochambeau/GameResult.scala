package ca.ulrichs.aoc.expedition.rochambeau

enum GameResult(val score: Int):
  case Lose   extends GameResult(score = 0)
  case Draw   extends GameResult(score = 3)
  case Win    extends GameResult(score = 6)
