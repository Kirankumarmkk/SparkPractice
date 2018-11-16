package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger
import scala.io.Source
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object popularhero {
  def main(args: Array[String]) {

    import common._

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = utils.sc
    val ss = utils.ss
    val datadir = utils.datadir1

   // var herogrp: Map[Int, Int] = Map()

    val graph = sc.textFile(datadir + "Marvel-graph.txt")
    
    def countheroocc(line:String) = {
       val fields = line.split("\\s+")
      (fields(0).toInt,(fields.length - 1).toInt)
    }

    val graphrdd = graph.map(countheroocc)
    
    val rdd1 = graphrdd.reduceByKey((x, y) => (x + y)).map(x => (x._2, x._1))

    val max_comb_hero = rdd1.max

    println(max_comb_hero)

    val heronames = sc.textFile(datadir + "Marvel-names.txt")

    def parsenames(lines: String): Option[(Int, String)] = {
      val fields = lines.split('\"')
      if (fields.length > 1) {
        Some(fields(0).trim.toInt, fields(1))
      } else None
    }

    val herordd = heronames.flatMap(parsenames)

    val herolook = herordd.lookup(max_comb_hero._2)

    println(s"$herolook is the top hero with ${max_comb_hero._1}") 

  }
}