/**
 * 
 */
package html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import wordseg.Keywords;

/**
 * @author yicai
 *
 */
public class KeyWordProcess {
	public static String splitStr = "\\|\\|\\|";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcFilepath = System.getProperty("process.file.path");
		String desFilepath = srcFilepath.replaceFirst("process_file", "keyword_file");
		long startTime = System.currentTimeMillis();
		
		KeyWordProcess.processContextFile(200000, srcFilepath, desFilepath);//处理行数
		
		System.out.println("总耗时：" + (System.currentTimeMillis() - startTime) + "ms");
	}

	/**
	 * 把process文件处理成keyword文件
	 * 
	 * @param lineNum
	 * @param sfilePath
	 * @param dfilePath
	 */
	public static void processContextFile(int lineNum, String sfilePath, String dfilePath) {
		try {
			String encoding = "utf-8";
			File sfile = new File(sfilePath);
			File dfile = new File(dfilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter fw = new FileWriter(dfilePath, true);

				String lineTxt = null, newline = null;
				String htmlStr = "";
				boolean start = false, end = false;
				int count = 0;
				while ((lineTxt = bufferedReader.readLine()) != null && count < lineNum) {
					newline = getKeyWordLineStr(lineTxt);
					System.out.println(count + ":" + newline);
					if (newline != null)
						fw.write(newline + "\n");
					count++;
				}
				read.close();
				fw.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	/**
	 * 把|||格式的一行中的正文处理成关键词串
	 * 
	 * @param line
	 * @return
	 */
	private static String getKeyWordLineStr(String line) {
		String newline = null;
		String context, keywordStr;
		String str[] = line.split(KeyWordProcess.splitStr);
		if (str != null && str.length > 6) {
			context = str[6];
			keywordStr = Keywords.getKeywords(context);
			if (keywordStr.isEmpty())//关键词不能为空
				return null;
			newline = str[0] +FileProcess.splitStr +str[1] +FileProcess.splitStr + str[2] +FileProcess.splitStr +
					str[3] +FileProcess.splitStr + str[4] +FileProcess.splitStr + str[5] +FileProcess.splitStr 
					+keywordStr+FileProcess.splitStr ;
		}
		return newline;
	}

}
