
/**
 *  Cast a Scala object from one type to another using "asInstanceOf"
 */
object TypeCastExample extends App {
  val a = 10
  println(a.getClass() + " :" + a)

  val b = a.asInstanceOf[Long]
  println(b.getClass() + " :" + b)

  val c = a.asInstanceOf[Byte]
  println(c.getClass() + " :" + c)

  // Example showing ClassCastException.
  // val d = a.asInstanceOf[String] 

  /*
   * when you need to interact with Java and send it an array of Object instances.
   */
  val objects = Array("a", 1)
  val arrayOfObject = objects.asInstanceOf[Array[Object]]

}