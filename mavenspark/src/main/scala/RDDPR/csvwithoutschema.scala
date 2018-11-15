package RDDPR

object csvwithoutschema extends App {

  import common._
  import org.apache.log4j.Level
  import org.apache.log4j.Logger
  import org.apache.spark.rdd.RDD
  
  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  val sc = utils.sc
  val ss = utils.ss
  val conf = utils.conf
  val datadir = utils.datadir1
  val sqlcontext = utils.sqlContext
  
  val fileread = sc.textFile(datadir + "bank_withoutSchema.csv")

    import ss.implicits._

  
  val rdd1 = fileread.map(x=>x.split(";")).map(x=>(x(0).toInt,x(1).trim.toString)).toDF("age","job")
  val rdd2 = fileread.toDS
  
  println(rdd1.printSchema)
  println(rdd1.dtypes)
  println(rdd1.explain)
  
  
  println("DATASET")
  println(rdd2.printSchema)
  rdd1.show(10)
  rdd1.take(2).foreach(println)

    
}