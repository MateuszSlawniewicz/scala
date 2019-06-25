package dependencyinjection

object DependencyInjectionDemo extends App {

  case class Field()

  case class Digger()

  case class PotatoFarm(field: Field, digger: Digger)

  case class CowPasture()

  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPoratoes(): Unit = {
      println(s"Welcome to $this. Here is your order")
    }
  }

  val field = new Field()
  val digger = new Digger()
  val potatoFarm = new PotatoFarm(field, digger)
  val cowPasture = new CowPasture()
  val meatery = new Meatery(cowPasture)
  val restaurant = new Restaurant(potatoFarm, meatery)
  restaurant.orderSteakWithPoratoes()

}
