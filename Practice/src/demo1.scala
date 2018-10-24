

object demo1{
  
  def main(args:Array[String]): Unit = {
      println("Kiran rocks")
  
      var i = 10
      
      def area(a:Int,b:Int) {val c=a*b; println(c)}
  
      area(10,2)
  
      i = 11;
      
      println(i)
 
      def fact(x:Int):Int = { if (x==0) 1 else x*fact(x-1)}
      
      println("factorial"+fact(1))
  
      var string1 = "Kiran"
      
      string1.foreach(i => println(i))
      
      
      
      for (j<-0 to 10) 
      {
      println(j);
      
      }
      
      for(a<-0 to 10; y=a-1;b<-y to 1){println(10*a+y+b)}
      
  }
}



