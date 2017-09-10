package com.johnmontroy.learning.funprogscala

import org.scalatest.{FlatSpec, Matchers}
import com.johnmontroy.learning.funprogscala.probs.ch4._

class ch4Spec extends FlatSpec with Matchers {
  "Option" should "return expected results for all core functions" in {
    val tstSome = new Some[Int](4)

    tstSome.getOrElse(None) should be (4)
    tstSome.map(_ + 1) should be (Some(5))
    tstSome.flatMap(v => Some(v + 1)) should be (Some(5))
    tstSome.orElse(None) should be (Some(4))
    tstSome.filter(_ < 3) should be (None)
  }
}