package html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import entity.WeixinArticle;
import wordseg.Keywords;

public class PageParse {
	public static void main(String[] args) throws IOException {
		// 解析Url的信息
		// Validate.isTrue(args.length == 1, "http://news.ycombinator.com/..");
		// String url =
		// "http://mp.weixin.qq.com/s?__biz=MzAwODY4NzI0OA==&mid=400931102&idx=1&sn=0357e1e8a7ed1cc0e97facdd506fa620&scene=0#wechat_redirect";
		// String url =
		// "http://mp.weixin.qq.com/s?__biz=MjM5NDczNDYyNQ==&mid=400914097&idx=1&sn=8b1313e86d1b75b657e2d14a747927ae&scene=0#wechat_redirect";
		// String url =
		// "http://mp.weixin.qq.com/s?__biz=MjM5OTY1NzkxMA==&mid=400566164&idx=1&sn=82cee791f40ecb164c5a15109745c651&scene=0#wechat_redirect";

		// String url =
		// "http://mp.weixin.qq.com/s?__biz=MjM5MDA1OTg4MA==&mid=403009075&idx=1&sn=3af84dcf6b90986bc6a1255d661e8bc0&scene=0#wechat_redirect";
		// Document doc = Jsoup.connect(url).get();
		// parseDocumentforUrl(doc);

		// 解析文本信息
		// List<String> htmlList = getHtmlFromText(10);
		// for (String htmlItem : htmlList) {
		//// Document doc = Jsoup.parse(htmlItem);
		//// parseDocumentforTxt(doc);
		// getWeixinArticleFromStr(htmlItem);
		// System.out.println("========================================================\n");
		// }

		// 测试文本去除空行等
		// String text=
		// "http://mp.weixin.qq.com/s?__biz=MzAwODM0MDkzMQ==&mid=208652399&idx=1&sn=d4ab0bba343fbac15cd318284375d982&scene=6#rd|||茶叶的十大奇葩事？？？|||mlbwkan|||美灵宝微刊|||2015-10-12|||1444642161|||茶叶的十大奇葩事？？？
		// 喜欢本文请分享到朋友圈
		// 中国是古老的产茶国，一片小小的树叶，在历史的沉淀中也玩出了许多花样，快来和小编看看，关于茶叶，这十种你不知道的奇葩事儿！
		// 1.女儿茶采法最奇葩 始于清雍正七年即1927年的普洱金瓜贡茶，是选取西双版纳最好的女儿茶制成团茶上贡到朝廷，这种团茶又称人头茶。
		// 人头贡茶的原料，据说都是由未婚少女采摘的一级普洱芽茶，采摘后先置于怀中，积到一定数量才取出放到竹篓里，所以女儿茶的采法感觉超级奇葩。
		// 2.安吉白茶归属最奇葩 安吉白茶产于浙江省安吉县溪龙乡，之所以叫“白茶”是因为其加工原料采自一种嫩叶全为白色的茶树。
		// 但有意思的是，它的制法却完全是绿茶的套路，所以，哪怕它名称再嘻哈，她也是属于绿茶的行列。 3.碧螺春名字来源最奇葩
		// 碧螺春原本是不叫碧螺春，它有一个俗到爆的名字，叫“吓煞人香”，是形容其香气了得，香到“吓煞人”，小编也真是醉了！
		// 直到康熙三十八年，皇帝开南巡，品饮到这样的好茶，觉得其名字难登大雅之堂，于是依据其形状改名为“碧螺春”。 4.“油炸雀舌”吃法最奇葩
		// 乍一看这个标题，小编也吓一跳，阿弥陀佛，油炸雀舌多残忍，其实这里的“雀舌”是指的黄山毛峰中的极品，茶叶来的。
		// 将茶叶泡开沥干，放入油锅中炸炒并放入鸡蛋翻炒均匀，有没有像香葱炒蛋的做法哟？这一盘算下来也是蛮贵的，茶叶这样吃，也是奇葩够了！
		// 5.千两茶外形最奇葩
		// 做茶就做茶，不能安静做个小清新吗？千两茶就是这样一个大老粗，做成长条形的千两茶动辄上百斤，哪怕它很值钱，小偷也不会偷，因为，根本扛不动，
		// 6.虫屎茶混搭最奇葩 虫屎茶又名\"龙珠茶\"，是生活在广西、湖南、贵州三省区交界处的苗族、瑶族等少数民族喜欢饮用一种特种茶。
		// 少数民族的喜好真是有些大胆，虫屎、蜂蜜、茶叶这些看起来八竿子打不着的东西居然活在一起做成茶，小编表示接受无力。
		// 7.凤凰单丛，鸭屎香叫法最奇葩 凤凰单丛依据香型分类有十多种，什么芝兰香啦，肉桂香啦，但最奇葩的莫过于鸭屎香。
		// 茶本来是文艺高雅的东西，怎么用鸭屎这样的词语来形容茶香，这样真的好吗？ 8.九曲红梅，干茶细得最奇葩
		// 九曲红梅是红茶中珍品，产于钱塘江畔，风韵独特，色香味形俱佳。
		// 说到九曲红梅的奇葩之处，就在于它的干茶细若发丝，弯曲细紧如银钩互相勾在一起，也正是一副难分难舍的样子，够奇葩，小编服了！
		// 9.六安瓜片，原料最奇葩
		// 都说茶树芽头是精华，可六安瓜片就是这么任性，芽头不要！在采摘过程中去掉了芽和梗，所以六安瓜片是世界上唯一一种由单片叶子制成的茶，真奇葩！
		// 至于六安瓜片茶树的芽头到底去了哪儿，众说纷纭，有的说做成了黄芽茶有的说拿去提取高科技物质，甚至还有搞笑的朋友说是不是拿去泡脚了，也真是吓得小编我跑焦了。
		// 10.七子饼茶357g，重量最奇葩
		// 喝普洱茶的朋友都知道，市面上的七子饼茶都是357g一饼，小编第一次见到也觉得纳闷，好好的整数斤两不好吗？偏要整成357g。
		// ☟☟☟点击阅读原文|||";
		//
		// String res=PageParse.formatArticleContext(text);
		// System.out.println(res);
	}

