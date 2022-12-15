val scala3Version = "3.2.1"
val scalacticVersion = "3.2.14"
val scoptVersion = "4.1.0"

lazy val adventOfCode = project
  .in(file("."))
  .settings(
    name := "adventofcode2022",
    version := "1.0.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % scoptVersion,
      "org.scalactic" %% "scalactic" % scalacticVersion,
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
      "org.scalatest" %% "scalatest" % scalacticVersion % Test
    ),
    scalacOptions ++= Seq("-deprecation", "-feature")
  )
