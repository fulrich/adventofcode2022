package ca.ulrichs.aoc

import java.io.{ File, PrintWriter }

trait FileTesting:
  def withFile(test: File => Unit): Unit = {
    withFile("Default Contents")(test)
  }

  def withFile(contents: Seq[String])(test: File => Unit): Unit = {
    withFile(contents.mkString(sys.props("line.separator")))(test)
  }

  def withFile(contents: String)(test: File => Unit): Unit = {
    val tempFile = File.createTempFile("test-", ".txt")
    tempFile.deleteOnExit()

    new PrintWriter(tempFile) {
      try {
        write(contents)
      } finally {
        close()
      }
    }

    try {
      test(tempFile)
    } finally {
      tempFile.delete
    }
  }
