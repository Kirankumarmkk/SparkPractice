package oops

object CompanionTest extends App {
  var x = new Account
  println(x.Account.newNum())
}

class Account {
  
  val id = Account.newNum()
  
  object Account {
    
    private var lastNum = 0
    
    def newNum() = {
      lastNum += 1
      lastNum
    }
  }
}