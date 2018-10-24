package com.tuple.examples

object MultipleConstructors {

  def main(args: Array[String]) {
    
    // (1) use the primary constructor
    val prakash = new Person("Prakash", "Chauhan", 29)
    println(prakash)

    // (2) use a Auxiliary constructor
    val fred = new Person("Fred", "Thomas")
    println(fred)

    // (3) use a Auxiliary constructor
    val john = new Person("John")
    println(john)
    
  }
}


class Person(val firstName: String, val lastName: String, val age: Int) {

  // Auxiliary constructor 1
  def this(firstName: String) {
    this(firstName, "", 0); // Calling the primary constructor.
    println("\nCalling Auxiliary constructor 1")
  }
  
  
 // // Auxiliary constructor 2
  def this(firstName: String, lastName: String) {
    this(firstName, lastName, 0); // Calling the primary constructor.
     println("\nCalling Auxiliary constructor 2")
  }

  override def toString: String = {
    return "%s %s, age %d".format(firstName, lastName, age)
  }

}