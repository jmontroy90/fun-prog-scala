package com.johnmontroy.learning.cats.ch4

import cats.syntax.either._ // for asRight

object MyEither extends App {

  val a = 3.asRight[String]
  val b = 4.asRight[String]

  val rightExpr = for {
    x <- a
    y <- b
  } yield x*x + y*y

  println(rightExpr)

  println(a.left, b.left, a.right, b.right)
  println(a.isRight, a.isLeft)

  // we can also use helper methods to catch errors, non-fatals, etc.
  val nfe = Either.catchOnly[NumberFormatException]("foo".toInt)
  val nfe2 = Either.catchOnly[NumberFormatException](3 / 0)
  val nf = Either.catchNonFatal(sys.error("Badness"))
}
