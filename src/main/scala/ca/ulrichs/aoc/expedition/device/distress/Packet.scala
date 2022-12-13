package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.core.input.InputParsing
import com.sun.tools.jdi.Packet

import scala.annotation.tailrec
import scala.util.matching.Regex

sealed trait Packet:
  def compare(right: Packet)(using ordering: Ordering[Packet]): Int = ordering.compare(this, right)

case class ListPacket(raw: Seq[Packet] = Vector.empty) extends Packet:
  lazy val head: Option[Packet] = raw.headOption
  lazy val tail: ListPacket = copy(raw = raw.tail)
  override def toString: String = s"[${raw.map(_.toString).mkString(",")}]"

case class NumberPacket(raw: Int) extends Packet:
  lazy val asListPacket: ListPacket = ListPacket(Vector(this))
  override def toString: String = raw.toString


object Packet:
  val empty: ListPacket = ListPacket(Vector.empty)

  given InputParsing[Packet] with
    def parse(input: String): Packet = PacketParsing.parse(input)

  given Ordering[Packet] with
    @tailrec
    override final def compare(leftPacket: Packet, rightPacket: Packet): Int = (leftPacket, rightPacket) match {
      case (NumberPacket(left), NumberPacket(right)) => right - left
      case (left: ListPacket, right: ListPacket) => compareList(left, right)
      case (left: NumberPacket, right: ListPacket) => compare(left.asListPacket, right)
      case (left: ListPacket, right: NumberPacket) => compare(left, right.asListPacket)
    }

    final def compareList(leftList: ListPacket, rightList: ListPacket): Int = (leftList.head, rightList.head) match {
      case (Some(left), Some(right)) =>
        val comparison = compare(left, right)
        if comparison == 0 then compare(leftList.tail, rightList.tail) else comparison
      case (Some(_), None) => -1
      case (None, Some(_)) => 1
      case (None, None) => 0
    }
