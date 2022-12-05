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

  test("Can parse a source input into a single value") {
    SourceInput("4").as[Int] shouldBe 4
    SourceInput("Hi", "Hello").as[String] shouldBe "Hi\nHello"
  }

  test("Can parse a source input into a sequence of values") {
    SourceInput("4", "8", "12").asSeq[Int] shouldBe Seq(4, 8, 12)
    SourceInput("Hi", "Hello").asSeq[String] shouldBe Seq("Hi", "Hello")
  }

  test("Can parse a source input into a nested sequence of values") {
    SourceInput("4,6", "8,10").asNestedSeq[Int] shouldBe Seq(Seq(4, 6), Seq(8, 10))
    SourceInput("Hi-Ho", "Hey-Hoi").splitOn('-').asNestedSeq[String] shouldBe Seq(Seq("Hi", "Ho"), Seq("Hey", "Hoi"))
  }

  test("Can create a source input from a list of strings") {
    val rawStrings = Vector("1", "2", "3")

    SourceInput.fromSeq(rawStrings).asSeq[String] shouldBe rawStrings
  }

  test("Can create a source input from a variable list of strings") {
    SourceInput("1", "2", "3").asSeq[String] shouldBe Vector("1", "2", "3")
  }

  test("Can create a source input from a ResourceRequest") {
    val resourceRequest = ResourceRequest("test_resource")

    SourceInput(resourceRequest).asSeq[String] shouldBe test_resource_source
  }

  test("Can create a source input from FileRequest") {
    withFile(test_resource_source) { file =>
      val fileRequest = FileRequest(file)

      SourceInput(fileRequest).asSeq[String] shouldBe test_resource_source
    }
  }
