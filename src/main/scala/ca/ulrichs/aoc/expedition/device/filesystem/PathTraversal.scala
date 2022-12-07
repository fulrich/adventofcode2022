package ca.ulrichs.aoc.expedition.device.filesystem

case class PathTraversal(path: Path, currentIndex: Int = 0) {
  lazy val current: String = path.segments(currentIndex)

  lazy val isEnd: Boolean = path.segments.length == (currentIndex + 1)

  lazy val down = copy(currentIndex = currentIndex + 1)
  lazy val up = copy(currentIndex = currentIndex - 1)
}
