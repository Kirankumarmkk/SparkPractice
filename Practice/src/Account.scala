
object Account extends App {
/*

Input:
initial Balance of c1 : 1000.0
initial Balance of c2 : 1000.0

  c1.deposit(100)
  c1.deposit(200)
  c1.withdraw(500)
  c1.withdraw(50.50)
  c2.deposit(3500)

OutPut:
C1 latest balance : 749.5
C1 latest balance : 5500.0
  
 */
  
  var c1= new Accountinfo(1000)
  var c2= new Accountinfo(2000)
  
  println("initial Balance of c1 : "+c1.currentbal)
  println("initial Balance of c2 : "+c1.currentbal)
  c1.deposit(100)
  c1.deposit(200)
  c1.withdraw(500)
  c1.withdraw(50.50)
  
  
  
  c2.deposit(3500)
  
  
  println("C1 latest balance : "+c1.currentbal)
  println("C1 latest balance : "+c2.currentbal)
  
}

class Accountinfo(initBal:Double){
   private var bal = initBal
   
   
   def deposit(amount:Double)={
     
     bal += amount;
     
     bal
     
   }
  
 def withdraw(amount:Double)={
   bal -= amount;
   
   bal
 }
   
 def currentbal() = {
   bal
 }
 
  
}


  