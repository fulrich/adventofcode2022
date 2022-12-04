package ca.ulrichs.aoc.core.input

import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite
import ca.ulrichs.aoc.FileTesting
import ca.ulrichs.aoc.core.input.{FileRequest, ResourceRequest, SourceInput}

class SourceInputTest extends AnyFunSuite with FileTesting:
  val test_resource_source = Vector(
    "Testing Resources!",
    "Multiline Output!"
  )

  test("Can create a puzzle input from a list of strings") {
    val rawStrings = Vector("1", "2", "3")

    SourceInput(rawStrings).asSeq[String] shouldBe rawStrings
  }

  test("Can create a puzzle input from a variable list of strings") {
    SourceInput("1", "2", "3").asSeq[String] shouldBe Vector("1", "2", "3")
  }

  test("Can create a puzzle input from a ResourceRequest") {
    val resourceReqeust = ResourceRequest("test_resource")

    SourceInput(resourceReqeust).asSeq[String] shouldBe test_resource_source
  }

  test("Can create a puzzle input from FileRequest") {
    withFile(test_resource_source) { file =>
      val fileRequest = FileRequest(file)

      SourceInput(fileRequest).asSeq[String] shouldBe test_resource_source
    }
  }
