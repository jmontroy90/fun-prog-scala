package com.johnmontroy.learning.cats.ch1

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._ // for Eq
import cats.instances.string._ // for Eq

object EqExercise {
  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (c1,c2) =>
    c1.color === c2.color &&
      c1.name === c2.name &&
      c1.age === c2.age
  }
}


object EqMain extends App {
  import EqExercise._

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  cat1 === cat2
//  optionCat1 =!= optionCat2
}
