
object checking_Account extends App {

  /*
Input:

Initial Balance of c1 : 1000.0
Initial Balance of c2 : 1000.0
  
  c1.deposit(100)
  c1.deposit(200)
  c1.withdraw(500)
  c1.withdraw(50)
    
  c2.deposit(3500)

Output:
C1 latest balance : 754.0
C1 transactions : 4
C1 latest balance : 5501.0
C1 transactions : 1

 */
  
  var c1= new checking(1000)
  var c2= new checking(2000)
  
  println("initial Balance of c1 : "+c1.currentbal)
  println("initial Balance of c2 : "+c1.currentbal)
  c1.deposit(100)
  c1.deposit(200)
  c1.withdraw(500)
  c1.withdraw(50)
    
  c2.deposit(3500)
    
  
  println("C1 latest balance : "+c1.currentbal)
  println("C1 transactions : "+c1.transactions)
  
  println("C1 latest balance : "+c2.currentbal)
  println("C1 transactions : "+c2.transactions)
}

class checking(initBal:Double){
   private var bal = initBal
   
   var trans = 0
   
   def deposit(amount:Double)={
     
     trans = trans+1

     bal += amount;
     
     bal = bal+1
     
         
   }
  
 def withdraw(amount:Double)={
   
   trans = trans+1
   
   bal -= amount;
   
   bal = bal+1
 }
   
 def currentbal() = {
   bal
 }
 
 def transactions = {
      trans 
 }
 
  
}


  