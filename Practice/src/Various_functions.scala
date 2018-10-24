

object Various_functions extends App{
  /*
	120 Inch to Feet : 10.0
	5 Feet to Inch: 60.0

	1 Miles to KMS : 1.60934
	2 KMS to Miles : 1.2427454732996135

	300 pounds to KgS : 136.0776
	93 KgS to pounds : 205.03007107709132
   
   */
  
  
  var c1 = new inch_feet()
  var c2 = new mile_kms()
  var c3 = new pound_kgs()
}


class inch_feet{  
  def inch_to_feet(inch:Double) = {
    var feet = inch/12
    
    feet
  }     

    def feet_to_inch(feet:Double) = {
    var inch = feet* 12
    
    inch
  }     
  
    var inches = 120
    var feets = 5
    
    
  println(inches+" Inch to Feet : "+inch_to_feet(inches))
  println(feets+" Feet to Inch: "+ feet_to_inch(feets))
  
} 
  
class mile_kms{
  
  def miletokms(mile:Double) = {
    var kms = mile*1.60934
    kms  
  }
  
  def kmstomile(kms:Double) = {
    var mile = kms/1.60934
    mile    
  }
  var kilom = 2
  var miles= 1
  
  println
  println(miles+" Miles to KMS : "+miletokms(miles))
  println(kilom+" KMS to Miles : "+kmstomile(kilom))
  
}

class pound_kgs{
  
  def poundtokgs(pound:Double) = {
    var kgs = pound*0.453592
    kgs  
  }
  
  def kgstopound(kgs:Double) = {
    var pound = kgs/0.453592
    pound    
  }
  var kilogms = 93
  var pounds= 300
  
  println
  println(pounds+" pounds to KgS : "+poundtokgs(pounds))
  println(kilogms+" KgS to pounds : "+kgstopound(kilogms))
  
}


