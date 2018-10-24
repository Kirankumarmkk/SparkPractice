package spark_examples

object Module_RDD extends App {

  import common._

  val sc = SparkCommonUtils.spContext

  val file = sc.textFile("C:\\Kiran\\Big\\Scala\\Assignment\\540_m5_cars.txt")
  
 case class cars(make:String,model:String,mpg:Int,Cylinders:Int,engine_disp:Int,horsepower:Int,weight:Int,accelerate:Double,year:Int,
origin:String)

val car_data = file.map(r=>r.split("\t")).map(x=>cars(x(0).toString,x(1).toString,x(2).toInt,x(3).toInt,x(4).toInt,x(5).toInt,x
(6).toInt,x(7).toDouble,x(8).toInt,x(9).toString))

car_data.take(2).foreach(println)

car_data.map(x=>(x.origin,1)).reduceByKey((x,y)=>x+y).collect.foreach(println)

val american_cars = car_data.filter(x=>(x.origin=="American"))

println(american_cars.count())

val makeWeightSum = american_cars.map(x=>(x.make,x.weight.toInt))

val display1 = makeWeightSum.collect.foreach(println)

val display2 = makeWeightSum.combineByKey(x=>(x:Int,1),(acc:(Int,Int),x)=>(acc._1+x,acc._2+1),(acc1:(Int,Int),acc2:(Int,Int))=>(acc1._1+acc2._1,acc1._2+acc2._2)).collect.foreach(println)




    
//case class travel(City_Pair:String,From:String,To:String,Prod_type:String,adult:Int,Senior:Int,Child:Int,youth:Int,infant:Int,timea:String,Timeb:String,air_price:Double,Car_price:Double,hotel_price:Double,airline:String,Air_name:String,Car_Vendor_code:String,Hotel_name:String)
//                        r0                r1          r2        r3              r4        r5          r6        r7        r8        r9            r10          r11              r12              r13                r14            r15              r16                  r17      
//val travel_recs = file.flatMap(r => r.split("\t")).map(r => (travel(r(0).toString,r(1).toString,r(2).toString,r(3).toString,r(4),r(5).toInt,r(6).toInt,r(7).toInt,r(8).toInt,r(9).toString,r(10).toString,r(11).toDouble,r(12).toDouble,r(13).toDouble,r(14).toString,r(15).toString,r(16).toString,r(17).toString)))

//val display_file = file.flatMap(r=>r.split("\t"))
//val travel_recs = display_file.map(r=>(travel(r(0).toString,r(1).toString,r(2).toString,r(3).toString,r(4).toInt,r(5).toInt,r(6).toInt,r(7).toInt,r(8).toInt,r(9).toString,r(10).toString,r(11).toDouble,r(12).toDouble,r(13).toDouble,r(14).toString,r(15).toString,r(16).toString,r(17).toString),1))
//val k1 = travel(To)


  

}
