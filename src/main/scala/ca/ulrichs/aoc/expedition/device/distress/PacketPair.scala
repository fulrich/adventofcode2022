package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.core.input.{InputParsing, SourceInput}
import ca.ulrichs.aoc.expedition.CalculateCalories.ElfSeparator
import ca.ulrichs.aoc.core.input.StringParsing.*

case class PacketPair(left: Packet, right: Packet):
  lazy val isCorrectOrder: Boolean = left.compare(right) > 0

object PacketPair:
  given InputParsing[PacketPair] with
    def parse(input: String): PacketPair =
      input.asSeqUsing[Packet](System.lineSeparator) match {
        case Seq(first, second) => PacketPair(first, second)
        case _ => throw IllegalArgumentException(s"Could not parse a set of pairs from: $input")
      }
