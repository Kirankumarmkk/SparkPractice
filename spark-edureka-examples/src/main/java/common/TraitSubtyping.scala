package common

import scala.collection.mutable.ArrayBuffer

/*
 * The trait Pet has an abstract field name which gets implemented by Cat and Dog in their constructors. 
 * On the last line, we call pet.name which must be implemented in any subtype of the trait Pet.
 */
trait Pet {
  var name: String
}

class Cat(var name: String) extends Pet

class Dog(var name:String) extends Pet

object TraitSubtyping extends App{
  
  var dog = new Dog("Germen Shephard")
  var cat = new Cat("Snowfie")
  var animals = ArrayBuffer.empty[Pet]
  
  // Adding instance of Dog and Cat in an ArrayBuffer of type Pet.
  animals.append(dog)
  animals.append(cat)
  
  animals.foreach {x => println(x.name)}
}
