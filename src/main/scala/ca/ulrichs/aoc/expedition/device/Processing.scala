package ca.ulrichs.aoc.expedition.device

case class Processing(toProcess: Seq[Char], processed: Seq[Char] = Seq.empty, index: Int = 0) {
  lazy val current: Char = toProcess.head
  lazy val last: Char = processed.last

  def next: Processing = copy(toProcess = toProcess.tail, processed = processed :+ current, index = index + 1)

  def lastProcessedWereUnique(length: Int) = processed.takeRight(length).distinct.length == length
}
