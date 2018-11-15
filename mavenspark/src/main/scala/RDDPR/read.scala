package RDDPR

import org.apache.spark.sql.Row

object read extends App {
  import common._
  import org.apache.spark.sql.functions._
  import org.apache.log4j.Logger
  import org.apache.log4j.Level;
  import scala.xml.XML

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  val ss = utils.ss
  val sc = utils.sc
  val datadir = utils.datadir1

  import org.apache.spark.sql.SQLContext

  val sqlContext = new SQLContext(sc)

  val fileread = sc.textFile(datadir + "test.xml")

  val xml = fileread.map(transformToNumeric)

  xml.collect.foreach(println)

  def transformToNumeric(inputStr: String): Row = {
    val xml = XML.loadString(inputStr.replaceAll("\n", ""))
    val beginTime = (xml \\ "measCollecFile" \\ "fileHeader" \\ "measCollec" \ "@beginTime").text.replaceFirst("T", " ").substring(0, 19)
    // Filter content by cur and previous day
    val values = Row(beginTime)    
    return values
   }

}