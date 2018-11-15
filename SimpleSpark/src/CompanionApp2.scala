class Foo2 {
// access the private object field 'obj'
def printObj { println(s"I can see ${Foo2.obj}") }
}
object Foo2 {
private val obj = "Foo's object"
}
object Driver2 extends App {
val f2 = new Foo2
f2.printObj
}
