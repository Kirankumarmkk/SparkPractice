
package common

object utils {

  import org.apache.spark._
  import org.apache.spark.SparkConf
  import org.apache.spark.SparkContext
  import org.apache.spark.sql.SparkSession
  import org.apache.spark.sql.functions._
  import org.apache.spark.sql.SQLContext
  

  val appname = "Kiran-app"
  val masterURL = "local[2]"
  val datadir = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\"

  val datadir1 = "C:\\Kiran\\Big\\GIT\\spark-edureka-examples\\data-files\\"

  //Temp dir required for Spark SQL
  val tempDir = "file:///c:/temp/spark-warehouse"

  //Need to set hadoop.home.dir to avoid errors during startup

  var ss: SparkSession = null
  var sc: SparkContext = null
  var conf: SparkConf = null
  var sqlContext:SQLContext =null

  {
    System.setProperty("hadoop.home.dir", "C:\\winutils");

    conf = new SparkConf()
      .setAppName(appname)
      .setMaster(masterURL)
      .set("spark.executor.memory", "2g")
      .set("spark.sql.shuffle.partitions", "2")
      .set("spark.eventLog.enabled","false")
      .set("spark.eventLog.dir","file:///c:/temp/Logs")

    sc = SparkContext.getOrCreate(conf)

    ss = SparkSession.builder().appName(appname).master(masterURL).config("spark.sql.warehouse.dir", tempDir).getOrCreate()

    sqlContext = new org.apache.spark.sql.SQLContext(sc)
  
  }
 
}