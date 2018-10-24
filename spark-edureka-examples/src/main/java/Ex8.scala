/*
 * This example shows the usage of abstract fields in traits.
 * Here maxLength is an abstract field used in ShortLogger8 trait
 * and is overridden in object Main8
 */
trait Logged8 {
  def log(msg: String) { }
}

trait ConsoleLogger8 extends Logged8 {
  override def log(msg: String) { println(msg) }
}

trait ShortLogger8 extends Logged8 {
  val maxLength: Int // An abstract field
  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
}

class Account8 {
  protected var balance = 0.0
}

class SavingsAccount8 extends Account8 with Logged8 {
  var interest = 0.0
  def withdraw(amount: Double) {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }

  // More methods ...
}

object Main8 extends App {
  val acct = new SavingsAccount8 with ConsoleLogger8 with ShortLogger8 {
    val maxLength = 17
  }
  acct.withdraw(100) 
    // Log message is not truncated because maxLength is 20
}