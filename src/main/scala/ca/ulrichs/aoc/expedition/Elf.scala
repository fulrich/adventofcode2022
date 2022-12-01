package ca.ulrichs.aoc.expedition

import scala.annotation.newMain

case class Elf(calories: Int = 0) {
  def addCalories(newCalories: Int): Elf = copy(calories = calories + newCalories)
}
