package com.johnmontroy.learning.funprogscala


object ch2 {

  /** E2.1: Write a recursive function to get the nth Fibonacci number */
  /* off-by-one errors are always fun */
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, p1: Int, p2: Int): Int = {
      if (n == 1) p1
      else loop(n - 1, p2, p1 + p2)
    }
    loop(n, 0, 1)
  }

  /** E2.2: Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function */
  /* tail-rec solution, classic out-of-bounds */
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(itr: Int): Boolean = {
      if (!ordered(as(itr), as(itr + 1))) false
      else if (itr + 1 == as.length - 1) true
      else loop(itr + 1)
    }
    loop(0)
  }

  /** E2.3: Let’s look at another example, currying,[9] which converts a function f of two arguments into a function of
    * one argument that partially applies f. Here again there’s only one implementation that compiles. Write this implementation. */
  def curry[A,B,C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a,b)

  /** E2.4: Implement uncurry, which reverses the transformation of curry. Note that since => associates to the
    * right, A => (B => C) can be written as A => B => C. */
  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)

  /** E2.5: Implement the higher-order function that composes two functions. */
  def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

}
