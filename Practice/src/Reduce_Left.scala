

object Reduce_Left extends App{
  
  var c1 = new Reduce_Pr()
  
  
}

class Reduce_Pr{
  
  val numbers:Array[Int] = Array(100,200,500,50,10,1);
  val words:Seq[String] = Seq("Kiran","Kumar","is","from","Hyderabad","edureka")
  
  val max = numbers.reduceLeft(_ max _)
  
  println("Maximum "+max)
  
  val min = numbers.reduceLeft(_ min _)
  
  println("Minimum "+min)
  
  val total_num = numbers.reduceLeft(_ + _)
  
  println("Total "+total_num)
  
  val concat = numbers.reduceLeft((a,b) => a+b)
  
  println("Concatenate "+concat)

    val concat1 = words.reduceLeft((a,b) => a+" "+b)
    println("Concatenate words "+concat1)
}