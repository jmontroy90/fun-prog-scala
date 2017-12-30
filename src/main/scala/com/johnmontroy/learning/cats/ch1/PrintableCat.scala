package com.johnmontroy.learning.cats.ch1

import cats.Show
import cats.syntax.show._


object PrintableCatInstances {
  implicit val stringShow = Show.show((s: String) => s)
  implicit val intShow = Show.show((i: Int) => i.toString)
  implicit val catShow = Show.show[Cat]((c: Cat) => s"${c.name} is a ${c.age} year-old ${c.color} cat.")
}

object PrintableCat extends App {
  import PrintableCatInstances._

  println(Cat("mycat",12,"black").show)

}
