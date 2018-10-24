class Item(var price: Double, var description: String) {

  // Override toString() method of AnyRef Class.
  override def toString(): String = {
    description + " Cost: " + discountedPrice
  }

  // No discounted price
  def discountedPrice(): Double = { price }
}

class SpecialItem(price: Double, description: String, discountPercent: Double) extends Item(price, description) {
  
  // A val in Superclass cannot be overridden by var or def in subclass
  override def discountedPrice(): Double = { price - ((discountPercent / 100) * price) }
}

object Inheritance {
  def main(args: Array[String]): Unit = {
    
    val item = new Item(45.6, "Book")
    println(item)

    val specialItem = new SpecialItem(100, "Sepcial Book", 10)
    println(specialItem)
  }
}