package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger
import scala.xml._

object readorc extends App{
  
  import org.apache.spark._
  import org.apache.spark.{SparkConf,SparkContext}
  import org.apache.spark.sql.SparkSession
  import org.apache.spark.sql.SQLContext
  import org.apache.spark.sql._
  //import com.databricks.spark.xml
  import scala.xml._
   
  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)  
 
  System.setProperty("hadoop.home.dir", "C:\\winutils");
  
  val conf = new SparkConf().setAppName("pgm-1").setMaster("local[2]")      
                            .set("spark.executor.memory","2g")
                            .set("spark.sql.shuffle.partitions","2")
                           
  val sc = SparkContext.getOrCreate(conf)
  
  val ss = SparkSession.builder().appName("pgm-1").master("local[2]")
                       .config("spark.sql.warehouse.dir","file:///c:/temp/spark-warehouse.dir")
                       .getOrCreate()
  
  val sq = new org.apache.spark.sql.SQLContext(sc)                     
  
  import sq.implicits._
  
  val datadir1 = "C:\\Kiran\\Big\\GIT\\spark-edureka-examples\\data-files\\"
  
  val xml = XML.loadFile(datadir1+"books.xml")
 
  case class book(id:String,author:String,title:String,genre:String,price:String,publish_date:String)
  
  val load = (xml \\ "@id")
  
  def booktransform(n:Node):book = {
    val id = (n \ "@id").text
    val author = (n \ "author").text
    val title = (n \ "title").text
    val genre = (n \ "genre").text
    val price = (n \ "price").text
    val publish_date = (n \ "publish_date").text
    
    book(id,author,title,genre,price,publish_date)
  }
  
  val books = (xml \\ "book").map(booktransform).toArray
  
  val rdd = sc.parallelize(books)
  
  rdd.foreach(println)
  
  val df = rdd.toDF.as[book]
  
  df.createOrReplaceTempView("books")
  
  sq.sql("select * from books").show
  
}