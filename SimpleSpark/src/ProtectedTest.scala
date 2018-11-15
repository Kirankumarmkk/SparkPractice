

package p {
  class Super {
    protected def f() {
      println("f")
    }
  }
  class Sub extends Super {
    f()
  }
  class Other {
    println("other")
//    (new Super).f()
  }
}