package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger
import scala.io.Source
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object popular_movie {
      
      val datadir = "C:\\Kiran\\Big\\GIT\\spark-edureka-examples\\data-files\\"
      
  def movie_id_name: Map[Int, String] = {
      // Handle character encoding issues:

      var MovieNames: Map[Int, String] = Map()
            
      implicit val codec = Codec("UTF-8")
      codec.onMalformedInput(CodingErrorAction.REPLACE)
      codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

      val uitem = Source.fromFile(datadir + "u.item").getLines
      
      for (i <- uitem) {
       val fields = i.split('|')
       if (fields.length > 1) {
           MovieNames += (fields(0).toInt -> fields(1))
        }
      }
      MovieNames
      }
  
  
  def main(args: Array[String]): Unit = {

    import org.apache.spark._
    import org.apache.spark.{ SparkConf, SparkContext }
    import org.apache.spark.sql.SparkSession
    import org.apache.spark.sql.SQLContext

    Logger.getLogger("org").setLevel(Level.ERROR)

    System.setProperty("hadoop.home.dir", "C:\\winutils");

    val conf = new SparkConf().setAppName("Kiran-movie").setMaster("local[3]")
      .set("spark.executor.memory", "1g")
      .set("spark.sql.shuffle.partitions", "2")
      .set("spark.eventLog.enabled", "false")
      .set("spark.eventLog.dir", "file:///c:/temp/Logs")

    val sc = SparkContext.getOrCreate(conf)

    val ss = SparkSession.builder().appName("Kiran-movie").master("local[3]").config("spark.sql.warehouse.dir", "file:///c:/temp/spark-warehouse")
    

    val broadcast_movie = sc.broadcast(movie_id_name)

      val udata = sc.textFile(datadir + "u.data")

      val rdd1 = udata.map(x => (x.split("\t")(1), 1)).reduceByKey((x, y) => (x + y)).map(x => (x._2, x._1.toInt)).sortByKey(false)
       val rdd2 = rdd1.map(x => ((broadcast_movie.value(x._2)), x._1))

       println(" ")
       rdd2.take(10).foreach(println)

    //call methods


  }

}

