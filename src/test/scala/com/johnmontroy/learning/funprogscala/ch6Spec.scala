package com.johnmontroy.learning.funprogscala

import com.johnmontroy.learning.funprogscala.ex.{RNG, SimpleRNG}
import org.scalatest.{FlatSpec, Matchers}
import com.johnmontroy.learning.funprogscala.probs.ch6._

class ch6Spec extends FlatSpec with Matchers {
  "nonNegativeInt" should "return a positive Int and the next state" in {
    nonNegativeInt(SimpleRNG(65)) should be ((25008678,SimpleRNG(1638968754616L)))
  }

  "double" should "return a double between 0 and 1" in {
    double(SimpleRNG(65)) should be ((0.011645573196767631,SimpleRNG(1638968754616L)))
  }

  "ints" should "return a random list of integers with the final state" in {
    ints(3)(SimpleRNG(65)) should be ((List(25008678, 59662480, 91247626), SimpleRNG(5980004436786L)))
  }

  "doubleMap" should "return a double identically to the original double implementation" in {
    doubleMap(SimpleRNG(65)) should be ((0.011645573196767631,SimpleRNG(1638968754616L)))
  }


}
