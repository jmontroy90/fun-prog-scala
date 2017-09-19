package com.johnmontroy.learning.funprogscala

import org.scalatest.{FlatSpec, Matchers}
import com.johnmontroy.learning.funprogscala.probs.ch5._
import com.johnmontroy.learning.funprogscala.ex.ch5._

class ch5Spec extends FlatSpec with Matchers {
  "toList" should "convert Stream to core Scala list" in {
    val s = Stream[Int](1,2,3)
    s.toList should be (List(1,2,3))
  }

  "take" should "only explicitly evaluate and take the first n elements of a stream" in {
    val s = Stream[Int](1,2,3)
    s.take(2).toList should be (List(1,2))
  }

  "takeWhile" should "take first n elements matching predicate" in {
    val s = Stream[Int](2,4,6,7,8)
    s.takeWhile((x: Int) => x % 2 == 0).toList should be (List(2,4,6))
  }

  "forAll" should "return true if predicate matches all elements" in {
    val st = Stream[Int](2,4,6,8,10)
    val sf = Stream[Int](2,4,6,7,8)

    st.forAll(_ % 2 == 0) should be (true)
    sf.forAll(_ % 2 == 0) should be (false)
  }

  "fibs" should "generate fibonacci numbers" in {
    fibs().take(10).toList should be (List(0,1,1,2,3,5,8,13,21,34))
  }

}
