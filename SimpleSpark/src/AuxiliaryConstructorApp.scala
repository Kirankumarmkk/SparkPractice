// primary constructor
class Pizza(var crustSize: Int, var crustType: String) {
  // one-arg auxiliary constructor
  def this(crustSize: Int) {
    this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
  }
  // one-arg auxiliary constructor
  def this(crustType: String) {
    this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  }
  // zero-arg auxiliary constructor
  def this() {
    this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  }
  override def toString = s"A $crustSize inch pizza with a $crustType crust"
}
object Pizza {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"
}

object AuxiliaryConstructorApp extends App {
  val p1 = new Pizza(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  val p2 = new Pizza(Pizza.DEFAULT_CRUST_SIZE)
  val p3 = new Pizza(Pizza.DEFAULT_CRUST_TYPE)
  val p4 = new Pizza
  println(p1.toString())
  println(p2.toString())
  println(p3.toString())
  println(p4.toString())
}