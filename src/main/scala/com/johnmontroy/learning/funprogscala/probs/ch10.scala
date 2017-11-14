package com.johnmontroy.learning.funprogscala.probs

object ch10 {

  // in code, basic definition
  trait Monoid[A] {
    def op(a1: A, a2: A): A
    def zero: A
  }

  // monoid instance for type String
  val stringMonoid = new Monoid[String] {
    def op(a1: String, a2: String) = a1 + a2
    val zero = ""
  }

  // monoid instance for type List[A] (how does this compose with the inner type?)
  def listMonoid[A] = new Monoid[List[A]] {
    def op(a1: List[A], a2: List[A]) = a1 ++ a2
    val zero = Nil
  }

  /** E10.1: Give Monoid instances for integer addition and multiplication as well as the Boolean operators. */
  val intAddition: Monoid[Int] = new Monoid[Int] {
    def op(a1: Int, a2: Int): Int = a1 + a2
    val zero = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    def op(a1: Int, a2: Int): Int = a1 * a2
    val zero = 1
  }

  val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
    def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
    val zero = false
  }

  val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
    def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
    val zero = true
  }

  /** E10.2: Give a Monoid instance for combining Option values. */
  def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    def op(a1: Option[A], a2: Option[A]): Option[A] = a1 orElse a2
    val zero = None
  }

  /** E10.3: A function having the same argument and return type is sometimes called an endofunction.
    * Write a monoid for endofunctions. */
  /* solutions point out that "implementation detail" of composition order is NOT a mistake - Monoids can have multiple
   * meanings to them */
  def endoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
    def op(a1: A => A, a2: A => A): A => A = (a: A) => a1(a2(a)) // more succinctly, a1 compose a2
    val zero = (a: A) => a
  }

  /** E10.5: Implement foldMap. */
  def foldMap[A,B](as: List[A], m: Monoid[B])(f: A => B): B =
    as.foldLeft(m.zero)((acc, itr) => m.op(f(itr), acc))



}
