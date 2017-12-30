package com.johnmontroy.learning.funprogscala.probs

import com.johnmontroy.learning.funprogscala.probs.Par.Par
import com.johnmontroy.learning.funprogscala.probs.ch11.Monad

/** We start with an IO box that forms a Monoid - this helps us get towards managing effects better via
  * abstracted descriptions. We then run these descriptions through OUR interpreter to actually make things happen.
  * "All it seems to have given us is the ability to delay when a side effect actually happens." */
sealed trait IO[A] { self =>
  def run: A
  def ++[B](io: IO[B]): IO[B] = new IO[B] {
    def run = { self.run; io.run }
  }
  def map[B](f: A => B): IO[B] =
    new IO[B] { def run = f(self.run) }
  def flatMap[B](f: A => IO[B]): IO[B] =
    new IO[B] { def run = f(self.run).run }
}

object IO extends Monad[IO] {
  def unit[A](a: => A): IO[A] = new IO[A] { def run = a }
  def flatMap[A,B](fa: IO[A])(f: A => IO[B]): IO[B] = fa flatMap f
  def apply[A](a: => A): IO[A] = unit(a)
}

sealed trait TailRec[A] {
  def flatMap[B](f: A => TailRec[B]): TailRec[B] = FlatMap(this, f)
  def map[B](f: A => B): TailRec[B] = flatMap(f andThen (Return(_)))
}
case class Return[A](a: A) extends TailRec[A]
case class Suspend[A](resume: () => A) extends TailRec[A]
case class FlatMap[A,B](sub: TailRec[A], k: A => TailRec[B]) extends TailRec[B]

object ch13 {

  def ReadLine: IO[String] = IO { readLine }
  def PrintLine(msg: String): IO[Unit] = IO { println(msg) }
  def fahrenheitToCelsius(f: Double): Double =
    (f - 32) * 5.0/9.0

  def converter: IO[Unit] = for {
    _ <- PrintLine("Enter a temperature in degrees Fahrenheit: ")
    d <- ReadLine.map(_.toDouble)
    _ <- PrintLine(fahrenheitToCelsius(d).toString)
  } yield ()

  /** Now let's use the `ReifiedIO` type to avoid a stack overflow! */
  def printLine(s: String): TailRec[Unit] = Suspend(() => Return(println(s)))

  /** This is the interpreter of ReifiedIO context flow descriptions. It has errors in it with type inference!
    * All of this is done to basically keep recursive `run` calls in tail position, such as to eliminate the need for
    * stack frames. This is a technique called "trampolining", and relates to coroutines (producing more of itself?). */
 /* @annotation.tailrec
  def run[A](io: TailRec[A]): A = io match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r))
      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
    }
  }*/

}

object AsyncObj {

  sealed trait Async[A] {
    def flatMap[B](f: A => Async[B]): Async[B] = FlatMap(this, f)
    def map[B](f: A => B): Async[B] = flatMap(f andThen (Return(_)))
  }

  case class Return[A](a: A) extends Async[A]
  case class Suspend[A](resume: Par[A]) extends Async[A]
  case class FlatMap[A,B](sub: Async[A], k: A => Async[B]) extends Async[B]

  /*@annotation.tailrec
  def step[A](async: Async[A]): Async[A] = async match {
    case FlatMap(FlatMap(x,f), g) => step(x flatMap (a => f(a) flatMap g))
    case FlatMap(Return(x), f) => step(f(x))
    case _ => async
  }

  def run[A](async: Async[A]): Par[A] = step(async) match {
    case Return(a) => Par.unit(a)
    case Suspend(r) => Par.flatMap(r)(a => run(a))
    case FlatMap(x, f) => x match {
      case Suspend(r) => Par.flatMap(r)(a => run(f(a)))
      case _ => sys.error("Impossible; `step` eliminates these cases")
    }
  }*/
}

object FreeMonad {
  sealed trait Free[F[_],A]
  case class Return[F[_],A](a: A) extends Free[F,A]
  case class Suspend[F[_],A](s: F[A]) extends Free[F,A]
  case class FlatMap[F[_],A,B](s: Free[F,A], f: A => Free[F,B]) extends Free[F,B]
}