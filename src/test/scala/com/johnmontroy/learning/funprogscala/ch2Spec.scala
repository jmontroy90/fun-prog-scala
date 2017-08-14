package com.johnmontroy.learning.funprogscala

import org.scalatest.{FlatSpec, Matchers}
import ch2._

class ch2Spec extends FlatSpec with Matchers {

  // 0, 1, 1, 2, 3, 5, 8, 13, ...
  "fib" should "correctly find the nth fibonacci number" in {
    fib(1) should be (0)
    fib(2) should be (1)
    fib(3) should be (1)
    fib(4) should be (2)
    fib(5) should be (3)
    fib(6) should be (5)
    fib(7) should be (8)
    fib(8) should be (13)
  }

  "isSorted" should "determine if a list of ints is sorted" in {
    isSorted(Array(1,2,3), (a1: Int, a2: Int) => a2 > a1) should be (true)
    isSorted(Array(1,2,4,3), (a1: Int, a2: Int) => a2 > a1) should be (false)
    isSorted(Array(2,1), (a1: Int, a2: Int) => a2 > a1) should be (false)
  }

  it should "determine if a list of strings is sorted" in {
    def stringSortChecker(a1: String, a2: String): Boolean = Array(a1,a2).sameElements(Array(a1,a2).sorted)
    isSorted(Array("a","b","c"), stringSortChecker) should be (true)
    isSorted(Array("a","c","b"), stringSortChecker) should be (false)
  }
}