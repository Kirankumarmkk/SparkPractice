package spark_examples
object Spark_Sql_Practice extends App {

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
  val airline_Data = spContext.textFile("C:\\Kiran\\Big\\Scala\\Assignment\\540_m6_airports.csv")
  
  //Remove the first line (contains headers)
  val dataLines = airline_Data.filter(x => !x.contains("AirportID"))
  println(dataLines.count())

  import org.apache.spark.ml.linalg.{ Vector, Vectors }
  import org.apache.spark.ml.feature.LabeledPoint
  import org.apache.spark.sql.Row;
  import org.apache.spark.sql.types._
  //Schema for Data Frame
  
  val schema =
    StructType(
      StructField("AirportID", IntegerType, false) ::
        StructField("Name", StringType, false) ::
        StructField("City", StringType, false) ::
        StructField("Country", StringType, false) ::
        StructField("FAA", StringType, false) ::
        StructField("ICAO", StringType, false) ::
        StructField("Latitude", DoubleType, false) ::  //7
        StructField("Longitude",DoubleType, false) ::  //8
        StructField("Altitude",FloatType, false) :://9
        StructField("Timezone",FloatType, false) :://10
        StructField("DST",StringType, false) ::
        StructField("Tz",StringType, false)    
        :: Nil)

 def transformToNumeric(inputStr: String): Row = {
    val attList = inputStr.split(",")

    //Filter out columns not wanted at this stage
    val values = Row(      
      attList(0).toInt,
      attList(1),
      attList(2),
      attList(3),
      attList(4),
      attList(5),
      attList(6).toDouble,
      attList(7).toDouble,
      attList(8).toFloat,
      attList(9).toFloat,
      attList(10),
      attList(11))
      
    return values
  }
        
        

  //Change to a Vector
  val tableVectors = dataLines.map(transformToNumeric)
  
  println("Transformed data in Data Frame")
  val tableDF = spSession.createDataFrame(tableVectors, schema)
  tableDF.printSchema()
  
  /*
   *  |-- AirportID: integer (nullable = false)
 |-- Name: string (nullable = false)
 |-- City: string (nullable = false)
 |-- Country: string (nullable = false)
 |-- FAA: string (nullable = false)
 |-- ICAO: string (nullable = false)
 |-- Latitude: double (nullable = false)
 |-- Longitude: double (nullable = false)
 |-- Altitude: float (nullable = false)
 |-- Timezone: float (nullable = false)
 |-- DST: string (nullable = false)
 |-- Tz: string (nullable = false)
   * 
   */
  
  tableDF.createOrReplaceTempView("airdata")
  
  //Southeast Cities
  println("Southeast cities: " + tableDF.filter(tableDF("Latitude")<0&&tableDF("Longitude")>0).count())
  
  
    spSession.sql("select Name,City,Country from airdata where Latitude<0 AND Longitude>0 ").show()
  
    println("unique Cities")
    spSession.sql("select Country,count(Distinct(City)) from airdata group by Country").show()
    
    println("AVG Altitude")    
    spSession.sql("select Country,AVG(Altitude) from airdata group by Country").show()
    
    println("Count of airports in each Time Zone")        
    spSession.sql("select Tz,count(AirportID) from airdata group by Tz").show()
    
        println("Country avg Latitude and Avg Longitude")
    spSession.sql("select Country,AVG(Latitude),AVG(Longitude) from airdata group by Country").show()

    println("Count of different DST's")
    spSession.sql("select DST,count(DST) from airdata group by DST").show()

    
}