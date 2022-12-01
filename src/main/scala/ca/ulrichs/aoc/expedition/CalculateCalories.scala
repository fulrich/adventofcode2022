package ca.ulrichs.aoc.expedition

import scala.annotation.tailrec
import ca.ulrichs.aoc.input.SourceInput

object CalculateCalories:
  val ElfSeparator = s"${System.lineSeparator}${System.lineSeparator}"

  def apply(sources: SourceInput): Seq[Elf] =
    sources.asString.split(ElfSeparator).toVector.foldLeft(Seq.empty) { (elves, calories) =>
      elves :+ Elf(calories = calories.split(System.lineSeparator).map(_.toInt).sum)
    }
