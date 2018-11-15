package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger

object csvread extends App {

  import common._

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  val ss = utils.ss
  val sc = utils.sc
  val conf = utils.conf
  val datadir = utils.datadir1

  val fileread = ss.read.option("header", "true").csv(datadir + "primary_results.csv")

  println(fileread.printSchema)

  val DF = fileread.createOrReplaceTempView("results")

  val readDF = ss.sql("select state,count(*) from results group by state")

  readDF.show(30)

}