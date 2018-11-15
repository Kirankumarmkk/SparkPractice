package RDDPR

import org.apache.log4j.Level
import org.apache.log4j.Logger

object parquet extends App {

 import common._

 Logger.getLogger("org").setLevel(Level.ERROR);
 Logger.getLogger("akka").setLevel(Level.ERROR);
 
 val sc=utils.sc
 val ss=utils.ss
 val datadir=utils.datadir1

 val fileread = ss.read.format("parquet").load(datadir+"fileparquet.parquet")
 
 fileread.show(1)
 
}