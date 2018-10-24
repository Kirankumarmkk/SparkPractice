package oops

class Counter1 {
  private var value = 0 // You must initialize the field
  def increment() { value += 1 } // Methods are public by default
  def current() = value
}

class Counter2 {
  private var value = 0
  def increment() { value += 1 }
  def current = value // No () in definition
}

object CounterExample extends App {
  val myCounter = new Counter1
  myCounter.increment()
  println(myCounter.current)

  val myCounter1 = new Counter1() // () ok
  myCounter.current() // () ok

  val myCounter2 = new Counter2
  //myCounter2.current() // () not ok
  
  println(myCounter2.current)
}