package oops

/**
 * Redefining the getter and setter in scala.
 */
class PrivateDuck {
  private var privateAge = 0

  def age = privateAge //getter
  
  def age_=(newAge: Int) {
    if (newAge > privateAge) privateAge = newAge;
  } //setter
}

object PrivDuckDemo extends App {

  var f = new PrivateDuck

  println("Duck Age:" + f.age)

  f.age_=(100) 

  println("Duck Age:" + f.age)
}