package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.core.input.InputParsing
import ca.ulrichs.aoc.core.input.StringParsing.*
import ca.ulrichs.aoc.expedition.device.distress.DistressSignal.DividerPackets

case class DistressSignal(pairs: Seq[PacketPair]):
  lazy val inOrderIndexes: Seq[Int] = pairs.zipWithIndex.flatMap { (pair, index) =>
    if pair.isCorrectOrder then Some(index + 1) else None
  }

  lazy val allPackets = pairs.flatMap(pair => Seq(pair.left, pair.right))
  lazy val allPacketsWithDividers = allPackets ++ DividerPackets
  lazy val orderedPackets: Seq[Packet] = allPacketsWithDividers.sorted.reverse
  lazy val indexesOfDividers: Seq[Int] = orderedPackets.zipWithIndex.collect { case (pair, index) if DividerPackets.contains(pair) => index + 1 }
  lazy val decoderKey: Int = indexesOfDividers.product

object DistressSignal:
  private val EmptyLineSeparator =s"${System.lineSeparator}${System.lineSeparator}"

  val DividerPackets: Seq[Packet] = Vector(
    "[[2]]".as[Packet],
    "[[6]]".as[Packet]
  )

  given InputParsing[DistressSignal] with
    def parse(input: String): DistressSignal = DistressSignal(input.asSeqUsing[PacketPair](EmptyLineSeparator))
