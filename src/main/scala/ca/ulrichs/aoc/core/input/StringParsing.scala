package ca.ulrichs.aoc.core.input

object StringParsing:
    def as[A](input: String)(using parsing: InputParsing[A]): A = parsing.parse(input)

    def asSeq[A](input: String)(using parsing: InputParsing[A]): Seq[A] = asSeqUsing[A](input, ',')

    def asSeqUsing[A](input: String, separator: Char)(using parsing: InputParsing[A]): Seq[A] =
      asSeqUsing[A](input, separator.toString)

    def asSeqUsing[A](input: String, separator: String)(using parsing: InputParsing[A]): Seq[A] =
      input.split(separator).toVector.map(parsing.parse)
