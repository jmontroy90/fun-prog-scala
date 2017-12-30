package com.johnmontroy.learning.cats.ch2

import cats.Monoid
import cats.Semigroup
import cats.instances.string._ // for Monoid
import cats.instances.option._
import cats.instances.int._ // for Monoid
import cats.syntax.semigroup._ // for |+|

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait MyMonoid[A] extends Semigroup[A] {
  def empty: A
}

object MyMonoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}


object BooleanMonoidInstances {
  implicit val AndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = true
  }

  implicit val OrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean = false
  }

  implicit val XorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x && y) || (x && !y)
    override def empty: Boolean = true
  }
}


object MonoidMain {
  /** All monoids must obey these two laws */
  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  val nums = 1 |+| 2 // |+| Monoid[Int].empty
  val opts = Option(1) |+| Option(3) // Some(4)

}
