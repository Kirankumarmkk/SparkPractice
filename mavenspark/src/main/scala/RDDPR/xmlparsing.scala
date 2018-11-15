package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger

object xmlparsing extends App{
  
  import org.apache.spark._
  import org.apache.spark.SparkConf
  import org.apache.spark.SparkContext
  import org.apache.spark.sql.SQLContext
  import org.apache.spark.sql.SparkSession
  import com.databricks.spark.xml._
  import org.apache.spark.sql.types.{StructType, StructField, StringType, DoubleType};
  
  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);
 
  System.setProperty("hadoop.home.dir", "C:\\winutils");
    
  val conf = new SparkConf().setAppName("kiran-xml").setMaster("local[2]")
                  .set("spark.executor.memory","2g")
                  .set("spark.sql.shuffle.partitions","2")
                  
  val datadir = "C:\\Kiran\\Big\\GIT\\spark-edureka-examples\\data-files\\"
                  
  val sc = SparkContext.getOrCreate(conf)
  
  val ss = SparkSession.builder().appName("kiran-xml").master("local[2]").config("spark.sql.warehouse.dir","file:///c:/temp/spark-warehouse").getOrCreate()
  
  val sq = new org.apache.spark.sql.SQLContext(sc)                
  
  val xmlread = sq.read.format("com.databricks.spark.xml").option("rowtag","book").load(datadir+"books.xml")
  
  println(xmlread.printSchema)
  
  /*
 |-- _id: string (nullable = true)
 |-- author: string (nullable = true)
 |-- description: string (nullable = true)
 |-- genre: string (nullable = true)
 |-- price: double (nullable = true)
 |-- publish_date: string (nullable = true)
 |-- title: string (nullable = true)
   
   */
  
  import sq.implicits._
  val df = xmlread.createOrReplaceTempView("books") 
  
  val df2 = sq.sql("select _id,author,genre,price,publish_date,title from books")
  
  df2.persist()
  
  val df3 = df2.rdd
  
  import org.apache.spark.sql.Row
  
  val df3_1 = df3.map(x=>x.mkString("\t")).collect.foreach(println)

  
  //.saveAsTextFile(datadir+"xml_v1")
  df2.unpersist()
 
  
  
  
}