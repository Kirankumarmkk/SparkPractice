package spark_examples

object GraphExample1 extends App {

  import common._
  import org.apache.spark._
  import org.apache.spark.graphx._
  import org.apache.spark.rdd.RDD

  val spContext = SparkCommonUtils.spContext

  val vertexArray = Array(
    (1L, ("Alice", 28)),
    (2L, ("Bob", 27)),
    (3L, ("Charlie", 65)),
    (4L, ("David", 42)),
    (5L, ("Ed", 55)),
    (6L, ("Fran", 50)))

  val edgeArray = Array(
    Edge(2L, 1L, 7),
    Edge(2L, 4L, 2),
    Edge(3L, 2L, 4),
    Edge(3L, 6L, 3),
    Edge(4L, 1L, 1),
    Edge(5L, 2L, 2),
    Edge(5L, 3L, 8),
    Edge(5L, 6L, 3))

  val vertexRDD: RDD[(Long, (String, Int))] = spContext.parallelize(vertexArray)
  val edgeRDD: RDD[Edge[Int]] = spContext.parallelize(edgeArray)

  val graph: Graph[(String, Int), Int] = Graph(vertexRDD, edgeRDD)

  // Users whose age > 30.
  graph.vertices.filter { case (id, (name, age)) => age > 30 }.collect.foreach {
    case (id, (name, age)) => println(s"$name is $age")
  }

  val x = graph.triplets;

  //  Let us look at which person likes whom on Twitter.
  for (triplet <- graph.triplets.filter(t => t.attr > 5).collect) {
    println(s"${triplet.srcAttr._1} loves ${triplet.dstAttr._1}")
  }

  /**
   * Number of followers: Every user in our graph has a different number of followers.
   *  Let us look at all the followers for every user.
   */
  // Defining a class to more clearly model the user property
  case class User(name: String, age: Int, inDeg: Int, outDeg: Int)
  // Creating a user Graph
  val initialUserGraph: Graph[User, Int] = graph.mapVertices { case (id, (name, age)) => User(name, age, 0, 0) }

  // Filling in the degree information
  val userGraph = initialUserGraph.outerJoinVertices(initialUserGraph.inDegrees) {
    case (id, u, inDegOpt) => User(u.name, u.age, inDegOpt.getOrElse(0), u.outDeg)
  }.outerJoinVertices(initialUserGraph.outDegrees) {
    case (id, u, outDegOpt) => User(u.name, u.age, u.inDeg, outDegOpt.getOrElse(0))
  }
  for ((id, property) <- userGraph.vertices.collect) {
    println(s"User $id is called ${property.name} and is liked by ${property.inDeg} people.")
  }
  
}