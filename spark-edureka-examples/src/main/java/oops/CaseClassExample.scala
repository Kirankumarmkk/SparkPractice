package oops

case class CaseClass(a: Int, b: Int)

object CaseClassExample {

  def main(args: Array[String]) {
    var c = CaseClass(10, 200) // Creating object of case class  
    println("a = " + c.a) // Accessing elements of case class  
    println("b = " + c.b)
  }
}