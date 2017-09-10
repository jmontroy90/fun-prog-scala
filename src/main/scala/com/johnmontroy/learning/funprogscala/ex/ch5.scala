package com.johnmontroy.learning.funprogscala.ex

object ch5 {

  // call-by-name versus call-by-value -- strict versus lazy!
  def if2Val[A](cond: Boolean, b1: => A, b2: => A) = if (cond) b1 else b2
  def if2Name[A](cond: Boolean, b1: => A, b2: => A) = if (cond) b1 else b2

  // problems and code for Stream collection
  sealed trait Stream[+A] {
    def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
      case Cons(h,t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }

    // P5.1
    def toList: List[A] = this match {
      case Empty => Nil
      case Cons(h, t) => h() :: t().toList
    }

    // P5.2
    def take(n: Int): Stream[A] = (this, n) match {
      case (_, 0) => Empty
      case (Empty, num) => throw new ArrayIndexOutOfBoundsException
      case (Cons(h,t), num) => Stream.cons(h(), t().take(num - 1))
    }

    // P5.3
    def takeWhile(fn: A => Boolean): Stream[A] = this match {
      case Cons(h,t) if fn(h()) => Stream.cons(h(), t().takeWhile(fn))
      case _ => Empty
    }

    // P5.4
    def forAll(p: A => Boolean): Boolean = this match {
      case Cons(h,t) => p(h()) && t().forAll(p)
      case _ => true
    }

    // P5.5
    def takeWhileFold(fn: A => Boolean): Stream[A] =
      this.foldRight(Stream[A]())((elm, acc) => if (fn(elm)) Stream.cons(elm, acc) else Empty)

  }
  case object Empty extends Stream[Nothing]
  case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

  object Stream {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] =
      if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  }

  // infinite stream
  val ones: Stream[Int] = Stream.cons(1, ones)
}
