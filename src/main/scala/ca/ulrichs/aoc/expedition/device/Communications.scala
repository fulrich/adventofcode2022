package ca.ulrichs.aoc.expedition.device

import ca.ulrichs.aoc.core.input.SourceInput
import org.xml.sax.InputSource

import scala.annotation.tailrec

class Communications(input: Seq[Char]) {
  lazy val startOfPacketMarker: Int = findUniqueSequence(size = 4)
  lazy val startOfMessageMarker: Int = findUniqueSequence(size = 14)

  @tailrec
  private final def findUniqueSequence(size: Int, processing: Processing = Processing(input)): Int =
    if (processing.lastProcessedWereUnique(size)) processing.index else findUniqueSequence(size, processing.next)
}

object Communications:
  def apply(input: SourceInput): Communications = new Communications(input.as[String].toSeq)
