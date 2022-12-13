package ca.ulrichs.aoc.core.input

object StringParsing:
  extension (input: String)
    def as[A](using parsing: InputParsing[A]): A = parsing.parse(input)
    def asSeq[A](using parsing: InputParsing[A]): Seq[A] = asSeqUsing[A](',')
    def asSeqUsing[A](separator: Char)(using parsing: InputParsing[A]): Seq[A] = asSeqUsing[A](separator.toString)
    def asSeqUsing[A](separator: String)(using parsing: InputParsing[A]): Seq[A] =
      input.split(separator).toVector.map(parsing.parse)
