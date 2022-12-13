package ca.ulrichs.aoc.expedition.device.distress

import ca.ulrichs.aoc.expedition.device.distress.PacketParsing.parse

import scala.annotation.tailrec

case class PacketParsing(input: String, packet: ListPacket):
  lazy val section: String = if input.head.isDigit then input.takeWhile(_.isDigit) else input.head.toString
  lazy val remaining: String = input.drop(section.length)

  lazy val next: PacketParsing = next(withRemaining = remaining)
  def next(withRemaining: String): PacketParsing = copy(input = withRemaining)

  def add(parser: PacketParsing): PacketParsing = addToPacket(parser.packet)
  def add(other: Packet): PacketParsing = addToPacket(other)
  def add(other: Int): PacketParsing = addToPacket(NumberPacket(other))

  private def addToPacket(addition: Packet): PacketParsing = copy(packet = packet.copy(raw = packet.raw :+ addition))

object PacketParsing:
  def apply(input: String): PacketParsing = PacketParsing(input, Packet.empty)
  def apply(input: String, parser: PacketParsing): PacketParsing = PacketParsing(input, parser.packet)

  def parse(input: String): Packet = input.head match {
    case '[' => parse(PacketParsing(input.tail)).packet
    case _ => throw IllegalArgumentException("A Packet must start with an opening bracket: '['")
  }

  private def parse(builder: PacketParsing): PacketParsing =
    builder.section match {
      case "[" =>
        val innerList = parse(PacketParsing(builder.remaining))
        parse(builder.add(innerList).next(innerList.remaining))
      case "]" => builder
      case number if number.forall(_.isDigit) => parse(builder.add(number.toInt).next)
      case "," => parse(builder.next)
    }
