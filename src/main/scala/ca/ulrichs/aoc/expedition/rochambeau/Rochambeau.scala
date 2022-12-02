package ca.ulrichs.aoc.expedition.rochambeau

import ca.ulrichs.aoc.input.SourceInput
import ca.ulrichs.aoc.expedition.rochambeau.strategies.*

case class Rochambeau(source: SourceInput, strategy: Strategy):
  lazy val playAll: Seq[Game] = source.asStringList.map(strategy.playGame)
  lazy val scoreOfAll = playAll.map(_.score).sum

object Rochambeau:
  def simple(source: SourceInput): Rochambeau = Rochambeau(source, Simple)
  def targetedResult(source: SourceInput): Rochambeau = Rochambeau(source, TargetedResult)
