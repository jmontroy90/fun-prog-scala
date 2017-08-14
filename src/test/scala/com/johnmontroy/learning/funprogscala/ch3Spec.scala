package com.johnmontroy.learning.funprogscala

import org.scalatest.{FlatSpec, Matchers}
import ch3._

class ch3Spec extends FlatSpec with Matchers {
  "tail" should "return in the tail" in {
    tail(List(1,2,3)) should be (List(2,3))
  }

  "setHead" should "replace the head of the list" in {
    setHead(List(1,2,3), 4) should be (List(4,2,3))
  }

  "drop" should "drop the first n characters" in {
    drop((1 until 10).toList, 4) should be ((5 until 10).toList)
  }

  "dropWhile" should "drop on the condition" in {
    dropWhile(List(5,6,7,3,8,9), (_: Int) >= 5) should be (List(3,8,9))
  }

  "init" should "drop the last element of the list" in {
    init(List(1,2,3,4)) should be (List(1,2,3))
  }

}