import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object FileContentCount {
  
  def main(args : Array[String]) {
    if(args.length != 1) 
      println("Usage : spark-submit --class FileContentCount <path to jar>/file-content-count_2.10-1.0.jar <file to read with full path>")
     else {
       val sConf = new SparkConf().setAppName("File Length Count App").setMaster("local")
       val sc = new SparkContext(sConf)
       val textFile = sc.textFile(args(0), 1)
       println("Num of lines in File : "+args(0)+" = "+textFile.count)
       sc.stop()
     } 
  }

}