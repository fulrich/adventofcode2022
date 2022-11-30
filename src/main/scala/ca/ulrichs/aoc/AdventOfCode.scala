package ca.ulrichs.aoc

object AdventOfCode:
  @main
  def main(args: String*): Unit = {
    val config = cli.ConfigurationParser.parse(args)

    config.foreach { validConfig =>
      println(s"Day: ${validConfig.day} Part: ${validConfig.part}")
    }
  }
