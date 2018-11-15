class Foo {
private val secret = 2
}
object Foo {
// access the private class field 'secret'
def double(foo: Foo) = foo.secret * 2
}
object Driver extends App {
val f = new Foo
println(Foo.double(f)) // prints 4
}
