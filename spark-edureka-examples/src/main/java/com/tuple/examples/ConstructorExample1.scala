package com.tuple.examples

object ConstructorExample1 extends App {
  var d = new Duck(10, 20)

  var d1 = new Duck(11, 21, "MyDuck")

}

class Duck(val size: Int, val age: Int) {
  private var name = ""
  println("New Duck Created!!!")
  

  def this(size: Int, age: Int, dName: String) {
    this(size, age)
    name = dName
    println("Age:" + age + "\t" + "size" + size + "\t" + "Name" + name)
  }
}