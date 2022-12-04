package ca.ulrichs.aoc.expedition

import ca.ulrichs.aoc.core.input.SourceInput
import scala.annotation.tailrec

object CalculateCalories:
  val ElfSeparator = s"${System.lineSeparator}${System.lineSeparator}"

  def apply(sources: SourceInput): Seq[Elf] =
    sources.as[String].split(ElfSeparator).toVector.foldLeft(Seq.empty) { (elves, calories) =>
      elves :+ Elf(calories = calories.split(System.lineSeparator).map(_.toInt).sum)
    }
