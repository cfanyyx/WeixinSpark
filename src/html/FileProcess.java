/**
 * 
 */
package html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

import entity.WeixinArticle;

/**
 * @author cyc
 *
 */
public class FileProcess {
	/**
	 * 分隔符
	 */
	public static String splitStr = "|||";

	public static void main(String[] args) {
		String srcFilepath = System.getProperty("weixin.page.path");
//		String srcFilepath = "E:\\weixin_page6-20bug.txt";
		String desFilepath = srcFilepath.replaceFirst("weixin_page", "process_file");
//		String linefilePath = srcFilepath.replaceFirst("weixin_page", "line");
//		String pagefilePath = srcFilepath.replaceFirst("weixin_page", "context");
//		String copyfilePath = srcFilepath.replaceFirst("weixin_page", "weixin_page_copy10");
		
		long startTime = System.currentTimeMillis();
//		FileProcess.writeLineTxtFromSrcText(10000, srcFilepath, desFilepath);
		FileProcess.writeLineTxtFromSrcTextforJune(200000, srcFilepath, desFilepath);
//		PageParse.readAndWriteText(10, srcFilepath, copyfilePath);
		System.out.println("总耗时：" + (System.currentTimeMillis() - startTime) + "ms");
	}

	/**
	 * 
	 */
	public FileProcess() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将原始page文件处理成一个文章一行的文件 6月版
	 * 
	 * @param htmlNum
	 * @param sfilePath
	 * @param dfilePath
	 */
	public static void writeLineTxtFromSrcTextforJune(int htmlNum, String sfilePath, String dfilePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			String urlStartStr = "<URL>", urlEndStr = "</URL>";

			String startLine = "<BODY><!DOCTYPE html>";
			String endLine = "</html>";
			File sfile = new File(sfilePath);
			File dfile = new File(dfilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter fw = new FileWriter(dfilePath, true);

				String lineTxt = null;
				String htmlStr = "";
				String writeStr, articleUrl = "";
				boolean start = false, end = false;
				int htmlCount = 0;
				WeixinArticle weixinArticle;
				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					if (lineTxt.equals(urlStartStr) || lineTxt.contains(urlStartStr)) {
						articleUrl = lineTxt.replace(urlStartStr, "");
						articleUrl = articleUrl.replace(urlEndStr, "");
					}

					if (start && end) {

						// 处理html源代码 并写入新文件
						weixinArticle = PageParse.parseHtmlforTxtforJune(htmlStr);
						if (weixinArticle != null) {
							if (!articleUrl.equals("") || articleUrl != "") {
								weixinArticle.setArticleLink(articleUrl);
								articleUrl = "";

								writeStr = getLineStrforWeixinArticle(weixinArticle);
								fw.write(writeStr + "\n");
							}

						} else {
							System.out.println("null weixinArticle");
						}
						htmlStr = "";
						start = false;
						end = false;
					}

					if (start)
						htmlStr += lineTxt + "\n";
					if (!end && !start && (lineTxt.equals(startLine) || lineTxt.contains(startLine))) {
						start = true;
					} else if (start && !end && (lineTxt.equals(endLine) || lineTxt.contains(endLine))) {
						end = true;
						System.out.println(htmlCount);
						htmlCount++;
					}

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
	 * 将原始page文件处理成一个文章一行的文件 10-13
	 * 
	 * @param htmlNum
	 * @param sfilePath
	 * @param dfilePath
	 */
	public static void writeLineTxtFromSrcText(int htmlNum, String sfilePath, String dfilePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			String urlStartStr = "<URL>", urlEndStr = "</URL>";

			String startLine = "<BODY><!DOCTYPE html>";
			String endLine = "</html>";
			File sfile = new File(sfilePath);
			File dfile = new File(dfilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter fw = new FileWriter(dfilePath, true);

				String lineTxt = null;
				String htmlStr = "";
				String writeStr, articleUrl = "";
				boolean start = false, end = false;
				int htmlCount = 0;
				WeixinArticle weixinArticle;
				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					if (lineTxt.equals(urlStartStr) || lineTxt.contains(urlStartStr)) {
						articleUrl = lineTxt.replace(urlStartStr, "");
						articleUrl = articleUrl.replace(urlEndStr, "");
					}

					if (start && end) {

						// 处理html源代码 并写入新文件
						weixinArticle = PageParse.parseHtmlforTxt(htmlStr);
						if (weixinArticle != null) {
							if (!articleUrl.equals("") || articleUrl != "") {
								weixinArticle.setArticleLink(articleUrl);
								articleUrl = "";

								writeStr = getLineStrforWeixinArticle(weixinArticle);

								fw.write(writeStr + "\n");
							}

						} else {
							System.out.println("null weixinArticle");
						}
						htmlStr = "";
						start = false;
						end = false;
					}

					if (start)
						htmlStr += lineTxt + "\n";
					if (!end && !start && (lineTxt.equals(startLine) || lineTxt.contains(startLine))) {
						start = true;
					} else if (start && !end && (lineTxt.equals(endLine) || lineTxt.contains(endLine))) {
						end = true;
						System.out.println(htmlCount);
						htmlCount++;
					}

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

//	/**
//	 * 判断行格式是否规范 ArticleLink（文章url）|||ArticleTitle(标题)||| WeixinId（公众号id 唯一的）|||
//	 * WeixinNickName（微信号昵称）||| PostDate(日期)|Ct(发布时间戳)||| ArticleContext（文章正文）
//	 * 
//	 * @param line
//	 * @return
//	 */
//	private static boolean isLineFormatRight(String line) {
//		boolean result = false;
//		Pattern linePattern = Pattern.compile("\\S+\\|\\|\\|");
//		return result;
//	}

	/**
	 * 暂时不用此方法 将原始page文件处理成两个文件：一篇文章信息一行的文件 和 文章的xml文件
	 * 
	 * @param htmlNum
	 * @param sfilePath
	 * @param linefilePath
	 * @param pagefilePath
	 */
	public static void writeLineTxtandXmlPageFileFromSrcText(int htmlNum, String sfilePath, String linefilePath,
			String pagefilePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			String urlStartStr = "<URL>", urlEndStr = "</URL>";

			String startLine = "<BODY><!DOCTYPE html>";
			String endLine = "</html>";
			File sfile = new File(sfilePath);
			File linefile = new File(linefilePath);
			File pagefile = new File(pagefilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter linefw = new FileWriter(linefile, true);
				FileWriter pagefw = new FileWriter(pagefile, true);

				pagefw.append("<ROOT>\n");
				String lineTxt = null;
				String htmlStr = "";
				String writeStr, articleUrl = "";
				boolean start = false, end = false;
				int htmlCount = 0;
				WeixinArticle weixinArticle;
				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					if (lineTxt.equals(urlStartStr) || lineTxt.contains(urlStartStr)) {
						articleUrl = lineTxt.replace(urlStartStr, "");
						articleUrl = articleUrl.replace(urlEndStr, "");
					}

					if (start && end) {

						// 处理html源代码 并写入新文件
						weixinArticle = PageParse.parseHtmlforTxt(htmlStr);
						if (weixinArticle != null) {
							if (!articleUrl.equals("") || articleUrl != "") {
								weixinArticle.setArticleLink(articleUrl);
								articleUrl = "";
							}
							writeStr = getLineStrforWeixinArticle(weixinArticle, htmlCount);
							linefw.write(writeStr + "\n");
							pagefw.write("<node>\n<htmlId>" + htmlCount + "</htmlId>\n" + "<articleContext>"
									+ weixinArticle.getArticleContext().trim() + "</articleContext>\n</node>");

						} else {
							System.out.println("null weixinArticle");
						}
						htmlStr = "";
						start = false;
						end = false;
					}

					if (start)
						htmlStr += lineTxt + "\n";
					if (!end && !start && (lineTxt.equals(startLine) || lineTxt.contains(startLine))) {
						start = true;
					} else if (start && !end && (lineTxt.equals(endLine) || lineTxt.contains(endLine))) {
						end = true;
						System.out.println(htmlCount);
						htmlCount++;
					}

				}
				pagefw.append("</ROOT>");
				read.close();
				linefw.close();
				pagefw.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	/**
	 * 暂时不用此方法 将原始page文件处理成xml格式文件
	 * 
	 * @param htmlNum
	 * @param sfilePath
	 * @param dfilePath
	 */
	public static void writeXmlTxtFromSrcText(int htmlNum, String sfilePath, String dfilePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			String urlStartStr = "<URL>", urlEndStr = "</URL>";

			String startLine = "<BODY><!DOCTYPE html>";
			String endLine = "</html>";
			File sfile = new File(sfilePath);
			File dfile = new File(dfilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter fw = new FileWriter(dfilePath, true);

				String lineTxt = null;
				String htmlStr = "";
				String writeStr, articleUrl = "";
				boolean start = false, end = false;
				int htmlCount = 0;

				fw.write("<ROOT>\n");

				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					if (lineTxt.equals(urlStartStr) || lineTxt.contains(urlStartStr)) {
						articleUrl = lineTxt.replace(urlStartStr, "");
						articleUrl = articleUrl.replace(urlEndStr, "");
					}

					if (start && end) {
						System.out.println(htmlStr);
						// 处理html源代码 并写入新文件
						WeixinArticle weixinArticle = PageParse.getWeixinArticleFromStr(htmlStr);
						if (!articleUrl.equals("") || articleUrl != "") {
							weixinArticle.setArticleLink(articleUrl);
							articleUrl = "";
						}
						writeStr = getXMLStrforWeixinArticle(weixinArticle);

						fw.write(writeStr + "\n");

						htmlStr = "";
						start = false;
						end = false;
					}

					if (start)
						htmlStr += lineTxt + "\n";
					if (!end && !start && (lineTxt.equals(startLine) || lineTxt.contains(startLine))) {
						start = true;
					} else if (start && !end && (lineTxt.equals(endLine) || lineTxt.contains(endLine))) {
						end = true;
						htmlCount++;
					}

				}
				fw.write("</ROOT>");
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
	 * 暂时不用此方法 把WeixinArticle转换成一行的字符串 ，不包括正文
	 * 
	 * @param weixinArticle
	 * @param htmlnum
	 * @return
	 */
	private static String getLineStrforWeixinArticle(WeixinArticle weixinArticle, int htmlnum) {
		String lineStr;

		// 不包括正文
		lineStr = htmlnum + splitStr + weixinArticle.getArticleLink() + splitStr + weixinArticle.getArticleTitle()
				+ splitStr + weixinArticle.getWeixinId() + splitStr + weixinArticle.getWeixinNickName() + splitStr
				+ weixinArticle.getPostDate() + splitStr + weixinArticle.getCt();
		return lineStr;
	}

	/**
	 * 把WeixinArticle转换成一行的字符串，用分隔符拼接
	 * 
	 * @param weixinArticle
	 * @return
	 */
	private static String getLineStrforWeixinArticle(WeixinArticle weixinArticle) {
		String lineStr;
		// lineStr = weixinArticle.getArticleLink() + splitStr +
		// weixinArticle.getArticleTitle() + splitStr
		// + weixinArticle.getWeixinId() + splitStr +
		// weixinArticle.getWeixinNickName() + splitStr
		// + weixinArticle.getArticleKeywords() + splitStr +
		// weixinArticle.getArticleDesc() + splitStr
		// + weixinArticle.getPostDate() + splitStr + weixinArticle.getCt() +
		// splitStr
		// + weixinArticle.getUserName() + splitStr +
		// weixinArticle.getArticleSourceUrl() + splitStr
		// + weixinArticle.getSourceUsername();

		lineStr = weixinArticle.getArticleLink() + splitStr + weixinArticle.getArticleTitle() + splitStr
				+ weixinArticle.getWeixinId() + splitStr + weixinArticle.getWeixinNickName() + splitStr
				+ weixinArticle.getPostDate() + splitStr + weixinArticle.getCt() + splitStr
				+ weixinArticle.getArticleContext() + splitStr;
		return lineStr;
	}

	private static String getXMLStrforWeixinArticle(WeixinArticle weixinArticle) {
		String xmlStr;
		xmlStr = "<WeixinArticle>\n" + "<url>" + weixinArticle.getArticleLink() + "</url>\n" + "<weixinNickName>"
				+ weixinArticle.getWeixinNickName() + "</weixinNickName>\n" + "<weixinId>" + weixinArticle.getWeixinId()
				+ "</weixinId>\n" + "</WeixinArticle>";
		return xmlStr;
	}

	/**
	 * 处理html源代码 将weixinArticle 转化成XML格式的字符串
	 * 
	 * @param htmlStr
	 * @return
	 */
	private static String getXMLStrforHtml(String htmlStr) {
		String xmlStr;
		WeixinArticle weixinArticle = PageParse.getWeixinArticleFromStr(htmlStr);
		xmlStr = "<WeixinArticle>" + "<url>" + weixinArticle.getArticleLink() + "</url>\n" + "<weixinNickName>"
				+ weixinArticle.getWeixinNickName() + "</weixinNickName>\n" + "<weixinId>" + weixinArticle.getWeixinId()
				+ "</weixinId>\n" + "</WeixinArticle>";
		return xmlStr;
	}

	/**
	 * 读取原始page文件，取前几条copy到新文件
	 * 
	 * @param htmlNum
	 * @param filePath
	 */
	public static void readAndWriteText(int htmlNum, String sfilePath, String dfilePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			String startLine = "<URL>";
			String endLine = "</ID>";
			File sfile = new File(sfilePath);
			File dfile = new File(dfilePath);
			if (sfile.isFile() && sfile.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(sfile), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				FileWriter fw = new FileWriter(dfilePath, true);

				String lineTxt = null;
				String htmlStr = "";
				boolean start = false, end = false;
				int htmlCount = 0;
				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					fw.write(lineTxt + "\n");

					if (start && end) {
						htmlStr = "";
						start = false;
						end = false;
					}

					if (start)
						htmlStr += lineTxt + "\n";
					if (!end && !start && (lineTxt.equals(startLine) || lineTxt.contains(startLine))) {
						start = true;
					} else if (start && !end && (lineTxt.equals(endLine) || lineTxt.contains(endLine))) {
						end = true;
						System.out.println(htmlCount);
						htmlCount++;

					}

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
	 * 放弃此方法 将原始文件转换成xml格式，添加root结点
	 * 
	 * @param filePath
	 */
	public static void FormatTextToXml(String filePath) {
		// 读取文件
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
				// 将写文件指针移到文件头
				raf.seek(0);
				raf.writeBytes("<root><URL>");
				raf.close();

				FileWriter fw = new FileWriter(filePath, true);

				fw.write("</root>");
				fw.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}
}
