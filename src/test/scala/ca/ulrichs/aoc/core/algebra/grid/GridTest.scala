package ca.ulrichs.aoc.core.algebra.grid

import ca.ulrichs.aoc.core.algebra.coordinate.Coordinate
import org.scalatest.*
import matchers.should.Matchers.*
import org.scalatest.funsuite.AnyFunSuite

class GridTest extends AnyFunSuite with LoneElement:
  val testInput: Seq[String] = Seq(
    "123",
    "456",
    "789"
  )

  val grid: Grid[Int] = Grid.fromStrings(testInput, default = 0) { (_, value) => value.asDigit }

  test("Can get all the keys of the grid") {
    grid.keys should contain theSameElementsAs Seq(
      Coordinate(0, 0),
      Coordinate(1, 0),
      Coordinate(2, 0),
      Coordinate(0, 1),
      Coordinate(1, 1),
      Coordinate(2, 1),
      Coordinate(0, 2),
      Coordinate(1, 2),
      Coordinate(2, 2)
    )
  }

  test("Can get all the values of the grid") {
    grid.values should contain theSameElementsAs Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  test("Can optionally access values at any coordinate") {
    grid.at(-1, -1) shouldBe None
    grid.at(3, 3) shouldBe None

    grid.at(0, 0) should contain(1)
    grid.at(1, 0) should contain(2)
    grid.at(2, 0) should contain(3)

    grid.at(0, 1) should contain(4)
    grid.at(1, 1) should contain(5)
    grid.at(2, 1) should contain(6)

    grid.at(0, 2) should contain(7)
    grid.at(1, 2) should contain(8)
    grid.at(2, 2) should contain(9)
  }

  test("Can directly access values at any coordinate") {
    grid(0, 0) shouldBe 1
    grid(1, 0) shouldBe 2
    grid(2, 0) shouldBe 3

    grid(0, 1) shouldBe 4
    grid(1, 1) shouldBe 5
    grid(2, 1) shouldBe 6

    grid(0, 2) shouldBe 7
    grid(1, 2) shouldBe 8
    grid(2, 2) shouldBe 9
  }

  test("Throws an exception if directly accessing a coordinate that does not exist on the grid") {
    an [IndexOutOfBoundsException] should be thrownBy grid(3, 3)
  }

  test("Can determine if the grid contains a Coordinate") {
    grid.contains(0, 0) shouldBe true
    grid.contains(1, 1) shouldBe true
    grid.contains(2, 2) shouldBe true

    grid.contains(-1, 0) shouldBe false
    grid.contains(0, -1) shouldBe false
    grid.contains(3, 2) shouldBe false
    grid.contains(2, 3) shouldBe false
  }

  test("Can determine if the grid contains a value") {
    grid.containsValue(1) shouldBe true
    grid.containsValue(9) shouldBe true

    grid.containsValue(-1) shouldBe false
    grid.containsValue(10) shouldBe false
  }

  test("Can find the coordinate of the first instance of a value on the grid") {
    grid.find(5) shouldBe Coordinate(1, 1)
    grid.find(9) shouldBe Coordinate(2, 2)
  }

  test("Throws an exception if using find and cannot find the value on the grid") {
    an [IndexOutOfBoundsException] should be thrownBy grid.find(200)
  }

  test("Can safely find the first all instances on the grid using findAll") {
    val otherStrings = Seq(
      "123",
      "426",
      "782"
    )

    val other = Grid.fromStrings(otherStrings, default = 0) { (_, value) => value.asDigit }

    other.findAll(2) should contain theSameElementsAs Seq(Coordinate(1, 0), Coordinate(1, 1), Coordinate(2, 2))
    other.findAll(4) should contain theSameElementsAs Vector(Coordinate(0, 1))

    other.findAll(5) shouldBe empty
    other.findAll(9) shouldBe empty
  }

  test("Can safely find the first instance on the grid using findOption") {
    grid.findOption(5) should contain(Coordinate(1, 1))
    grid.findOption(9) should contain(Coordinate(2, 2))

    grid.findOption(10) shouldBe None
    grid.findOption(200) shouldBe None
  }

  test("Can map the on the grid") {
    val mapped = grid.map { (coordinate, value) => value + coordinate.x + coordinate.y }

    mapped.at(0, 0) should contain(1)
    mapped.at(1, 0) should contain(3)
    mapped.at(2, 0) should contain(5)

    mapped.at(0, 1) should contain(5)
    mapped.at(1, 1) should contain(7)
    mapped.at(2, 1) should contain(9)

    mapped.at(0, 2) should contain(9)
    mapped.at(1, 2) should contain(11)
    mapped.at(2, 2) should contain(13)
  }

  test("Can map the values of the grid") {
    val mapped = grid.mapValues(_ + 10)

    mapped.at(0, 0) should contain(11)
    mapped.at(1, 0) should contain(12)
    mapped.at(2, 0) should contain(13)

    mapped.at(0, 1) should contain(14)
    mapped.at(1, 1) should contain(15)
    mapped.at(2, 1) should contain(16)

    mapped.at(0, 2) should contain(17)
    mapped.at(1, 2) should contain(18)
    mapped.at(2, 2) should contain(19)
  }

  test("Can update a single value on the grid") {
    val updatedGrid = grid.updated(Coordinate(1, 1), 50)

    updatedGrid.at(1, 1) should contain (50)
  }

  test("Can update a single value on the grid based on the previous value") {
    val updatedGrid = grid.updated(Coordinate(1, 1), _ + 50)

    updatedGrid.at(1, 1) should contain(55)
  }

  test("Can generate a string representation of the grid") {
    grid.toString shouldBe "1 2 3\n4 5 6\n7 8 9"
  }

  test("Can customize to the string representation") {
    val stringify: (Coordinate, Int) => String = (coordinate, value) => value.toString + coordinate.y.toString
    val expected = "10 20 30\n41 51 61\n72 82 92"

    grid.toString(stringify) shouldBe expected
  }