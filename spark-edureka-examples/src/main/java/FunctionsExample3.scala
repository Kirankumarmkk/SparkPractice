/*
 * This example is just a demo of various useful
 * higher order functions available with collections 
 */
object Main12 extends App {
  //(1 to 9).map(_ * 0.1).foreach(println)
  //(1 to 9).map("*" * _).foreach(println _)
  //(1 to 9).filter(_ % 2 == 0).foreach(println _)

  //println((1 to 4).reduceLeft(_ * _))

  //val x = "Mary had a little lamb".split(" ").sortWith(_.length < _.length)
  //for (i <- x) println(i)
  
  val x = List(1,2,3,4,5,6,6,7,8,8)
  val evens = x.filter((i:Int) => i%2 == 0)
  val evens1 = x.filter(i => i%2 == 0)
  val evens2 = x.filter(_%2 ==0)
  println(evens)
  println(evens1)
  println(evens2)
}
