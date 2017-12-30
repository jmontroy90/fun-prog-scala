package com.johnmontroy.learning.cats.ch3

import cats.Functor

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object BranchingInstances {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      case Leaf(v) => Leaf(f(v))
    }
  }

}

object Tree {
  def map[A,B](t: Tree[A])(f: A => B)(implicit treeFunctor: Functor[Tree]): Tree[B] = treeFunctor.map(t)(f)
}

object BranchingMain extends App {
  import BranchingInstances._

  val t = Tree.map(Branch(Leaf(3), Leaf(4)))(_ + 1)

  println(t)
}
