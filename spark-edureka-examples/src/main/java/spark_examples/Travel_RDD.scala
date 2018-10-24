package spark_examples

object Travel_RDD extends App {

  import common._

  val sc = SparkCommonUtils.spContext

  val file = sc.textFile("C:\\Kiran\\Big\\Scala\\Assignment\\TravelData.txt")
     
case class travel(City_Pair:String,From:String,To:String,Prod_type:String,adult:Int,Senior:Int,Child:Int,youth:Int,infant:Int,timea:String,Timeb:String,air_price:Double,Car_price:Double,hotel_price:Double)

//                        r0                r1          r2        r3              r4        r5          r6        r7        r8        r9            r10          r11              r12              r13                r14            r15              r16                  r17      

val travel_recs = file.map(r => r.split("\t")).map(r => (travel(r(0).trim.toString,r(1).trim.toString,r(2).trim.toString,r(3).trim.toString,r(4).trim.toInt,r(5).trim.toInt,r(6).trim.toInt,r(7).trim.toInt,r(8).trim.toInt,r(9).trim.toString,r(10).trim.toString,r(11).trim.toFloat,r(12).trim.toFloat,r(13).trim.toFloat)))


val top_destination = travel_recs.map(x=>(x.To,1)).reduceByKey((x,y)=>(x+y)).map(_.swap).sortByKey(false)

top_destination.take(20).foreach(println)


val top_undertaken_from = travel_recs.map(x=>(x.From,1)).reduceByKey((x,y)=>(x+y)).map(_.swap).sortByKey(false)

top_undertaken_from.take(20).foreach(println)


val top_airlines_city = travel_recs.map(x=>(x.City_Pair,x.air_price)).reduceByKey((x,y)=>(x+y)).map(_.swap).sortByKey(false)

top_airlines_city.take(20).foreach(println)

val count_lines = file.map(r=>r.split("\n"))
    
println(count_lines.count()) //to print lines ...2319

/*
 * Top 20 Destinations:
 * 
 * (396,MIA)
(290,SFO)
(202,LAS)
(162,LAX)
(102,DFW)
(64,DEN)
(57,ORD)
(54,PHL)
(50,IAH)
(45,JFK)
(44,PHX)
(40,FLL)
(36,ATL)
(31,BOS)
(31,MCO)
(27,SAN)
(25,WAS)
(24,CUN)
(22,AUS)
(22,LON)
 * 
 */

/*
 * Top 20 trips undertaken From Location
(504,DFW)
(293,MIA)
(272,LAS)
(167,BOM)
(131,SFO)
(101,ORD)
(72,LAX)
(55,DEN)
(41,PHL)
(37,IAH)
(35,FLL)
(33,PHX)
(31,JFK)
(24,WAS)
(19,HOU)
(19,ATL)
(18,DXB)
(17,SAN)
(17,BOS)
(17,BCN)
*/

/*
 * Top 20 Airline Prices for City Pair
 * (175974.59375,DFW-MLE)
(100890.54064941406,BOM-DFW)
(79388.14990234375,BOM-SFO)
(77901.69946289062,BOM-DEN)
(65448.400939941406,DFW-SFO)
(60448.699768066406,BOM-MIA)
(44756.770904541016,LAS-MIA)
(41027.319580078125,BOM-LAS)
(35724.000427246094,DFW-MIA)
(28171.19921875,LAS-AUH)
(18660.679977416992,LAS-LAX)
(18146.19921875,PHF-LGW)
(17462.400146484375,BOM-MEM)
(17168.19921875,MIA-AMS)
(17109.759887695312,BOM-ZRH)
(17043.7001953125,MIA-AUH)
(16428.320281982422,SFO-MIA)
(16343.7998046875,RIC-BGO)
(16304.30029296875,MIA-LAS)
(16206.0,BGO-IAH)
 */


}
