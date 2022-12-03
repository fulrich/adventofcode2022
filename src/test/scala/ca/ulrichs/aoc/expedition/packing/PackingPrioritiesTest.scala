package ca.ulrichs.aoc.expedition.packing

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class PackkingPrioritiesTest extends AnyFunSuite:
  test("Can determine the priority for lower case letters") {
    PackingPriorities.value('a') shouldBe 1
    PackingPriorities.value('z') shouldBe 26
  }

  test("Can determine the priority for upper case letters") {
    PackingPriorities.value('A') shouldBe 27
    PackingPriorities.value('Z') shouldBe 52
  }
