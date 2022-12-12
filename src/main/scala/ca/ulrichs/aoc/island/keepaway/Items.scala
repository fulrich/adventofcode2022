package ca.ulrichs.aoc.island.keepaway

case class Items(held: Seq[Long], tossing: Seq[Toss] = Vector.empty):
  lazy val clearHeld: Items = copy(held = Vector.empty)

  def addToss(newToss: Toss): Items = copy(tossing = tossing :+ newToss)
  def addTosses(newTosses: Seq[Toss]): Items = copy(tossing = tossing ++ newTosses)

  def catchItem(newItem: Long): Items = copy(held = held :+ newItem)
  def catchItems(newItems: Seq[Long]): Items = copy(held = held ++ newItems)

object Items:
  val empty: Items = Items(Vector.empty, Vector.empty)
