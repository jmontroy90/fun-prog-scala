package com.johnmontroy.learning.funprogscala.probs

import com.johnmontroy.learning.funprogscala.ex.ch5._


object ch5 {

  /** P5.1: Write a function to convert a Stream to a List, which will force its evaluation
    * and let you look at it in the REPL. You can convert to the regular List type in the standard library.
    * You can place this and other functions that operate on a Stream inside the Stream trait. */


  /** P5.8: Generalize ones slightly to the function constant, which returns an infinite Stream of a given value. */
  def constant[A](a: A): Stream[A] = Stream.cons(a, constant(a))

  /** P5.9: Write a function that generates an infinite stream of integers, starting from n, then n + 1, n + 2, and so on. */
  def from(n: Int): Stream[Int] = Stream.cons(n, from(n + 1))

  /** P5.10: Write a function fibs that generates the infinite stream of Fibonacci numbers: 0, 1, 1, 2, 3, 5, 8, and so on. */
  def fibs(n1: Int = 0, n2: Int = 1): Stream[Int] = Stream.cons(n1, fibs(n2, n1 + n2))

  /** P5.11: Write a more general stream-building function called unfold. It takes an initial state, and a
    * function for producing both the next state and the next value in the generated stream. */
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => Stream.cons(a, unfold(s)(f))
    case None => Empty
  }




}
