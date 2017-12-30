package com.johnmontroy.learning.funprogscala.probs

import com.johnmontroy.learning.funprogscala.probs.ch11.Functor

object ch12 {

  trait Applicative[F[_]] extends Functor[F] {

    def map2[A,B,C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
    def unit[A](a: => A): F[A]

    def map[A,B](fa: F[A])(f: A => B): F[B] =
      map2(fa, unit(()))((a, _) => f(a))

    def traverse[A,B](as: List[A])(f: A => F[B]): F[List[B]]

  }
}
