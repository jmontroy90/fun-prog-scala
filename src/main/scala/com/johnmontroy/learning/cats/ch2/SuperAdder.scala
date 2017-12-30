package com.johnmontroy.learning.cats.ch2

import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object SuperAdder extends App {
  def add[A : cats.Monoid](items: List[A]): A = items.foldLeft(cats.Monoid[A].empty)(_ |+| _)

  implicit val OrderInstance: cats.Monoid[Order] = new cats.Monoid[Order] {
    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    override def empty: Order = Order(0, 0)
  }

  println(add(List(Order(1,2), Order(3,4))))
}
