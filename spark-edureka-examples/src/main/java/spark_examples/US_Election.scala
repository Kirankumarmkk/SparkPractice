package spark_examples

import org.apache.spark.sql.types._
import org.apache.spark.storage.StorageLevel
import scala.io.Source
import scala.collection.mutable.HashMap
import java.io.File
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import scala.collection.mutable.ListBuffer
import org.apache.spark.util.IntParam
import org.apache.spark.util.StatCounter
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.rdd._
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.mllib.clustering.{ KMeans, KMeansModel }
import org.apache.spark.mllib.linalg.Vectors
import common.SparkCommonUtils
import org.apache.spark.sql.functions.lit

object US_Election extends App {
  val sc = SparkCommonUtils.spContext
  val spark = SparkCommonUtils.spSession
  val dataDir = SparkCommonUtils.datadir
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._
  import sqlContext._

  val schema = StructType(Array(
    StructField("state", StringType, true),
    StructField("state_abbr", StringType, true),
    StructField("county", StringType, true),
    StructField("fips", StringType, true),
    StructField("party", StringType, true),
    StructField("candidate", StringType, true),
    StructField("votes", IntegerType, true),
    StructField("fraction_votes", DoubleType, true)))

  val df = spark.read.option("header", "true").schema(schema).csv(dataDir + "primary_results.csv")
  val df_R = df.filter($"party" === "Republican")
  val df_D = df.filter($"party" === "Democrat")

  df_R.createOrReplaceTempView("election")
  val temp = spark.sql("SELECT * FROM election INNER JOIN (SELECT fips as b, MAX(fraction_votes) AS a FROM election GROUP BY fips) groupedtt WHERE election.fips = groupedtt.b AND election.fraction_votes = groupedtt.a")
  //temp.show()
  temp.createOrReplaceTempView("election1")

  val temp2 = spark.sql("SELECT state, state_abbr, county, fips, party, candidate, votes, fraction_votes FROM election1")
  val r_winner = temp2
  r_winner.createOrReplaceTempView("republican")
  val temp3 = spark.sql("select state, candidate, count(candidate) as countyswon from republican group by state, candidate")

  val d_state = temp3
  d_state.createOrReplaceTempView("state")

  val schema1 = StructType(Array(StructField("fips", StringType, true), StructField("area_name", StringType, true), StructField("state_abbreviation", StringType, true), StructField("Population_2014", IntegerType, true), StructField("Population_2010_Apr1", IntegerType, true), StructField("Change_in_Population_percent", DoubleType, true), StructField("Population_2010", IntegerType, true), StructField("Persons_under_5", DoubleType, true), StructField("Persons_under_18", DoubleType, true), StructField("Persons_65_years_over", DoubleType, true), StructField("Female_persons_percent", DoubleType, true), StructField("White_alone", DoubleType, true), StructField("Black_or_African_American_alone", DoubleType, true), StructField("American_Indian_and_Alaska_Native_alone", DoubleType, true), StructField("Asian_alone", DoubleType, true), StructField("Native_Hawaiian_and_Other_Pacific_Islander_alone", DoubleType, true), StructField("Two_or_More_Races", DoubleType, true), StructField("Hispanic_or_Latino", DoubleType, true), StructField("White_alone_not_Hispanic_or_Latino", DoubleType, true), StructField("Living_in_same_house_1_year_&_over", DoubleType, true), StructField("Foreign_born_persons", DoubleType, true), StructField("Language_other_than_English_spoken_at_home", DoubleType, true), StructField("High_school_graduate_or_higher", DoubleType, true), StructField("Bachelor_degree_or_higher", DoubleType, true), StructField("Veterans", IntegerType, true), StructField("Mean_travel_time_to_work", DoubleType, true), StructField("Housing_units", IntegerType, true), StructField("Homeownership_rate", DoubleType, true), StructField("Housing_units_in_multi_unit_structures", DoubleType, true), StructField("Median_value_of_owner_occupied_housing_units", IntegerType, true), StructField("Households", IntegerType, true), StructField("Persons_per_household", DoubleType, true), StructField("Per_capita_money_income", IntegerType, true), StructField("Median_household_income", IntegerType, true), StructField("Persons_below_poverty_level", DoubleType, true), StructField("Private_nonfarm_establishments", IntegerType, true), StructField("Private_nonfarm_employment", IntegerType, true), StructField("Private_nonfarm_employment_percentage_change", DoubleType, true), StructField("Nonemployer_establishments", IntegerType, true), StructField("Total_number_of_firms", IntegerType, true), StructField("Black_owned_firms", DoubleType, true), StructField("American_Indian_and_Alaska_Native_owned_firms", DoubleType, true), StructField("Asian_owned_firms", DoubleType, true), StructField("Native_Hawaiian_and_Other_Pacific_Islander_owned_firms", DoubleType, true), StructField("Hispanic_owned_firms", DoubleType, true), StructField("Women_owned_firms", DoubleType, true), StructField("Manufacturers_shipments", DoubleType, true), StructField("Merchant_wholesaler_sales", DoubleType, true), StructField("Retail_sales", DoubleType, true), StructField("Retail_sales_per_capita", IntegerType, true), StructField("Accommodation_and_food_services_sales", IntegerType, true), StructField("Building_permits", IntegerType, true), StructField("Land_area_in_square_miles", DoubleType, true), StructField("Population_per_square_mile", DoubleType, true)))

