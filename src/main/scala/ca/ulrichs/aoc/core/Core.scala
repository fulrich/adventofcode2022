package ca.ulrichs.aoc.core

import ca.ulrichs.aoc.core.input.StringParsing

type InputParsing[A] = input.InputParsing[A]

type SourceInput = input.SourceInput
val SourceInput = input.SourceInput

type Coordinate = algebra.coordinate.Coordinate
val Coordinate = algebra.coordinate.Coordinate

type Grid[+A] = algebra.grid.Grid[A]
val Grid = algebra.grid.GridBuilder

extension (input: String)
  def as[A : InputParsing]: A = StringParsing.as[A](input)
  def asSeq[A : InputParsing]: Seq[A] = StringParsing.asSeqUsing[A](input, ',')
  def asSeqUsing[A : InputParsing](separator: Char): Seq[A] = StringParsing.asSeqUsing[A](input, separator.toString)
  def asSeqUsing[A : InputParsing](separator: String): Seq[A] = StringParsing.asSeqUsing(input, separator)
