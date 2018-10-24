package spark_examples

import common.SparkCommonUtils
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating
import common._

object MovieLensALS extends App {

  val spSession = SparkCommonUtils.spSession
  val sc = SparkCommonUtils.spContext

  val rawData = sc.textFile("C:\\Users\\epshcan\\scala-workspace\\spark-edureka-examples\\data-files\\u.data")
  rawData.first()

  val rawRatings = rawData.map(_.split("\t").take(3))
  val ratings = rawRatings.map { case Array(user, movie, rating) => Rating(user.toInt, movie.toInt, rating.toDouble) }

  //train the data:-
  val model = ALS.train(ratings, 50, 5, 0.01)
  model.userFeatures.count
  model.productFeatures.count
  val predictedRating = model.predict(789, 123)
  val userId = 789
  val K = 10
  val topKRecs = model.recommendProducts(userId, K)
  
  println(topKRecs.mkString("\n"))

  val movies = sc.textFile("C:\\Users\\epshcan\\scala-workspace\\spark-edureka-examples\\data-files\\u.item")
  val titles = movies.map(line => line.split("\\|").take(2)).map(array => (array(0).toInt, array(1))).collectAsMap()
  titles(123)

  val moviesForUser = ratings.keyBy(_.user).lookup(789)

  println(moviesForUser.size)

  moviesForUser.sortBy(-_.rating).take(10).map(rating => (titles(rating.product), rating.rating)).foreach(x => println("Recommended Movie:-> " + x))
}