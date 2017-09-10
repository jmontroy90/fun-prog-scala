package com.johnmontroy.learning.funprogscala.probs

object ch4 {

  case class Some[+A](get: A) extends Option[A]
  case object None extends Option[Nothing]

  /** E4.1: Implement all of the preceding functions on Option. As you implement each function, try to think about what
    * it means and in what situations you’d use it. We’ll explore when to use each of these functions next.
    * Here are a few hints for solving this exercise:
    *
    * It’s fine to use pattern matching, though you should be able to implement all the functions besides map and getOrElse without
    * resorting to pattern matching.
    *
    * For map and flatMap, the type signature should be enough to determine the implementation.
    *
    * getOrElse returns the result inside the Some case of the Option, or if the Option is None, returns the given default value.
    *
    * orElse returns the first Option if it’s defined; otherwise, it returns the second Option.
    */

  trait Option[+A] {
    def map[B](f: A => B): Option[B] = this match {
      case None => None
      case Some(a) => Some(f(a))
    }

    def getOrElse[B >: A](default: => B): B = this match {
      case Some(a) => a
      case None => default
    }

    def flatMap[B](f: A => Option[B]): Option[B] = (this map f).getOrElse(None)

    def orElse[B >: A](ob: => Option[B]): Option[B] = Some(this) getOrElse ob // this is weird

    def filter(f: A => Boolean): Option[A] = this flatMap(v => if (f(v)) Some(v) else None)
  }

  /* for problem 4.2 */
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  /** E4.2: Implement the variance function in terms of flatMap. If the mean of a sequence is m,
    * the variance is the mean of math.pow(x - m, 2) for each element x in the sequence.
    */
  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs).flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))

  /** E4.3: Write a generic function map2 that combines two Option values using a binary function. If either
    * Option value is None, then the return value is too. */
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    // a.flatMap(aval => b.map(bval => f(aval,bval)))
    for {
      aval <- a
      bval <- b
    } yield f(aval,bval)
  }

  /** E4.4: Write a function sequence that combines a list of Options into one Option containing a list of all the
    * Some values in the original list. If the original list contains None even once, the result of the function
    * should be None; otherwise the result should be Some with a list of all the values.
    */
  def sequence[A](a: List[Option[A]]): Option[List[A]] = ???


}
