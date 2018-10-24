

object Higher_Order_function extends App{
  
def sum(f:Int=> Int,a:Int,b:Int):Int = {
  if (a>b) 0
  else 
    f(a) + sum(f,a+1,b)
   
}

def id(x:Int):Int = {x}
def cube(x:Int):Int = {x*x*x}
def fact(n:Int):Int = {if (n==0) 1 else n*fact(n-1)}


def sumints(a:Int,b:Int):Int = { sum(id,a,b)}
def cubesum(a:Int,b:Int):Int = {sum(cube,a,b)}
def sumfact(a:Int,b:Int):Int = {sum(fact,a,b)}

println(sumints(1,3)) //6
println(cubesum(1,3)) //36
println(sumfact(1,3)) //9

}
