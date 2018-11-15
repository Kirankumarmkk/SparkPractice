package RDDPR

object json_hotel extends App{
  

  import common._
  import org.apache.spark.sql.functions._
  import org.apache.log4j.Logger
  import org.apache.log4j.Level;

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);
  
  val ss = utils.ss
  val sc = utils.sc
  val datadir = utils.datadir1
  val conf = utils.conf
  
  
  println(sc,ss,datadir)
  println(conf.toDebugString)
  val fileread = ss.read.json(datadir+"hotel.json")
  
  println(fileread.printSchema)
  
  val DF1 = fileread.createOrReplaceTempView("hotel")
  
  val cust = ss.sql("select * from hotel")
  
  cust.show(10)
  
  
}
 