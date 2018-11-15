import java.io.File
import java.io.PrintWriter
import org.apache.spark._

import scala.io.Source

object wc {

  def main(args: Array[String]) {

    val appname = "Kiran-WC"
    val sparkMasterURL = "local[4]"
    //Create spark configuration object
    val conf = new SparkConf()
      .setAppName(appname)
      .setMaster(sparkMasterURL)

    val sc = SparkContext.getOrCreate(conf)

    val opFile = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\file_out_v1"

    val ipFile = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\hellospark_ip"

    val readf = Source.fromFile(ipFile).getLines.toArray

    val readfile = sc.textFile(ipFile)

    val count1 = readfile.count

     
    val wc1 = readfile.map(x => (x, 1)).reduceByKey((x,y)=>(x+y)).map(_.swap).coalesce(1)
    
    val isize = wc1.partitions.size
    
    println(isize)
    
    wc1.saveAsTextFile(opFile)
    
    println(wc1)
  
    
    
  //  pw.close

  }
}