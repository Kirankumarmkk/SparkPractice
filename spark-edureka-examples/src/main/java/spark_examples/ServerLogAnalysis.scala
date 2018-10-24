package spark_examples

object ServerLogAnalysis extends App {
  import common._

  val spContext = SparkCommonUtils.spContext

  val file = spContext.textFile("C:\\Users\\epshcan\\Desktop\\ssss\\EDUREKA APACHE SPARK TRAINING\\Module 5\\Server_log_Sample\\server_log");
  
  //val file = spContext.textFile("hdfs://");
  
  val errors = file.filter(_.contains("ERROR"))

  //val errors1 = errors.map( _.split("\t")) // RDD[Array[String]]

  val errors1 = errors.map(_.split("\t")).map(r => r(1))
  
  errors1.cache()

  val count = errors1.filter(_.contains("php")).count()

  println("Count :" + count)
  
  val x = List(("Alice",1) , ("Prasad" ,2))
  spContext.parallelize(x,3)
}