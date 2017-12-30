package com.johnmontroy.learning.cats.ch3


import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.instances.function._ // for Functor
import cats.syntax.functor._     // for map

import scala.language.higherKinds

trait MyFunctor[F[_], A] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object MyFunctor {
  val list1 = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)

  val option1 = Option(123)
  val option2 = Functor[Option].map(option1)(_.toString) // option2: Option[String] = Some(123)

  val func = (x: Int) => x + 1
  val liftedFunc = Functor[Option].lift(func)
}


object FunctionFunctors {

  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => a + "!"

  // compiler is having a hard time with this..
//  val func4 = func1.map(func2).map(func3)
//  func4(123)
}