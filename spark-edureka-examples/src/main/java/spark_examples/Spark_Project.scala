package spark_examples
object Spark_Project extends App {

  import common._
  import org.apache.spark.sql.functions._
  import org.apache.log4j.Logger
  import org.apache.log4j.Level;

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  val spSession = SparkCommonUtils.spSession
  val spContext = SparkCommonUtils.spContext
  val datadir = SparkCommonUtils.datadir

  //Load the CSV file into a RDD
  println("Loading data file :")
  //with Shell used HDFS as storage
  //with Eclipse used CSV file as storage
  
  val Call_Data = spContext.textFile("C:\\Kiran\\Big\\Scala\\Assignment\\CDR.CSV")
  
  println("Total Count of Records: " + Call_Data.count())

  import org.apache.spark.ml.linalg.{ Vector, Vectors }
  import org.apache.spark.ml.feature.LabeledPoint
  import org.apache.spark.sql.Row;
  import org.apache.spark.sql.types._
  //Schema for Data Frame
  
  val schema =
    StructType(
      StructField("Visitor_locn", LongType, false) ::
        StructField("Call_duration", LongType, false) ::
        StructField("Phone_no", LongType, false) ::
        StructField("Error_code", StringType, false) :: Nil)

 def transformToNumeric(inputStr: String): Row = {
    val attList = inputStr.split(",")

    //Filter out columns not wanted at this stage
    val values = Row(      
      attList(0).toLong,
      attList(1).toLong,
      attList(2).toLong,
      attList(3).toString)    
    return values
  }

  
  
  //Change to a Vector
  val tableVectors = Call_Data.map(transformToNumeric)
  
  println("with RDD Transformations") 
  
  tableVectors.map(x=>(x(2),1)).reduceByKey((x,y)=>(x+y)).map(_.swap).sortByKey(false).take(10).map(_.swap).foreach(println)
  
 println("With Spark SQL transformations")
  
  println("Transformed data in Data Frame")
  val tableDF = spSession.createDataFrame(tableVectors, schema)
  tableDF.printSchema()
  

  tableDF.createOrReplaceTempView("calldata")

  println("call data by Phone number")
  spSession.sql("select Phone_no,count(*) as Count from calldata where Error_code IS NOT NULL group by Phone_no order by count DESC").show(10)
   
}