package spark
import html._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * 将原始page文件处理成process_file文件
 */
object IntialFileProcess {
  val srcFilepath = System.getProperty("weixin.page.path");
  val hdfsdesFilepath = System.getProperty("hadoop.process.file.path");
  //val srcFilepath = "E:\\weixin_page.2015-10-13"
  val desFilepath = srcFilepath.replaceFirst("weixin_page", "process_file"); //FileProcess写入文件路径
  //val hdfsdesFilepath = ""; //hdfs最终存放文件路径
  val lineNum: Int = 200000

  // val source = "E:\\pagexml.txt"
  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("IntialFileProcess")
        val sc = new SparkContext(conf)
//    val sc = new SparkContext("local", "calltest", "E:\\spark\\spark-1.5.2-bin-hadoop2.6\\bin", null)

    //调用java类处理文件
    FileProcess.writeLineTxtFromSrcTextforJune(lineNum, srcFilepath, desFilepath)
    //将java类处理生成的process文件存入hdfs
    val input = sc.textFile("file://"+desFilepath)
    input.repartition(1).saveAsTextFile(hdfsdesFilepath)
  }
}