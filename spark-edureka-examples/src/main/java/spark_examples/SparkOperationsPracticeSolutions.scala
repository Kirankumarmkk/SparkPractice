package spark_examples


object SparkOperationsPracticeSolutions extends App {
	
	import org.apache.log4j.Level
	import org.apache.log4j.Logger;
	import common._
	
	Logger.getLogger("org").setLevel(Level.ERROR);
	Logger.getLogger("akka").setLevel(Level.ERROR);
  	
  	val spContext = SparkCommonUtils.spContext
  	val datadir = SparkCommonUtils.datadir
  	
  	/*-----------------------------------------------------------------------------
	1. Your course resource has a CSV file "iris.csv". 
	Load that file into an RDD called irisRDD
	Cache the RDD and count the number of lines
	-----------------------------------------------------------------------------*/
	val irisData = spContext.textFile(datadir + "/iris.csv")
	irisData.cache()
	//Loads only now.
	irisData.count()
	irisData.first()
	irisData.take(5).foreach(println)
	
	/*-----------------------------------------------------------------------------
	2. Filter irisRDD for lines that contain "versicolor" and count them.
	-----------------------------------------------------------------------------*/
	val versiData=irisData.filter(x => x.contains("versicolor"))
	println("Total versicolor = " + versiData.count())
}