name := "pawn-tour"

version := "0.1"

scalaVersion := "2.12.7"

enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
)