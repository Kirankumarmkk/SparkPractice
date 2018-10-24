package spark_examples

import common.SparkCommonUtils

object Joins extends App {

  import org.apache.spark.SparkContext
  import org.apache.spark.SparkContext._
  import org.apache.spark.SparkConf
  import common._

  val sc = SparkCommonUtils.spContext //new SparkContext(new SparkConf().setAppName("Spark Joins").setMaster("local[2]"))

  case class Customer(cust_id: Int, name: String)
  
  case class Txn(cust_id: Int, store_id: String, amount: Float)

  val custs = sc.textFile("C:\\Users\\epshcan\\scala-workspace\\spark-edureka-examples\\data-files\\custs").map(_.split("\t"))
  // custs: org.apache.spark.rdd.RDD[Array[String]] = MapPartitionsRDD[2] at map at <console>:20

  val cust_recs = custs.map(r => (r(0).toInt, Customer(r(0).toInt, r(1))))
  // cust_recs: org.apache.spark.rdd.RDD[(Int, Customer)] = MapPartitionsRDD[3] at map at <console>:21

  val txns = sc.textFile("C:\\Users\\epshcan\\scala-workspace\\spark-edureka-examples\\data-files\\custs_txns").map(_.split("\t"))
  // txns: org.apache.spark.rdd.RDD[Array[String]] = MapPartitionsRDD[6] at map at <console>:19

  val txns_recs = txns.map(r => (r(0).toInt, Txn(r(0).toInt, r(1), r(2).toFloat)))
  // txns_recs: org.apache.spark.rdd.RDD[(Int, Txn)] = MapPartitionsRDD[7] at map at <console>:20

  println(txns_recs.toDebugString)

  /*
	 * The lines below are showing various types of joins
	 * which are as easy as using a keyword!
	 */
  val joind = cust_recs.join(txns_recs)

  val leftOuterjoind = cust_recs.leftOuterJoin(txns_recs)
  
  val cartesianJoined = cust_recs.cartesian(txns_recs)
  
  val cogrpd = cust_recs.cogroup(txns_recs)

  joind.foreach(println)
  //leftOuterjoind.foreach(println)
  //cartesianJoined.foreach(println)
  //cogrpd.foreach(println)
}