package com.johnmontroy.learning.funprogscala

object ch3 {


  /** E3.1: What will be the result of the following match expression? */
  /* actual exercise is to convert into non-Cons syntax, but the answer is 3 */
  List(1,2,3,4,5) match {
    case x :: 2 :: 4 :: _ => x
    case Nil => 42
    case x :: y :: 3 :: 4 :: _ => x + y
    case h :: t => h + t.sum
    case _ => 101
  }

  /** E3.2: Implement the function tail for removing the first element of a List. Note that the function takes
    * constant time. What are different choices you could make in your implementation if the List is Nil? We’ll
    * return to this question in the next chapter. */
  def tail[T](l: List[T]): List[T] = l match {
    case Nil => throw new UnsupportedOperationException
    case _ :: xs => xs
  }

  /** E3.3: Using the same idea, implement the function setHead for replacing the first element of a List
    * with a different value. */
  def setHead[T](l: List[T], elm: T): List[T] = l match {
    case Nil => throw new UnsupportedOperationException
    case _ :: xs => elm :: xs
  }

  /** E3.4: Generalize tail to the function drop, which removes the first n elements from a list. Note that this function
    * takes time proportional only to the number of elements being dropped—we don’t need to make a copy of the entire List. */
  def drop[A](l: List[A], n: Int): List[A] = (l, n) match {
    case (x :: xs, 0) => x :: xs
    case (_ :: xs, i) => drop(xs, i - 1)
  }

  /** E3.5: Implement dropWhile, which removes elements from the List prefix as long as they match a predicate. */
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case x :: xs if f(x) => dropWhile(xs, f)
    case xs => xs
  }

  /** E3.6: Not everything works out so nicely. Implement a function, init, that returns a List consisting of all
    * but the last element of a List. So, given List(1,2,3,4), init will return List(1,2,3). Why can’t this function
    * be implemented in constant time like tail?
    */
  def init[A](l: List[A]): List[A] = l match {
    case x :: Nil => Nil
    case x :: xs => x :: init(xs)
  }



}
