package com.johnmontroy.learning.cats.ch4

import cats.Id

trait IdMonad[F[_]] {
  def pure[A](a: A): Id[A] = a
  def flatMap[A,B](id: Id[A])(f: A => Id[B]): Id[B] = f(id)
  def map[A,B](id: Id[A])(f: A => B): Id[B] = f(id)
}
