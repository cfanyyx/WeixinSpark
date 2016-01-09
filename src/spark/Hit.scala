package spark

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import io.lamma._

/**
 * Created by mymac on 15/11/3.
 */
object Hit {

  def main(args: Array[String]) = {
val dataPath = System.getProperty("hadoop.process.file.path");
val inPath = System.getProperty("weixin.click.path");
val hitNum = System.getProperty("hit.num");
val curDate = System.getProperty("hit.cur.date");

    val conf = new SparkConf().setAppName("assignment1")
    val sc = new SparkContext(conf)

    val data = sc.textFile(dataPath)
    val in =sc.textFile(inPath)
      
//please code here.
val doc= data.map{line=>line.split("\\|\\|\\|")}
val md = doc.map{n=>(n(0),n(2))}
val doc1=in.map{line=>line.split("\t")}
val md1=doc1.map{n=>(n(1),n(3),n(4),n(6))}
val md2= md1.map{case(n1,n2,n3,n4)=>(n1,(n2,n3,n4))}
val md3=md.join(md2)
val md4=md3.map{case(b1,(n2,(n3,n4,n5)))=>(n2,(n3,n4,n5))}
val md5= md4.map{case(n1,(n2,n3,n4))=>val num1=n2.toFloat;val num2=n3.toFloat;val num3=(io.lamma.Date(curDate) - io.lamma.Date(n4));(n1,(num1,num2,num3))}
val md6=md5.map{case(n1,(n2,n3,n4))=>val para=0.2*n2+0.8*n3-0.03*n4;(n1,para)}
val md7=md6.reduceByKey((a,b)=>a+b)
val md8=md7.sortBy(line=>line._2,false)
//val md9=md8.map{case(m,n)=>m}
md8.take(hitNum.toInt).foreach(println)
    
    sc.stop()
  }
}
