package com.johnmontroy.learning.cats.ch1

trait Printable[A] {
  def format(v: A): String
}

object PrintableInstances {
  implicit val StringPrintable = new Printable[String] {
    override def format(v: String): String = v
  }

  implicit val IntPrintable = new Printable[Int] {
    override def format(i: Int): String = i.toString
  }

  implicit val CatPrintable = new Printable[Cat] {
    override def format(c: Cat): String = {
      s"${Printable.format(c.name)} is a ${Printable.format(c.age)} year-old ${Printable.format(c.color)} cat."
    }
  }
}

object Printable {
  def format[A](v: A)(implicit p: Printable[A]): String = p.format(v)
  def print[A](v: A)(implicit p: Printable[A]): Unit = println(format(v))
}

object PrintableSyntax {
  implicit class PrintableOps[A](v: A) {
    def format(implicit p: Printable[A]): String = Printable.format(v)
    def print(implicit p: Printable[A]): Unit = Printable.print(v)
  }
}

/** Using the type class */
final case class Cat(name: String, age: Int, color: String)

object RunPrintable extends App {
  import PrintableInstances._
  import PrintableSyntax._

  val tc = Cat("cat",12,"blue")

  // using interface object
  Printable.print(tc)

  // using interface syntax
  tc.print
}


