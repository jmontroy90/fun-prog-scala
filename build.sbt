name := "scala-learning"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(Libraries.junit, Libraries.scalatest, Libraries.cats)

scalacOptions += "-Ypartial-unification"