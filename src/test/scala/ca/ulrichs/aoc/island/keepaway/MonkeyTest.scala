package ca.ulrichs.aoc.island.keepaway

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class MonkeyTest extends AnyFunSuite:
  val testMonkey: Monkey = Monkey(
    id = 1,
    items = Vector(79, 98),
    howToInspect = worryLevel => worryLevel * 19,
    divisor = 23,
    trueTarget = 2,
    falseTarget = 3
  )

  test("Can determine what the test monkey will do when it inspects the items it is holding") {
    val tossingMonkey = testMonkey.inspect

    tossingMonkey.items.tossing should contain theSameElementsAs Vector(
      Toss(to = 3, 500),
      Toss(to = 3, 620)
    )
  }
