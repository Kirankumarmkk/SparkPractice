

object Collections {
  def main(args:Array[String]):Unit = {
   
    val i = (1 to 5).toList
    
    def double:PartialFunction[Int,Int] = {
      case num:Int => 2*num
    }

    val j = i.collect(double)

    println(j)
    
    val double1=(num:Int)=>2*num
    
    var j1 = i.map(double1)
    
    println(j1)
    
    val j2 = i.count(x=>x%2!=0)
    
    print(j2)
    
    val odd=(num:Int)=>num%2!=0
    
    val j3=i.count(odd)
    println(j3)
    
  }
}