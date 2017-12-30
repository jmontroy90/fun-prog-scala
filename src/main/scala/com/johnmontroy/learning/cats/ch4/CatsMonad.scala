package com.johnmontroy.learning.cats.ch4


import cats.Monad
import cats.instances.option._
import cats.instances.list._

import scala.util.{Success, Try}

object CatsMonad {
  val opt1 = Monad[Option].pure(3) // Some(3)
  val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2)) // Some(5)
  val opt3 = Monad[Option].map(opt2)(a => 100 * a) // Some(500)

  val list1 = Monad[List].pure(3) // List(3)
  val list2 = Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10)) // List(1, 10, 2, 20, 3, 30)
  val list3 = Monad[List].map(list2)(a => a + 123) // whatever

  Try(3).recoverWith { case _ => Success(3) }
  Try(3).recover { case _ => 3 }


}
