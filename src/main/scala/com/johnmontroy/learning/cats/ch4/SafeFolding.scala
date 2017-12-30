package com.johnmontroy.learning.cats.ch4

import cats.Eval

/** I'm not sure about how I feel about this. Trampolining is a hella complex solution, and simply wrapping a tail call in
  * in a Eval.Defer doesn't lend itself to very solid understanding. More just machinery. */
object SafeFolding extends App {
  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] = as match {
    case head :: tail => Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
    case Nil => acc
  }

  def foldRight[A,B](as: List[A], acc: B)(fn: (A,B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) => b.map(fn(a, _)) }.value

  val f = foldRight((1 to 100000).toList, 0L)(_ + _)
//  val f2 = foldRightEval((1 to 100000).toList, Eval.later(0L))((a, b) => Eval.defer(b.map(_ + a)))

  println(f)
}
