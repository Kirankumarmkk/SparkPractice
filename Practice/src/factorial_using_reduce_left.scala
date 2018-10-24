

object factorial_using_reduce_left extends App{
  
  var c1 = new factorial_Pr()
    
}

class factorial_Pr{
  
  
  val factorial = (n:Int) => if (n < 1) 1 else (n to 1 by -1).reduceLeft(_*_)
  
  
  
  println(factorial(3))
  println(factorial(4))
  println(factorial(5))
  println(factorial(6))
}

/*
Input:
  println(factorial(3))
  println(factorial(4))
  println(factorial(5))
  println(factorial(6))

Output:  
6
24
120
720

* 
* */
