package com.johnmontroy.learning.cats.ch4

import cats.Eval

object MyEval extends App {
  val now = Eval.now(math.random + 1000)
  val later = Eval.later(math.random + 2000)
  val always = Eval.always(math.random + 3000)

  println(now.value, now.value)
  println(later.value, later.value)
  println(always.value, always.value)
}
