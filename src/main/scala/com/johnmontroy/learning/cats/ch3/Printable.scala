package com.johnmontroy.learning.cats.ch3

final case class Box[A](value: A)

trait Printable[A] { self =>
  def format(value: A): String
  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
      def format(value: B): String = self.format(func(value))
  }
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
      def format(value: String): String = "\"" + value + "\""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    def format(value: Boolean): String = if(value) "yes" else "no"
  }

  implicit def boxPrintable[A](implicit boxPrinter: Printable[A]): Printable[Box[A]] = boxPrinter.contramap[Box[A]](_.value)
}

object Printable {
  def format[A](value: A)(implicit printableInstance: Printable[A]): String = implicitly[Printable[A]].format(value)
}

object PrintableMain extends App {
  import PrintableInstances._

  println(Printable.format(Box("hi")), Printable.format(Box(false)))
}