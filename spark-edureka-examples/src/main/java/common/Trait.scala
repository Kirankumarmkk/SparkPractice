package common

object Trait extends App {
  val iterator = new IntIterator(10)
  
  while(iterator.hasNext){
     println(iterator.next)
  }
}

trait Iterator[T] {
  def hasNext: Boolean
  def next: T
}

class IntIterator(value: Int) extends Iterator[Int] {
  private var current = 0
  
  override def hasNext: Boolean = {
    //current < value
    if (current < value) true else false
  }

  override def next: Int = {
    if (hasNext) {
      val temp = current
      current += 1
      temp // Return temp
    } else 0 // Else return 0
  }
}