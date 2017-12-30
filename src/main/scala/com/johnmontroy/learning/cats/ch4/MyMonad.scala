package com.johnmontroy.learning.cats.ch4

import scala.language.higherKinds

trait MyMonad[F[_]] {
  def pure[A](a: A): F[A]
  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}