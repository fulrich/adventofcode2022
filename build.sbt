val scala3Version = "3.2.1"
val scalacticVersion = "3.2.14"

lazy val root = project
  .in(file("."))
  .settings(
    name := "adventofcode2022",
    version := "1.0.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % scalacticVersion,
      "org.scalatest" %% "scalatest" % scalacticVersion % Test
    )
  )
