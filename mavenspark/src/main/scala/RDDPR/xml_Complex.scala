package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger
import scala.xml.XML
import scala.io.Source

object xml_Complex extends App {

  import org.apache.spark._
  import org.apache.spark.SparkConf
  import org.apache.spark.SparkContext
  import org.apache.spark.sql.SQLContext
  import org.apache.spark.sql.SparkSession
  import com.databricks.spark.xml._
  import org.apache.spark.sql.types.{ StructType, StructField, StringType, DoubleType };
  import scala.xml._

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  System.setProperty("hadoop.home.dir", "C:\\winutils");

  val conf = new SparkConf().setAppName("kiran-xml").setMaster("local[2]")
    .set("spark.executor.memory", "2g")
    .set("spark.sql.shuffle.partitions", "2")

  val datadir = "C:\\Kiran\\Big\\GIT\\spark-edureka-examples\\data-files\\"

  val sc = SparkContext.getOrCreate(conf)

  val ss = SparkSession.builder().appName("kiran-xml").master("local[2]").config("spark.sql.warehouse.dir", "file:///c:/temp/spark-warehouse").getOrCreate()

  val sq = new org.apache.spark.sql.SQLContext(sc)

  import sq.implicits._

  case class meascollec(fileFormat: String, vendorName: String, elementtype: String, beginTime: String, userlabel: String, measinfoID: String, grant_duration: String, grant_endtime: String, rep_duration: String,
                        meastypes: String, measvalues: List[measvalues], foot_endtime: String)
  case class measvalues(measObjLdn: String, measresults: String)

  val xml = XML.loadFile(datadir + "test.xml")
  
  val rdd = sc.parallelize(xml)
  
  val rdd1 = rdd.map(x=>meascollecFile(x)).toDF.as[meascollec]
  
  rdd1.createOrReplaceTempView("measure")

    rdd1.show
    
  val measvalues_df = sq.sql("select measvalue,measvalue1 from measure lateral view explode(measvalues) mm as measvalue lateral view explode(measvalues) m1 as measvalue1") 
  
 measvalues_df.show
  
    def measValuesList(n: Node): measvalues = {
      val measObjLdn = (n \ "@measObjLdn").text
      val measresults = (n \ "measResults").text
      measvalues(measObjLdn, measresults)
    }
  
  def meascollecFile(xml:Node): meascollec = {
    val fileFormat = (xml \\ "@fileFormatVersion").text
    val vendorName = (xml \\ "@vendorName").text
    val elementtype = (xml \\ "@elementType").text
    val beginTime = (xml \\ "@beginTime").text
    val userlabel = (xml \\ "@userLabel").text
    val measinfoID = (xml \\ "@measInfoId").text
    val grant_duration = (xml \\ "@duration").text
    val grant_endtime = (xml \\ "@endTime").text
    val rep_duration = (xml \\ "@duration").text
    val meastypes = (xml \\ "measTypes").text
    val meas_values = (xml \\ "measValue").map(measValuesList).toList
    val foot_endtime = (xml \\ "@endTime").text

    meascollec(fileFormat, vendorName, elementtype, beginTime, userlabel, measinfoID, grant_duration, grant_endtime, rep_duration,
      meastypes, meas_values, foot_endtime)
  }
}   