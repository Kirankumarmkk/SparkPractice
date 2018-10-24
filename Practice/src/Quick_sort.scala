import scala.util.Sorting;

object Quick_sort extends App{
  
    val numbers:Array[Int] = Array(100,20,500,50,10,1,5);
    val fruits:Array[String]= Array("Mango","Apple","Sapota","Grapes","Banana");

      println("Before Sorting numbers:\n")
      numbers.foreach(i=>print(i+","))
      
      println("\n\nBefore Fruits:\n")
      fruits.foreach(i=>print(i+" ,"))
    
      scala.util.Sorting.quickSort(numbers);
      scala.util.Sorting.quickSort(fruits);
      println("\n\nAfter Sorted :\n")
      numbers.foreach(i=>print(i+","))
      println("Sorted Fruits:\n")
      fruits.foreach(i=>print(i+","))
      
/*
 * 
Result:

Before Sorting numbers:

100,20,500,50,10,1,5,

Before Fruits:

Mango ,Apple ,Sapota ,Grapes ,Banana ,

After Sorted :

1,5,10,20,50,100,500,Sorted Fruits:

Apple,Banana,Grapes,Mango,Sapota,
 * 
 */
      
      
      
    
}



  