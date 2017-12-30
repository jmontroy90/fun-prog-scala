package com.johnmontroy.learning.cats.ch4

import cats.Eval

/** This function uses Cats Eval to avoid a StackOverflow by trampolining. Behind the scenes, this is simply a TailRec Monad
  * that makes use of tail calls to not consume stack space. It DOES memoize intermediate results using heap space, however -
  * there is no free lunch! */
object Trampolining {
  def factorial(n: BigInt): Eval[BigInt] = {
    if (n == 1) Eval.now(n)
    else Eval.defer(factorial(n - 1).map(_ * n))
  }
}
