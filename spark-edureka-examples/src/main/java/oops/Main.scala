package oops
/*
 * When a singleton object is named the same as a class,
 * it is called a companion object. A companion object must be defined inside the same source file as the class. 
 * 
 */
class Main {
  def sayHelloWorld() {
    println("Hello World");
  }
}

object Main extends App {
  def sayHi() {
    println("Hi!");
  }

  var aMain = new Main();
  aMain.sayHelloWorld();

  Main.sayHi();
}