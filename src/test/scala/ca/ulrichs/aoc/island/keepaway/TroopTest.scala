package ca.ulrichs.aoc.island.keepaway

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class TroopTest extends AnyFunSuite with TroopFixtures:
  test("Determine monkey business for the example troop after 20 rounds") {
    exampleTroop.untilRound(20).monkeyBusiness shouldBe 10605
  }

  test("Determine monkey business for the real troop after 20 rounds") {
    realTroop.untilRound(20).monkeyBusiness shouldBe 110888
  }

  test("Determine monkey business for the example troop after 10000 rounds") {
    exampleTroop.alleviateByGcd.untilRound(10000).monkeyBusiness shouldBe 2713310158L
  }

  test("Determine monkey business for the real troop after 10000 rounds") {
    realTroop.alleviateByGcd.untilRound(10000).monkeyBusiness shouldBe 25590400731L
  }
