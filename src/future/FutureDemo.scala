package future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object FutureDemo extends App {
  val start = System.currentTimeMillis()

  def info(msg: String) = printf("%.2f: %s\n", (System.currentTimeMillis() - start) / 1000.0, msg)

  case class Dish(name: String) {
    def +(other: Dish) = Dish(s"$name with ${other.name}")
  }

  def cook(what: String): Dish = {
    Thread.sleep(1000L)
    info(s"$what was cooked!")
    Dish(what)
  }

  def serve(dish: Dish): Unit = {
    info(s"Here is your ${dish.name}")
  }

  val futureSteak = Future {
    cook("steak")
  }
  val futurePotatoes = Future {
    cook("potatoes")
  }

  val fs: Future[Unit] = for {
    s <- futureSteak
    p <- futurePotatoes
  } yield {
    serve(s + p)
  }
  Await.result(fs, 10.seconds)
}
