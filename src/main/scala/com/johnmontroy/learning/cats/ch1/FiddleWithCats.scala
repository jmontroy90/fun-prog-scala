package com.johnmontroy.learning.cats.ch1

object FiddleWithCats {

  import cats.Show
  import cats.instances.all._ // import all type class instances at once
  import cats.syntax.all._ // import all syntax (implicit classes) at once
  import cats.instances.string._ // for Show


//  val showInt: Show[Int] = Show.apply[Int]
//  val showString: Show[String] = Show.apply[String]

  import java.util.Date

  implicit val dateShow: Show[Date] = new Show[Date] {
    def show(date: Date): String = s"${date.getTime}ms since the epoch."
  }
}

