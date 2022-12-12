package ca.ulrichs.aoc.island.keepaway

import scala.collection.immutable.Vector

trait TroopFixtures {
  val exampleTroop: Troop = Troop(Vector(
    Monkey(
      id = 0,
      items = Vector(79, 98),
      howToInspect = worryLevel => worryLevel * 19,
      divisor = 23,
      trueTarget = 2,
      falseTarget = 3
    ),
    Monkey(
      id = 1,
      items = Vector(54, 65, 75, 74),
      howToInspect = worryLevel => worryLevel + 6,
      divisor = 19,
      trueTarget = 2,
      falseTarget = 0
    ),
    Monkey(
      id = 2,
      items = Vector(79, 60, 97),
      howToInspect = worryLevel => worryLevel * worryLevel,
      divisor = 13,
      trueTarget = 1,
      falseTarget = 3
    ),
    Monkey(
      id = 3,
      items = Vector(74),
      howToInspect = worryLevel => worryLevel + 3,
      divisor = 17,
      trueTarget = 0,
      falseTarget = 1
    )
  ))

  val realTroop: Troop = Troop(Vector(
    Monkey(
      id = 0,
      items = Vector(54, 89, 94),
      howToInspect = worryLevel => worryLevel * 7,
      divisor = 17,
      trueTarget = 5,
      falseTarget = 3
    ),
    Monkey(
      id = 1,
      items = Vector(66, 71),
      howToInspect = worryLevel => worryLevel + 4,
      divisor = 3,
      trueTarget = 0,
      falseTarget = 3
    ),
    Monkey(
      id = 2,
      items = Vector(76, 55, 80, 55, 55, 96, 78),
      howToInspect = worryLevel => worryLevel + 2,
      divisor = 5,
      trueTarget = 7,
      falseTarget = 4
    ),
    Monkey(
      id = 3,
      items = Vector(93, 69, 76, 66, 89, 54, 59, 94),
      howToInspect = worryLevel => worryLevel + 7,
      divisor = 7,
      trueTarget = 5,
      falseTarget = 2
    ),
    Monkey(
      id = 4,
      items = Vector(80, 54, 58, 75, 99),
      howToInspect = worryLevel => worryLevel * 17,
      divisor = 11,
      trueTarget = 1,
      falseTarget = 6
    ),
    Monkey(
      id = 5,
      items = Vector(69, 70, 85, 83),
      howToInspect = worryLevel => worryLevel + 8,
      divisor = 19,
      trueTarget = 2,
      falseTarget = 7
    ),
    Monkey(
      id = 6,
      items = Vector(89),
      howToInspect = worryLevel => worryLevel + 6,
      divisor = 2,
      trueTarget = 0,
      falseTarget = 1
    ),
    Monkey(
      id = 7,
      items = Vector(62, 80, 58, 57, 93, 56),
      howToInspect = worryLevel => worryLevel * worryLevel,
      divisor = 13,
      trueTarget = 6,
      falseTarget = 4
    ),
  ))
}
