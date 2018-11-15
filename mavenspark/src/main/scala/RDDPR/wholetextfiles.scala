package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger

object wholetextfiles extends App{
  
  import common._
  
  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);
  
  val sc = utils.sc
  val sq=utils.sc
  val ss=utils.ss
  val datadir =utils.datadir1
  val filename:String = null
  val content:String =null
  
  
  
  val fileread = sc.wholeTextFiles(datadir+"auto-data-updated.csv", 2).
                flatMap(x=>x._2.split("\\n")).map(x=>(x.split(",")(0),x)).sortByKey(false).
                coalesce(1).saveAsTextFile(datadir+"auto-data-folder1.csv")
  
}

