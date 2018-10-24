

object fibonacci {
  def main(args:Array[String]):Unit = {
    
    /* 4
     * 0+1+1+2+3+5+8
     */
    
    var a:Int= 0
    var b:Int=1
    print(a+","+b);

    var total = b
    
    
    
    def fib(x:Int):Unit = {
      for(i <- 0 to 11) {
      var result = a+b
      a=b
      b=result
      total+=result
      print(","+result)
      
      }
    }
    
    println(fib(11)+" Total :"+total)
    
    def fib_recursion(n:Int):Int = {
      
      def fib_tail_rec(n:Int,prev:Int = 0,next:Int = 1):Int = n match {
        case 0 => prev
        case 1 => next
        case _ => fib_tail_rec(n-1,next,(prev+next))
      }
      fib_tail_rec(n)
    }

    var total1 = 0
    for (i <- 0 to 11) {

      var value = fib_recursion(i)
      print(","+value)  
      total1 = total1 + value
    
    }
    
    println("\n rec Total :"+total1)
    
  }
  
}