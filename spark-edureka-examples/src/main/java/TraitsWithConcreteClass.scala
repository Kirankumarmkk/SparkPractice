// Defining trait.
trait ConsoleLogger2 {
  /*
   * Concrete method inside a trait. 
   * Not supported by Java till java 7.
   */
  def log(msg: String) { println(msg) }
}

// Defining Class.
class Account2 {
  protected var balance = 10.0
}

class SavingsAccount extends Account2 with ConsoleLogger2 {
  
  def withdraw(amount: Double) {
    if (amount > balance) log("Oops..! You don't have enough balance!!")
    else {
      balance -= amount
      println("Withdrawl Successful. Current Balance is: "+balance)
    }
  }
  
}

object TraitsWithConcreteClass extends App {
  val acct = new SavingsAccount
  //acct.withdraw(args(0).toDouble)
  acct.withdraw(100)
}