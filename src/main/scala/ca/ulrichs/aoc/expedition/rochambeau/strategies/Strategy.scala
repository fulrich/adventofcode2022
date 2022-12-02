package ca.ulrichs.aoc.expedition.rochambeau.strategies

import ca.ulrichs.aoc.expedition.rochambeau.Game

trait Strategy:
  def playGame(gameInput: String): Game
