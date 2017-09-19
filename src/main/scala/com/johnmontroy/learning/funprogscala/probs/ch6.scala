package com.johnmontroy.learning.funprogscala.probs

import com.johnmontroy.learning.funprogscala.ex.RNG
import com.johnmontroy.learning.funprogscala.ex.ch6._

object ch6 {

  /** E6.1: Write a function that uses RNG.nextInt to generate a random integer between 0 and Int.maxValue (inclusive).
    * Make sure to handle the corner case when nextInt returns Int.MinValue, which doesn’t have a non-negative counterpart. */
  // this feels like a silly implementation
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val ret = rng.nextInt
    val trn = {
      if (ret._1 < 0 & ret._1 >= -Int.MaxValue) -1 * ret._1
      else if (ret._1 == Int.MinValue) Int.MaxValue
      else ret._1
    }
    (trn, ret._2)
  }

  /** E6.2: Write a function to generate a Double between 0 and 1, not including 1. Note: You can use Int.MaxValue to
    * obtain the maximum positive integer value, and you can use x.toDouble to convert an x: Int to a Double. */
  def double(rng: RNG): (Double, RNG) = {
    val nxt = nonNegativeInt(rng)
    val db: Double = nxt._1.toDouble / Int.MaxValue.toDouble
    (db, nxt._2)
  }

  /** E6.3: Write functions to generate an (Int, Double) pair, a (Double, Int) pair, and a (Double, Double, Double) 3-tuple.
    * You should be able to reuse the functions you’ve already written. */
  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (n1,s1) = rng.nextInt
    val (d1,s2) = double(s1)
    ((n1, d1), s2)
  }

  def doubleInt(rng: RNG): ((Double,Int), RNG) = {
    val ((i1, d1), s1) = intDouble(rng)
    ((d1, i1), s1)
  }
  def double3(rng: RNG): ((Double,Double,Double), RNG) = {
    val (d1, s1) = double(rng)
    val (d2, s2) = double(s1)
    val (d3, s3) = double(s2)
    ((d1,d2,d3), s3)
  }

  /** E6.4: Write a function to generate a list of random integers. */
  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    if (count == 0) (Nil, rng)
    else {
      val (i,s) = rng.nextInt
      (i :: ints(count - 1)(s)._1, ints(count - 1)(s)._2)
    }
  }

  /** E6.5: Use map to reimplement double in a more elegant way. */
  def doubleMap(rng: RNG): (Double, RNG) =
    map(nonNegativeInt)(n => n / Int.MaxValue.toDouble)(rng)

}
