package com.johnmontroy.learning.cats.ch4

import cats.Monad

sealed trait TreeMonad[+A]
final case class Branch[A](left: TreeMonad[A], right: TreeMonad[A]) extends TreeMonad[A]
final case class Leaf[A](value: A) extends TreeMonad[A]

object TreeMonad {

  implicit val treeMonad: Monad[TreeMonad] = new Monad[TreeMonad] {

    override def flatMap[A, B](fa: TreeMonad[A])(f: A => TreeMonad[B]): TreeMonad[B] = fa match {
      case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
      case Leaf(v) => f(v)
    }

    override def pure[A](x: A): TreeMonad[A] = Leaf(x)

    /** Answer taken from book, come back to understand this more later. */
    override def tailRecM[A,B](a: A)(f: A => TreeMonad[Either[A, B]]): TreeMonad[B] = f(a) match {
        case Branch(l, r) => Branch(
            flatMap(l) {
              case Left(v)  => tailRecM(v)(f)
              case Right(v) => pure(v)
            },
            flatMap(r) {
              case Left(v)  => tailRecM(v)(f)
              case Right(v) => pure(v)
            }
          )
        case Leaf(Left(value)) => tailRecM(value)(f)
        case Leaf(Right(value)) => Leaf(value)
      }
  }
}

object UseTree extends App {
  def branch[A](left: TreeMonad[A], right: TreeMonad[A]): TreeMonad[A] = Branch(left, right)
  def leaf[A](value: A): TreeMonad[A] = Leaf(value)


}