	/**
	 * 将html源转换为WeixinArticle
	 * 
	 * @param html
	 * @return
	 */
	public static WeixinArticle getWeixinArticleFromStr(String html) {
		Document doc = Jsoup.parse(html);
		return parseDocumentforTxt(doc);
	}

	/**
	 * 使用方法 将html源转换为WeixinArticle 6月
	 * 
	 * @param doc
	 * @return
	 */
	public static WeixinArticle parseHtmlforTxtforJune(String html) {
		Element element;
		String weixinNickName, weixinId, postDate, ct, articleTitle, articleLink, articleContext, articleKeywords;
		String msg;
		Document doc = Jsoup.parse(html);

		// var 基础信息
		element = doc.select("body#activity-detail > script:nth-child(9)").first(); // css
																					// path:#activity-detail
																					// >
																					// script:nth-child(9)
		if (element == null)
			return null;
		try {
			msg = element.data();
			msg = deleteCRLFOnce(msg);// 去除空行
			String vars[] = msg.split("var");
			weixinNickName = getVarValue(vars, 8);
			ct = getVarValue(vars, 9);
			articleTitle = getVarValue(vars, 15);
			// articleTitle=formatArticleContext(articleTitle);
			articleTitle.replace(FileProcess.splitStr, "");

			articleLink = getVarValue(vars, 18);

			// 公众号
			element = doc.select("div#js_profile_qrcode>div> p:nth-child(3)> span").first();
			weixinId = element.text();

			// 发布日期
			element = doc.select("em#post-date").first();
			postDate = element.text();

			element = doc.select("div#js_content").first();
			articleContext = element.text();
			articleContext = formatArticleContext(articleContext);
			// articleKeywords =
			// Keywords.getKeywords(articleContext);//从文本中提取关键词

			if (isNull(weixinId) || isNull(weixinNickName)|| isNull(postDate)|| isNull(ct) || isNull(articleTitle)|| isNull(articleContext))//剔除信息缺失的文章
				return null;
			WeixinArticle weixinArticle = new WeixinArticle(weixinNickName, weixinId, postDate, ct, articleTitle,
					articleLink, articleContext);
			return weixinArticle;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 使用方法 将html源转换为WeixinArticle 10月
	 * 
	 * @param doc
	 * @return
	 */
	public static WeixinArticle parseHtmlforTxt(String html) {
		Element element;
		String weixinNickName, weixinId, postDate, ct, articleTitle, articleLink, articleContext, articleKeywords;
		String msg;
		Document doc = Jsoup.parse(html);

		// var 基础信息
		element = doc.select("body#activity-detail > script:nth-child(10)").first();
		if (element == null)
			return null;
		try {
			msg = element.data();
			msg = deleteCRLFOnce(msg);// 去除空行
			String vars[] = msg.split("var");
			weixinNickName = getVarValue(vars, 10);
			ct = getVarValue(vars, 11);
			articleTitle = getVarValue(vars, 17);
			// articleTitle=formatArticleContext(articleTitle);
			articleTitle.replace(FileProcess.splitStr, "");

			articleLink = getVarValue(vars, 20);

			// 公众号
			element = doc.select("div#js_profile_qrcode>div> p:nth-child(3)> span").first();
			weixinId = element.text();

			// 发布日期
			element = doc.select("em#post-date").first();
			postDate = element.text();

			element = doc.select("div#js_content").first();
			articleContext = element.text();
			articleContext = formatArticleContext(articleContext);
			// articleKeywords =
			// Keywords.getKeywords(articleContext);//从文本中提取关键词

			if (isNull(weixinId) || isNull(weixinNickName)|| isNull(postDate)|| isNull(ct) || isNull(articleTitle)|| isNull(articleContext))//剔除信息缺失的文章
				return null;
			WeixinArticle weixinArticle = new WeixinArticle(weixinNickName, weixinId, postDate, ct, articleTitle,
					articleLink, articleContext);
			return weixinArticle;
		} catch (Exception e) {
			return null;
		}

	}

	private static boolean isNull(String str) {
		if (str == null || str.trim().equals("") || str.isEmpty() || str == "")
			return true;
		else
			return false;
	}

	private static String formatArticleContext(String articleContext) {
		articleContext = articleContext.trim();
		articleContext = articleContext = deleteCRLFOnce(articleContext);
		articleContext = articleContext.replace(FileProcess.splitStr, "");
		articleContext = articleContext.replace("\n", "");
		articleContext = articleContext.replace("☟☟☟", "");
		articleContext = articleContext.replace("↓↓↓", "");
		articleContext = articleContext.replace("▼▼", "");
		articleContext = articleContext.replace("↑", "");
		articleContext = articleContext.replace("点击阅读原文", "");
		articleContext = articleContext.replace("扫一扫", "");
		articleContext = articleContext.replace("关注微信公众号", "");
		articleContext = articleContext.replace("点击“阅读原文”", "");
		articleContext = articleContext.replace("点击下方", "");
		articleContext = articleContext.replace("点击左下方", "");
		articleContext = articleContext.replace("点击右下方", "");
		articleContext = articleContext.replace("点击[关注]", "");
		articleContext = articleContext.replace("【阅读原文】", "");
		articleContext = articleContext.replace("“阅读原文”", "");
		articleContext = articleContext.replace("\"阅读原文\"", "");
		articleContext = articleContext.replace("阅读原文", "");
		return articleContext;
	}

	/**
	 * 暂时不用此方法 从原始文件中提取html string list
	 * 
	 * @param htmlNum
	 * @return
	 */
	public static List<String> getHtmlFromText(int htmlNum) {
		String filePath = "E:\\课程\\大数据系统基础\\项目\\weixin_page.2015-10-13\\weixin_page.2015-10-13";
		List<String> htmlList = new ArrayList<String>();

		// 读取文件
		try {
			String encoding = "utf-8";
			String startLine = "<BODY><!DOCTYPE html>";
			String endLine = "</html>";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String htmlStr = "";
				boolean start = false, end = false;
				int htmlCount = 0;
				while ((lineTxt = bufferedReader.readLine()) != null && htmlCount < htmlNum) {
					if (start && end) {
						// System.out.println(htmlStr);
						htmlList.add(htmlStr);
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
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return htmlList;
	}

	/**
	 * 从Document中提取信息转换成WeixinArticle
	 * 
	 * @param doc
	 * @return
	 */
	public static WeixinArticle parseDocumentforTxt(Document doc) {
		Element element;
		String weixinNickName, weixinId, postDate, ct, userName, articleTitle, articleDesc, articleLink, articleContext,
				articleKeywords, articleSourceUrl, sourceUsername;
		String msg;

		// var 基础信息
		element = doc.select("body#activity-detail > script:nth-child(10)").first();
		msg = element.data();
		msg = deleteCRLFOnce(msg);// 去除空行
		// System.out.println("msg:\n" + msg);
		String vars[] = msg.split("var");
		weixinNickName = getVarValue(vars, 10);
		ct = getVarValue(vars, 11);
		userName = getVarValue(vars, 12);
		articleTitle = getVarValue(vars, 17);
		articleDesc = getVarValue(vars, 18);
		articleLink = getVarValue(vars, 20);
		articleSourceUrl = vars[22].split("\'")[1];
		sourceUsername = getVarValue(vars, 32);

		// for (int i = 0; i < vars.length; i++) {
		// String var = vars[i].trim();
		// System.out.println("var[" + i + "]:" + var);
		// }

		// // 公众号名称
		// element = doc.select("a#post-user").first();
		// weixinNickName = element.text();
		// System.out.println("weixinNickName:" + weixinNickName);

		// 公众号
		element = doc.select("div#js_profile_qrcode>div> p:nth-child(3)> span").first();
		weixinId = element.text();

		// 发布日期
		element = doc.select("em#post-date").first();
		postDate = element.text();

		// 文章标题
		// element = doc.select("h2#activity-name").first();
		// articleTitle = element.text();

		// 文章内容js_content
		// articleContext=doc.body().text();
		element = doc.select("div#js_content").first();
		articleContext = element.text();
		articleContext = formatArticleContext(articleContext);
		articleKeywords = Keywords.getKeywords(articleContext);

		WeixinArticle weixinArticle = new WeixinArticle(weixinNickName, weixinId, userName, postDate, ct, articleTitle,
				articleDesc, articleLink, articleContext, articleKeywords, articleSourceUrl, sourceUsername);
		// WeixinArticle weixinArticle = new WeixinArticle(weixinNickName,
		// weixinId, null, postDate, null, articleTitle,
		// null, null, articleContext, null, null);
		// printWeixinArticle(weixinArticle);
		return weixinArticle;
	}

	/**
	 * 从url转化WeixinArticle 需要联网
	 * 
	 * @param doc
	 * @return
	 */
	public static WeixinArticle parseDocumentforUrl(Document doc) {
		Element element;
		String weixinNickName, weixinId, postDate, ct, userName, articleTitle, articleDesc, articleLink, articleContext,
				articleKeywords, articleSourceUrl, sourceUsername;
		String msg;

		// var 基础信息
		element = doc.select("body#activity-detail > script:nth-child(8)").first();
		msg = element.data();
		msg = deleteCRLFOnce(msg);// 去除空行
		// System.out.println("msg:\n" + msg);
		String vars[] = msg.split("var");
		weixinNickName = getVarValue(vars, 10);
		ct = getVarValue(vars, 11);
		userName = getVarValue(vars, 12);
		articleTitle = getVarValue(vars, 17);
		articleDesc = getVarValue(vars, 18);
		articleLink = getVarValue(vars, 20);
		articleSourceUrl = vars[22].split("\'")[1];
		sourceUsername = getVarValue(vars, 32);

		// for (int i = 0; i < vars.length; i++) {
		// String var = vars[i].trim();
		// System.out.println("var[" + i + "]:" + var);
		// }

		// // 公众号名称
		// element = doc.select("a#post-user").first();
		// weixinNickName = element.text();
		// System.out.println("weixinNickName:" + weixinNickName);

		// 公众号
		element = doc.select("div#js_profile_qrcode>div> p:nth-child(3)> span").first();
		weixinId = element.text();

		// 发布日期
		element = doc.select("em#post-date").first();
		postDate = element.text();

		// 文章标题
		// element = doc.select("h2#activity-name").first();
		// articleTitle = element.text();

		// 文章内容js_content
		// articleContext=doc.body().text();
		element = doc.select("div#js_content").first();
		articleContext = element.text();
		articleContext = formatArticleContext(articleContext);
		articleKeywords = Keywords.getKeywords(articleContext);

		WeixinArticle weixinArticle = new WeixinArticle(weixinNickName, weixinId, userName, postDate, ct, articleTitle,
				articleDesc, articleLink, articleContext, articleKeywords, articleSourceUrl, sourceUsername);
		printWeixinArticle(weixinArticle);
		return weixinArticle;
	}

	/**
	 * 打印weixinArticle 测试用
	 * 
	 * @param weixinArticle
	 */
	private static void printWeixinArticle(WeixinArticle weixinArticle) {
		System.out.println("weixinNickName:" + weixinArticle.getWeixinNickName() + "\nWeixinId："
				+ weixinArticle.getWeixinId() + "\nusername:" + weixinArticle.getUserName() + "\npostDate："
				+ weixinArticle.getPostDate() + "\nct：" + weixinArticle.getCt() + "\narticleTitle:"
				+ weixinArticle.getArticleTitle() + "\narticleDesc:" + weixinArticle.getArticleDesc() + "\narticleLink:"
				+ weixinArticle.getArticleLink() + "\narticleSourceUrl:" + weixinArticle.getArticleSourceUrl()
				+ "\nsourceUsername:" + weixinArticle.getSourceUsername() + "\nContext:"
				+ weixinArticle.getArticleContext() + "\nKeywords:" + weixinArticle.getArticleKeywords());
	}

	/**
	 * parseDocumentforTxt和parseDocumentforUrl的辅助方法
	 * 
	 * @param vars
	 * @param num
	 * @return
	 */
	private static String getVarValue(String vars[], int num) {
		String varSplits[] = vars[num].split("\"");
		if (varSplits.length > 0)
			return varSplits[1];
		else
			return null;
	}

	/**
	 * 去除空行
	 * 
	 * @param input
	 * @return
	 */
	private static String deleteCRLFOnce(String input) {
		if (input != null) {
			return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
		} else {
			return null;
		}
	}
}
