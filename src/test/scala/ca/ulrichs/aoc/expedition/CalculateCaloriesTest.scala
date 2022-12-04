package ca.ulrichs.aoc.expedition

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class CalculateCaloriesTest extends AnyFunSuite:

  val source = SourceInput(
    "1000",
    "2000",
    "3000",
    "",
    "4000",
    "",
    "5000",
    "6000",
    "",
    "7000",
    "8000",
    "9000",
    "",
    "10000",
    ""
  )

  test("Can determine the calories being carried by the 3 elves carrying the most") {
    val elves = CalculateCalories(SourceInput.fromResource("day1_calories"))
    
    elves.sortBy(_.calories).takeRight(3).map(_.calories).sum shouldBe 211189
  }

  test("Can determine the elf carrying the most calories") {
    val elves = CalculateCalories(SourceInput.fromResource("day1_calories"))

    elves.maxBy(_.calories).calories shouldBe 71471
  }

  test("Can create a list of elves and the calories they are carrying") {
    val elves = CalculateCalories(source)

    elves.maxBy(_.calories).calories shouldBe 24000
  }
