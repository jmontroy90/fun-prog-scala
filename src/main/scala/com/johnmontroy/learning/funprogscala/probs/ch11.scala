package com.johnmontroy.learning.funprogscala.probs


object ch11 {

  trait Functor[F[_]] {
    def map[A,B](fa: F[A])(fn: A => B): F[B]
  }

  val listFunctor = new Functor[List] {
    def map[A,B](as: List[A])(f: A => B): List[B] = as map f
  }

  trait Monad[F[_]] extends Functor[F] {
    def unit[A](a: => A): F[A]
    def flatMap[A,B](ma: F[A])(f: A => F[B]): F[B]
    def map[A,B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(a => unit(f(a)))
    def map2[A,B,C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] = flatMap(ma)(a => map(mb)(b => f(a,b)))

    // stolen from answers, need to study these because i skipped them in earlier chapters
    def sequence[A](lma: List[F[A]]): F[List[A]] =
      lma.foldRight(unit(List[A]()))((ma, mla) => map2(ma, mla)(_ :: _))
    def traverse[A,B](la: List[A])(f: A => F[B]): F[List[B]] =
      la.foldRight(unit(List[B]()))((a, mlb) => map2(f(a), mlb)(_ :: _))

    def replicateM[A](n: Int, ma: F[A]): F[List[A]] = sequence(List.fill(n)(ma))
  }

  val listMonad = new Monad[List] {
    def unit[A](a: => A): List[A] = List(a)
    def flatMap[A,B](ma: List[A])(f: A => List[B]): List[B] = ma flatMap f
  }

  val optionMonad = new Monad[Option] {
    def unit[A](a: => A): Option[A] = Some(a)
    def flatMap[A,B](ma: Option[A])(f: A => Option[B]): Option[B] = ma flatMap f
  }

  case class Id[A](value: A) {
    def map[B](f: A => B): Id[B] = Id(f(value))
    def flatMap[B](f: A => Id[B]): Id[B] = f(value)
  }

  val idMonad = new Monad[Id] {
    def unit[A](a: => A): Id[A] = Id(a)
    def flatMap[A,B](ma: Id[A])(f: A => Id[B]): Id[B] = ma flatMap f
  }

}
