package com.johnmontroy.learning.concprogramming.ex

import scala.annotation.tailrec
import scala.concurrent.{Future, Promise}
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

object ch4 {


  object p1 {
    import java.util._
    private val timer = new Timer(true)

    def timeout(p: Promise[String], t: Long): Future[Unit] = {
      timer.schedule(new TimerTask {
        def run() = {
          p success ()
          timer.cancel()
        }
      }, t)
    }


    def main(args: Array[String]): Unit = {
      val url = args(0)
      val p = Promise[String]

      val f = Future { Source.fromURL(url) }

      f flatMap

    }
  }
  Thread.sleep(50)
}
