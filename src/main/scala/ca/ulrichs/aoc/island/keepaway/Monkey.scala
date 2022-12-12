package ca.ulrichs.aoc.island.keepaway

case class Monkey(
  id: Int,
  items: Items,
  inspection: Long => Long,
  divisor: Long,
  alleviateWorry: Long => Long,
  trueTarget: Int,
  falseTarget: Int,
  inspections: Long
):
  def catchItem(item: Long): Monkey = copy(items = items.catchItem(item))

  def inspect: Monkey = {
    val tossedItems = items.held.map(inspection).map(alleviateWorry).map(toss)

    copy(
      items = items.clearHeld.addTosses(tossedItems),
      inspections = inspections + items.held.length
    )
  }

  lazy val finishedTossing: Monkey = copy(items = Items.empty)

  private val toss: Long => Toss = item =>
    if item % divisor == 0 then Toss(to = trueTarget, item = item)
    else Toss(to = falseTarget, item = item)

  def alleviateWorryBy(alleviate: Long => Long): Monkey = copy(alleviateWorry = alleviate)

  def print(): Unit =
    println(s"Monkey $id ($inspections): ${items.held.mkString(", ")}")

object Monkey:
  def apply(
    id: Int,
    items: Seq[Long],
    howToInspect: Long => Long,
    divisor: Long,
    trueTarget: Int,
    falseTarget: Int): Monkey = Monkey(
    id = id,
    items = Items(items),
    inspection = howToInspect,
    divisor = divisor,
    alleviateWorry = item => Math.floor(item.toDouble / 3).toLong,
    trueTarget = trueTarget,
    falseTarget = falseTarget,
    inspections = 0
  )
