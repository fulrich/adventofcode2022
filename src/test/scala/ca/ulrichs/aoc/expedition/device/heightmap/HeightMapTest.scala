package ca.ulrichs.aoc.expedition.device.heightmap

import ca.ulrichs.aoc.core.input.SourceInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class HeightMapTest extends AnyFunSuite:
  val exampleSource: SourceInput = SourceInput(
    "Sabqponm",
    "abcryxxl",
    "accszExk",
    "acctuvwj",
    "abdefghi"
  )

  test("Can determine the length of the shortest path for the example source") {
    val heightMap = HeightMap(exampleSource)

    heightMap.fastestRouteLength shouldBe 31
  }

  test("Can determine the length of the shortest path for the real source") {
    val heightMap = HeightMap(SourceInput.fromResource("day12_height_map"))

    heightMap.fastestRouteLength shouldBe 517
  }

  ignore("Can determine the best hiking trail") {
    val heightMap = HeightMap(SourceInput.fromResource("day12_height_map"))

    val lowestPoints = heightMap.grid.filter(_._2 == 'a').keys
    val scenicRouteLength = lowestPoints.map(heightMap.fastestRouteFrom).min

    scenicRouteLength shouldBe 512
  }
