package spark

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
object KeyWordCount {

  val keywordFile = System.getProperty("hadoop.keyword.file.path");
  val keywordSortFile = System.getProperty("hadoop.keyword.result.path");
  val keywordFrequency = System.getProperty("keyword.frequency");
  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("KeyWordCount")
    val sc = new SparkContext(conf)
    val data = sc.textFile(keywordFile)
    val test1 = data.map(line=>line.split("\\|\\|\\|"))
    val test2 = test1.map(line=>line(6))
    val test3 = test2.flatMap(line=>line.split("#"))
    val test4 = test3.map((_, 1)).reduceByKey(_ + _).sortBy(_._2, false)
    val test5 = test4.filter(x => x._2 > keywordFrequency.toInt) //词频>1的，可调
    test5.foreach(println) // 输出到屏幕上
    test5.saveAsTextFile(keywordSortFile)
    //test5.saveAsHadoopFile(path, keyClass, valueClass, outputFormatClass, codec)
    sc.stop()
  }
}

