import sbt._

object V {
  val junit = "4.12"
  val scalatest = "3.0.1"
}

object Libraries {
  val junit = "junit" % "junit" % V.junit % "test"
  val scalatest = "org.scalatest" %% "scalatest" % V.scalatest % "test"
  val cats = "org.typelevel" %% "cats-core" % "1.0.0-RC2"
}
