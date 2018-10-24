
import scala.io.Source

import java.io._


object write {

def main (args: Array[String]) {

  val filename = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\test.txt"

println(filename)  

val writer = new PrintWriter(new File(filename))


      writer.write("Hello Edureka how are you?")
      
      writer.close()

   }

}

