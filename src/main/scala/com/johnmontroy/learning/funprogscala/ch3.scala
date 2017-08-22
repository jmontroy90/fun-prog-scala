package com.johnmontroy.learning.funprogscala

import scala.annotation.tailrec

object ch3 {


  /** E3.1: What will be the result of the following match expression? */
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
  def tail = ???

  /** E3.3: Using the same idea, implement the function setHead for replacing the first element of a List
    * with a different value. */
  def setHead = ???

  /** E3.4: Generalize tail to the function drop, which removes the first n elements from a list. Note that this function
    * takes time proportional only to the number of elements being dropped—we don’t need to make a copy of the entire List. */
  def drop = ???

  /** E3.5: Implement dropWhile, which removes elements from the List prefix as long as they match a predicate. */
  def dropWhile = ???

  /** E3.6: Not everything works out so nicely. Implement a function, init, that returns a List consisting of all
    * but the last element of a List. So, given List(1,2,3,4), init will return List(1,2,3). Why can’t this function
    * be implemented in constant time like tail?
    */
  def init[A](l: List[A]): List[A] = l match {
    case x :: Nil => Nil
    case x :: xs => x :: init(xs)
  }

  /** E3.9: Compute the length of a list using foldRight. */
  def length[A](as: List[A]): Int = as.foldRight(0)((_, acc) => acc + 1)

  /** E3.10: Our implementation of foldRight is not tail-recursive and will result in a StackOverflowError for
    * large lists (we say it’s not stack-safe). Convince yourself that this is the case, and then write another
    * general list-recursion function, foldLeft, that is tail-recursive, using the techniques we discussed
    * in the previous chapter. */
  @tailrec
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case x :: xs => foldLeft(xs, f(z, x))(f)
  }

  /** E3.11: Write sum, product, and a function to compute the length of a list using foldLeft. */
  def sum(as: List[Int]): Int = as.foldLeft(0)(_ + _)
  def product(as: List[Int]): Int = as.foldLeft(0)(_ * _)
  def length[A](as: List[A]): Int = as.foldLeft(0)((acc, _) => acc + 1)

  /** E3.12: Write a function that returns the reverse of a list (given List(1,2,3) it returns List(3,2,1)).
    * See if you can write it using a fold. */
  def reverse[A](as: List[A]): List[A] = as.foldLeft(List(): List[A])((acc, itr) => itr :: acc)

  /** E3.13: Hard: Can you write foldLeft in terms of foldRight? How about the other way around? Implementing
    * foldRight via foldLeft is useful because it lets us implement foldRight tail-recursively, which means it
    * works even for large lists without overflowing the stack. */
  def foldLeftFR = ???

  /** E3.14: Implement append in terms of either foldLeft or foldRight. */
  def append = ???

  /** E3.15: Hard: Write a function that concatenates a list of lists into a single list. Its runtime should be
    * linear in the total length of all lists. Try to use functions we have already defined. */
  def flatMap = ???

  /** E3.16: Write a function that transforms a list of integers by adding 1 to each element.
    * (Reminder: this should be a pure function that returns a new List!) */
  def mapPlusOne = ???

  /** For use in exercises 25 - 29 */
  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  /** E3.25: Write a function size that counts the number of nodes (leaves and branches) in a tree. */
  def treeSize = ???

  /** E3.26: Write a function maximum that returns the maximum element in a Tree[Int].
    * (Note: In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x and y.) */
  def maximumTree = ???

  /** E3.27: Write a function depth that returns the maximum path length from the root of a tree to any leaf. */
  def maxTreeLength = ???

  /** E3.28: Write a function map, analogous to the method of the same name on List, that modifies
    * each element in a tree with a given function. */
  def treeMap = ???

  /** E3.29: Generalize size, maximum, depth, and map, writing a new function fold that abstracts
    * over their similarities. Reimplement them in terms of this more general function. Can you draw an
    * analogy between this fold function and the left and right folds for List? */
  def treeFold = ???

}
