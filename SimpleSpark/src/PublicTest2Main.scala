
import scopeA._
import scopeB._
object PublicTest2Main {
  def main(args: Array[String]): Unit = {
    val a = new PublicClass1();
    val b = new UsingClass(a);
    println(b.method)
  }
}