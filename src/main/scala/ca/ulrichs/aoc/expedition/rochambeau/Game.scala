package ca.ulrichs.aoc.expedition.rochambeau

case class Game(firstPlayerThrow: Handsign, secondPlayerThrow: Handsign):
  lazy val result: GameResult = firstPlayerThrow vs secondPlayerThrow
  lazy val score: Int = result.score + secondPlayerThrow.score
