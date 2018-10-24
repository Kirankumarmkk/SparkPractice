

object Prime_number extends App{

 
  val numbers:Array[Int] = Array(1,5,7,3,2,4,8,10,11);


  
  
  
  def isPrime2(i :Int) : Boolean = {
  
    if (i==1) false
    else
      if (i==2) false
    else 
      !(2 to i-1).exists(x=>i%x==0) 
  }
  
  println("Primenumbers ")

  numbers.foreach(k=>(if (isPrime2(k))  print(k+" ")))

  /*
   * Input :Array(1,5,7,3,2,4,8,10,11);
   * 
   	Output:
   	Primenumbers : 5 7 3 11 
   * 
   */
  
  
}