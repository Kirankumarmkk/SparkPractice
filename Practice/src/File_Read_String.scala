import scala.io.Source.fromFile 
import java.lang.String

/* Input Order 

 Hi, How are you?
 Are you Joining Edureka12345?
 How is the trainingsinHadoop?
 What is the Spark?
 What is ScalaScalaScala?
 
 Reverse Order 

data :
What is ScalaScalaScala?
What is the Spark?
How is the trainingsinHadoop?
Are you Joining Edureka12345?
Hi, How are you?
 
Greater than 10.
 
Edureka12345 trainingsinHadoop ScalaScalaScala
 * 
 */

object File_Read_String{
   def main(args: Array[String]){
   
val filename = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\fileread.txt"


val fileContents = io.Source.fromFile(filename).getLines.toArray
val fileContent1 = io.Source.fromFile(filename).getLines.toArray

println(" Normal Order \n")



for(lines<-io.Source.fromFile(filename).getLines)
{

  println(" "+lines)
 
}
   println(" ")
   
println(" Reverse Order \n")

val data1 = fileContents.reduceLeft((a,b)=> (b+"\n"+a))

println("data :\n"+data1)
  println(" ")

  for(words<-fileContent1)
{

val S1 = words.toString()
    
  S1.mkString
 
 val S2= S1.mkString.split("\\W+")

 S2.foreach((i)=> if(i.length > 10) print(" "+i))

}
   println(" ")
  
  
   }
  
}