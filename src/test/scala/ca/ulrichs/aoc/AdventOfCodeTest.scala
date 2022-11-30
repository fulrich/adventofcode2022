package ca.ulrichs.aoc

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite.AnyFunSuite

class AdventOfCodeTest extends AnyFunSuite:
  test("Can provide a resource input to determine the resource file to use") {
    val stream = new java.io.ByteArrayOutputStream()

    Console.withOut(stream) {
      AdventOfCode.main("-d", "20", "-p", "2")
    }

    stream.toString should include ("Day: 20 Part: 2")
  }
