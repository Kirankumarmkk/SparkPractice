

object json_read extends App {

  import org.apache.spark._
  import org.apache.spark.sql.functions._
  import org.apache.spark.SparkConf
  import org.apache.spark.SparkContext
  import org.apache.spark.sql.SQLContext
  import org.apache.spark.sql.SparkSession

  val appname = "kiran-pgm"
  val masterurl = "local[2]"

  //Temp dir required for Spark SQL
  val tempDir = "file:///c:/temp/spark-warehouse"

  var ss: SparkSession = null
  var sc: SparkContext = null

  //Need to set hadoop.home.dir to avoid errors during startup
  System.setProperty("hadoop.home.dir", "C:\\winutils");

  val conf = new SparkConf().setAppName(appname).setMaster(masterurl)
    .set("spark.executor.memory", "2g")
    .set("spark.sql.shuffle.partitions", "2")

  sc = SparkContext.getOrCreate(conf)

  ss = SparkSession
    .builder()
    .appName(appname)
    .master(masterurl)
    .config("spark.sql.warehouse.dir", tempDir)
    .getOrCreate()

  val datadir = "C:\\Kiran\\Big\\Scala\\spark-edureka-examples_mo2t1\\spark-edureka-examples\\data-files\\"

  val read_json = ss.read.json(datadir + "customerData.json")

  println(read_json.printSchema)

  read_json.show
  read_json.filter(read_json("age") > 30).groupBy("deptid").agg(avg("salary"), max("salary")).show()

  //read.JSON(datadir+"customerData.json")

}