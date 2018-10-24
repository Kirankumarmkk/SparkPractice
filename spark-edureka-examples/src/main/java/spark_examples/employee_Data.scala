package spark_examples

object employee_Data extends App {

  import common._

  val sc = SparkCommonUtils.spContext

  val file = sc.textFile("C:\\Kiran\\Big\\Scala\\Assignment\\540_m5_EMPSAL_v1.0.txt")
  
  
case class Emp(Name:String,Dept:String,designation:String,role_type:String,spoc:String,Salary:String)


val employee_data = file.map(r=>r.split("\t")).map(x=>Emp(x(0).trim.toString,x(1).trim.toString,x(2).trim.toString,x(3).trim.toString,x(4).trim.toString,x(5).trim.toString))

//employee_data.take(26).foreach(println)
  
  val formatter = java.text.NumberFormat.getCurrencyInstance
  
val dept_groupby_max = employee_data.map(x=>(x.Dept,formatter.parse(x.Salary).doubleValue())).reduceByKey(math.max(_,_)).collect.foreach(println)
val dept_groupby_min = employee_data.map(x=>(x.Dept,formatter.parse(x.Salary).doubleValue())).reduceByKey(math.min(_,_)).collect.foreach(println)
    


//dept_groupby_max.saveAsTextFile("C:\\Kiran\\Big\\Scala\\Assignment\\max_salary_emp.txt")

//dept_groupby_min.saveAsTextFile("C:\\Kiran\\Big\\Scala\\Assignment\\min_salary_emp.txt")

/*
 * Veterans Affairs Dept of,CompactBuffer(101124.0, 11669.0))
(Office Wisc Public Defender,CompactBuffer(40728.0))
(Health Services,CompactBuffer(40944.0, 16055.0, 70438.0))
(Corrections-Dept of,CompactBuffer(21434.0, 50367.0, 75074.0, 39439.0, 67520.0, 11254.0, 56970.0, 30322.0, 51200.0))
(WI Historical Society,CompactBuffer(3467.0))
(Transportation-Dept of,CompactBuffer(54856.0))
(Revenue-Dept of,CompactBuffer(37079.0))
(WI State Fair Park,CompactBuffer(699.0, 1256.0))
(Workforce Development-Dept,CompactBuffer(33167.0, 36584.0))
(Public Instruction,CompactBuffer(49786.0))
(Natural Resources-Dept of,CompactBuffer(76077.0, 6745.0))
 * 
 * 
 */


}
