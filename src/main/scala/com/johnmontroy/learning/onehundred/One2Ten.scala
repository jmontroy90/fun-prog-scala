package com.johnmontroy.learning.onehundred

object One2Ten {

  /** P01 (*) Find the last element of a list.
  Example:
    scala> last(List(1, 1, 2, 3, 5, 8))
  res0: Int = 8
    */
  def functionalLast[T <: AnyVal](ts: List[T]): T = {
    ts.foldLeft(List.empty[T])((_, itr) => List(itr)).head
  }

  def last[T <: AnyVal](ts: List[T]): T = ts match {
    case x :: Nil => x
    case _ :: xs => last(xs)
    case _ => throw new Exception("dork")
  }

  /** P02 (*) Find the last but one element of a list.
  Example:
    scala> penultimate(List(1, 1, 2, 3, 5, 8))
  res0: Int = 5
    */
  // dumb but fun functional way
  def functionalPenultimate(ts: List[Int]): Int = {
    ts.foldLeft(List[Int](0))((acc, itr) => List(acc.last, itr)).head
  }

  // silly loop exercise "insta-off-by-one" way
  def loopyPenultimate[T <: AnyVal](ts: List[T]): T = {
    val out = for ((t, count) <- ts.zipWithIndex if count >= ts.length - 2) yield t
    out.head
  }

  // normal pattern match way
  def penultimate[T <: AnyVal](ts: List[T]): T = ts match {
    case x1 :: _ :: Nil => x1
    case _ :: xs => penultimate(xs)
    case _ => throw new Exception("dork")
  }
}
