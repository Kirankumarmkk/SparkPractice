package spark_examples
object SparkMlRecommendations extends App {

  import common._
  import org.apache.spark.sql.functions._
  import org.apache.log4j.Logger
  import org.apache.log4j.Level;

  Logger.getLogger("org").setLevel(Level.ERROR);
  Logger.getLogger("akka").setLevel(Level.ERROR);

  val spSession = SparkCommonUtils.spSession
  val spContext = SparkCommonUtils.spContext
  val datadir = SparkCommonUtils.datadir

  //Load the CSV file into a RDD
  println("Loading data file :")
  val ratingsData = spContext.textFile(datadir + "useritemdata.txt")
  ratingsData.cache()
  ratingsData.take(5)

  //Convert the RDD into a Dense Vector. As a part of this exercise
  //   1. Change labels to numeric ones

  import org.apache.spark.ml.linalg.{ Vector, Vectors }
  import org.apache.spark.ml.feature.LabeledPoint
  import org.apache.spark.sql.Row;
  import org.apache.spark.sql.types._

  //Schema for Data Frame
  val schema =
    StructType(
      StructField("user", IntegerType, false) ::
        StructField("item", IntegerType, false) ::
        StructField("rating", DoubleType, false) :: Nil)

  def transformToNumeric(inputStr: String): Row = {
    val attList = inputStr.split(",") //1001,9001,10

    //Filter out columns not wanted at this stage
    val values = Row(attList(0).toInt,
      attList(1).toInt,
      attList(2).toDouble)
    return values
  }

  //Change to a Vector
  val ratingsVectors = ratingsData.map(transformToNumeric)
  // org.apache.spark.rdd.RDD[org.apache.spark.sql.Row] 

  ratingsVectors.collect()
  // res36: Array[org.apache.spark.sql.Row] = Array([1001,9001,10.0], [1001,9002,1.0], ...

  println("Transformed data in Data Frame")
  val ratingsDf = spSession.createDataFrame(ratingsVectors, schema)
  ratingsDf.printSchema()
  ratingsDf.show(5)

  //Split into training and testing data
  /**
   *  I would recommend that if you have a reasonable amount of data that you split into three data sets,
   *  "training" which you run the ML algorithms on; "test", which you use to check how your
   *  training is going; and "validation" which you never use until you think your entire ML process is optimized.
   */
  val Array(trainingData, testData) = ratingsDf.randomSplit(Array(0.9, 0.1))
  // trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row]
  // testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] 

  trainingData.count()
  testData.count()

  import org.apache.spark.ml.recommendation.ALS
  // https://spark.apache.org/docs/1.1.0/api/java/org/apache/spark/mllib/recommendation/ALS.html
  
  // Build the recommendation model using ALS on the training data.
  val als = new ALS()
  // als: org.apache.spark.ml.recommendation.ALS 
  
  als.setRank(10) // Set the rank of the feature matrices computed (number of features). Default: 10.
  als.setMaxIter(5) // Max no of iterations to run.
  als.setUserCol("user")
  als.setItemCol("item")
  als.setRatingCol("rating")
  
  val model = als.fit(trainingData)
  // model: org.apache.spark.ml.recommendation.ALSModel = als_31deb09f73f6

  val predictions = model.transform(testData)
  
  println("Recommendation scores:")
  predictions.select("user", "item", "prediction").show()

}