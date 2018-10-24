package spark_examples

object WordCountSpark extends App {

  import common._

  val spContext = SparkCommonUtils.spContext

//  val file = spContext.textFile("D:\\TRAININGS\\EDUREKA APACHE SPARK TRAINING LATEST\\spark-edureka-examples\\data-files\\Server_log_Sample\\server_log")
    val file = spContext.textFile("C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\Server_log_Sample\\server_log")
   
  //val file1 = spContext.textFile("hdfs:////")
  
  file.flatMap(r => r.split("\t")).map { r => (r, 1) }.reduceByKey((x, y) => x + y).collect().foreach(println)
}