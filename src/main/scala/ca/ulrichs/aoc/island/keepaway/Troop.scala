package ca.ulrichs.aoc.island.keepaway

import scala.annotation.tailrec

case class Troop(monkeys: Map[Int, Monkey]):
  lazy val monkeyBusiness: Long = monkeys.values.toVector.map(_.inspections).sorted.takeRight(2).product
  lazy val nextRound: Troop = nextRound(0, this)
  lazy val alleviateByGcd: Troop = {
    val gcd = monkeys.values.map(_.divisor).product
    val alleviateWithGcd: Long => Long = worry => worry % gcd

    copy(monkeys = monkeys.view.mapValues(_.alleviateWorryBy(alleviateWithGcd)).toMap)
  }

  @tailrec
  final def untilRound(index: Int, troop: Troop = this): Troop =
    if index == 0 then troop else untilRound(index - 1, troop.nextRound)

  @tailrec
  private final def nextRound(currentMonkey: Int, troop: Troop): Troop = troop.monkeys.get(currentMonkey) match {
    case Some(monkey) => nextRound(currentMonkey + 1, monkeyTurn(monkey, troop))
    case None => troop
  }

  private def monkeyTurn(monkey: Monkey, currentTroop: Troop): Troop = {
    val inspectedMonkey = monkey.inspect

    inspectedMonkey.items.tossing
      .foldLeft(currentTroop) { (troop, toss) => troop.catchToss(toss) }
      .setMonkey(inspectedMonkey.finishedTossing)
  }

  private def catchToss(toss: Toss): Troop = updateMonkey(toss.to)(_.catchItem(toss.item))
  private def setMonkey(monkey: Monkey): Troop = updateMonkey(monkey.id)(_ => monkey)
  private def updateMonkey(index: Int)(update: Monkey => Monkey): Troop = copy(monkeys = monkeys.updatedWith(index) {
    case Some(monkey) => Some(update(monkey))
    case None => throw IllegalArgumentException("Threw item to unknown monkey.")
  })

  def print(): Unit = monkeys.values.toVector.sortBy(_.id).foreach(_.print())

object Troop:
  def apply(monkeys: Seq[Monkey]): Troop = Troop(monkeys.map { monkey => monkey.id -> monkey}.toMap)
