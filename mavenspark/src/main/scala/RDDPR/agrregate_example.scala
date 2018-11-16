package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger

object agrregate_example extends App{
 
  import common._
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = utils.sc
  val ss = utils.ss
  
  val datadir = utils.datadir1
  
  val file = sc.textFile(datadir+"aggregate")
  
  val rdd1 = file.map(x=>x.split("=")).map(x=>(x(0).trim,x(1).trim.toInt))
  
  val initial = 0
  val addop = (sum:Int,value:Int) => sum+value.toInt
  val mergeop = (p1:Int,p2:Int) => p1+p2
  val rdd2 = rdd1.aggregateByKey(initial)(addop,mergeop)
  
  rdd2.foreach(println)
  
  val createcomb = (v:Int)=> (v,1)
  val mergevalue = (acc1:(Int,Int),v:Int) => (acc1._1+v,acc1._2+1)
  val mergecombiner = (acc1:(Int,Int),acc2:(Int,Int)) => (acc1._1+acc2._1,acc1._2+acc2._2)
  
  val rddcombine = rdd1.combineByKey(createcomb,mergevalue,mergecombiner)
  
  val  avg = rddcombine.map(x=>(x._1,(x._2._1/x._2._2.toFloat)))
  
  println("=================================")
  rddcombine.foreach(println)
  
  avg.foreach(println)
  
  var mul = 10/4.0

  println(mul)
  
}