  val df1 = sqlContext.read.option("header", "true").schema(schema1).csv(dataDir+"county_facts.csv")
  df1.createOrReplaceTempView("facts")
  val temp11 = spark.sql("SELECT facts.fips as fips, republican.state as state, facts.state_abbreviation as state_abbreviation, area_name, candidate, Persons_65_years_over, Female_persons_percent, White_alone, Black_or_African_American_alone, Asian_alone, Hispanic_or_Latino, Foreign_born_persons, Language_other_than_English_spoken_at_home, Bachelor_degree_or_higher, Veterans, Homeownership_rate, Median_household_income, Persons_below_poverty_level, Population_per_square_mile FROM facts INNER JOIN republican ON CAST(facts.fips AS INT) = CAST(republican.fips AS INT)")
  val df_facts = temp11

  df_facts.createOrReplaceTempView("winner_facts")
  val bc = df_facts.filter($"candidate" === "Ben Carson")
  val dt = df_facts.filter($"candidate" === "Donald Trump")
  val jk = df_facts.filter($"candidate" === "John Kasich")
  val mr = df_facts.filter($"candidate" === "Marco Rubio")
  val tc = df_facts.filter($"candidate" === "Ted Cruz")
  val wbc = bc.withColumn("w_bc", lit(1)).withColumn("w_dt", lit(0)).withColumn("w_jk", lit(0)).withColumn("w_mr", lit(0)).withColumn("w_tc", lit(0))
  val wdt = dt.withColumn("w_bc", lit(0)).withColumn("w_dt", lit(1)).withColumn("w_jk", lit(0)).withColumn("w_mr", lit(0)).withColumn("w_tc", lit(0))
  val wjk = jk.withColumn("w_bc", lit(0)).withColumn("w_dt", lit(0)).withColumn("w_jk", lit(1)).withColumn("w_mr", lit(0)).withColumn("w_tc", lit(0))
  val wmr = mr.withColumn("w_bc", lit(0)).withColumn("w_dt", lit(0)).withColumn("w_jk", lit(0)).withColumn("w_mr", lit(1)).withColumn("w_tc", lit(0))
  val wtc = tc.withColumn("w_bc", lit(0)).withColumn("w_dt", lit(0)).withColumn("w_jk", lit(0)).withColumn("w_mr", lit(0)).withColumn("w_tc", lit(1))
  wbc.createOrReplaceTempView("wbc")
  wdt.createOrReplaceTempView("wdt")
  wjk.createOrReplaceTempView("wjk")
  wmr.createOrReplaceTempView("wmr")
  wtc.createOrReplaceTempView("wtc")
  val result1 = spark.sql("SELECT * FROM wbc UNION ALL SELECT * FROM wdt")
  result1.createOrReplaceTempView("result1")
  val result2 = spark.sql("SELECT * FROM wjk UNION ALL SELECT * FROM wmr")
  result2.createOrReplaceTempView("result2")
  val result3 = spark.sql("SELECT * FROM result1 UNION ALL SELECT * FROM result2")
  result3.createOrReplaceTempView("result3")

  val result = spark.sql("SELECT * FROM result3 UNION ALL SELECT * FROM wtc")
  result.createOrReplaceTempView("result")
  val featureCols = Array("Persons_65_years_over", "Female_persons_percent", "White_alone", "Black_or_African_American_alone", "Asian_alone", "Hispanic_or_Latino", "Foreign_born_persons", "Language_other_than_English_spoken_at_home", "Bachelor_degree_or_higher", "Veterans", "Homeownership_rate", "Median_household_income", "Persons_below_poverty_level", "Population_per_square_mile", "w_bc", "w_dt", "w_jk", "w_mr", "w_tc")
  val rows = new VectorAssembler().setInputCols(featureCols).setOutputCol("features").transform(result)
  val kmeans = new org.apache.spark.ml.clustering.KMeans().setK(4).setFeaturesCol("features").setPredictionCol("prediction")
  val model = kmeans.fit(rows)
  model.clusterCenters.foreach(println)
  /*
   * output 
   * 18/06/07 01:11:13 INFO Instrumentation: KMeans-kmeans_099e49435c2a-2111700021-1: training finished
[14.406030150753772,50.995979899497485,76.07688442211055,14.76381909547739,5.50603015075377,14.267839195979901,12.239195979899502,18.37688442211056,33.18693467336683,34322.59798994975,66.67286432160803,62834.994974874375,12.85678391959799,2039.9562814070352,0.0,0.7286432160804021,0.05527638190954774,0.08542713567839195,0.1306532663316583]
[13.899999999999999,50.83461538461539,71.81538461538462,13.75,8.880769230769232,25.815384615384612,18.561538461538465,29.58461538461538,30.369230769230775,127395.73076923078,61.36923076923077,57969.0,15.26153846153846,1570.6846153846157,0.0,0.7692307692307693,0.038461538461538464,0.038461538461538464,0.15384615384615385]
[18.497240051347898,49.96219512195122,82.73735558408202,12.341912708600786,0.6878690629011549,8.434659820282405,3.1988446726572546,8.007573812580226,15.251604621309372,2547.7278562259303,72.08485237483956,37618.31771501925,20.77920410783053,85.25590500641853,0.0012836970474967907,0.7817715019255456,0.010911424903722721,0.007060333761232348,0.19897304236200256]
[16.757659574468093,49.88500000000004,89.29244680851072,5.953829787234041,1.4126595744680854,9.18031914893617,4.667340425531914,9.068936170212766,21.99085106382979,5122.567021276596,73.60202127659569,52821.9,12.691170212765952,164.91648936170222,0.0,0.6542553191489362,0.03297872340425532,0.012765957446808512,0.3]
18/06/07 01:11:13 INFO SparkContext: Invoking stop() from shutdown hook
18/06/07 01:11:13 INFO SparkUI: Stopped Spark web UI at http://192.168.2.7:4040
   * 
   * 
   */
  
}