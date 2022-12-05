package ca.ulrichs.aoc.expedition.ship

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class ShipTest extends AnyFunSuite:
  val testShip = Ship(containers = Map(
    1 -> Seq('Z', 'N'),
    2 -> Seq('M', 'C', 'D'),
    3 -> Seq('P')
  ))

  val testInstructions = SourceInput(
    "move 1 from 2 to 1",
    "move 3 from 1 to 3",
    "move 2 from 2 to 1",
    "move 1 from 1 to 2"
  )

  val realShip = Ship(containers = Map(
    1 -> Seq('W', 'M', 'L', 'F'),
    2 -> Seq('B', 'Z', 'V', 'M', 'F'),
    3 -> Seq('H', 'V', 'R', 'S', 'L', 'Q'),
    4 -> Seq('F', 'S', 'V',  'Q', 'P',  'M', 'T', 'J'),
    5 -> Seq('L', 'S', 'W'),
    6 -> Seq('F', 'V', 'P', 'M', 'R', 'J', 'W'),
    7 -> Seq('J', 'Q', 'C', 'P', 'N', 'R', 'F'),
    8 -> Seq('V', 'H', 'P', 'S', 'Z',  'W', 'R', 'B'),
    9 -> Seq('B', 'M', 'J', 'C', 'G',  'H', 'Z', 'W')
  ))

  test("Can determine the top level of each stack of containers") {
    testShip.topCrates shouldBe Seq('N', 'D', 'P')
  }

  test("Can move a crate from one stack to another stack") {
    testShip.move("move 1 from 2 to 3").topCrates shouldBe Seq('N', 'C', 'D')
  }

  test("Can move multiple crates from one stack to another stack") {
    testShip.move("move 2 from 2 to 3").topCrates shouldBe Seq('N', 'M', 'C')
  }

  test("Can move the test ship according to the test instructions") {
    testShip.move(testInstructions.asSeq[CraneInstruction]).topCrates.mkString shouldBe "CMZ"
  }

  test("Can move the test ship according to the test instructions and the 9001 ruleset") {
    testShip.using(NineThousandAndOne).move(testInstructions.asSeq[CraneInstruction]).topCrates.mkString shouldBe "MCD"
  }

  test("Can move the real ship according to the crane instructions") {
    val source = SourceInput.fromResource("day5_crane_instructions")

    realShip.move(source.asSeq[CraneInstruction]).topCrates.mkString shouldBe "VRWBSFZWM"
  }

  test("Can move the real ship according to the crane instructions and the 9001 rule set") {
    val source = SourceInput.fromResource("day5_crane_instructions")

    realShip.using(NineThousandAndOne).move(source.asSeq[CraneInstruction]).topCrates.mkString shouldBe "RBTWJWMCF"
  }
