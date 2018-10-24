

object Tailrecursion extends App{
  
  val n = 5
  
  def fact(x:Int):Int = {
  
    if (x==0) 1 else x*fact(x-1)
    
  }
  
  println(fact(5))
  
  def tailfact(x:Int,y:Int):Int = {
    
    if (x==0) y else tailfact(x-1,x*y) 
    
  }
  
  println("Tail fact:"+tailfact(5,1))
  
  def id(x:Int) = x
  def square(x:Int) = x*x
  def cube(x:Int) = x*x*x
  
  def sum(f:Int=>Int,a:Int,b:Int):Int ={
    if (a>b) 0 else f(a) + sum(f,a+1,b)  
  }
  
  println(sum(id,1,3))
  println(sum(square,1,3))
  println(sum(cube,1,3))
}