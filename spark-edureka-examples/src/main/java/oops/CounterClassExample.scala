package oops

object CounterClassExample extends App {

  var c = new CountNumber
  println("Value before inc() is called :" + c.curr())
  c.inc()
  c.inc()
  c.inc()
  c.inc()
  c.inc()
  println("Value after inc() is called :" + c.curr())

  //  def main(args: Array[String]): Unit = {
  //    
  //    var c = new CountNumber
  //    
  //    println("Value before inc() is called :"+c.curr())
  //    
  //    c.inc()
  //    c.inc()
  //    c.inc()
  //    c.inc()
  //    c.inc()
  //    
  //    println("Value after inc() is called :"+c.curr())
  //  }
}

class CountNumber {

  private var value = 0

  def inc() {
    value += 1
  }

  def curr() = {
    value
  }
}