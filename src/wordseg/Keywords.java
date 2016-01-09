package wordseg;

import com.sun.jna.Library;
import com.sun.jna.Native;
public class Keywords {
//	String argu = "";
//	String system_charset = "GBK";
//	CLibrary.Instance.NLPIR_Init("", charset_type, "0");
	
	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(
				"/home/ubuntu/hadoop_test/libNLPIR.so", CLibrary.class);
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);

		public int NLPIR_Init(byte[] sDataPath, int encoding,
				byte[] sLicenceCode);

		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);

		public int NLPIR_AddUserWord(String sWord);// add by qp 2008.11.10

		public int NLPIR_DelUsrWord(String sWord);// add by qp 2008.11.10

		public void NLPIR_Exit();
	}

 

	// 提取关键词串，结果格式为：阻尼器#地震#摇晃#大楼#阻尼#台湾#台北#
	public static String getKeywords2(String sInput) {
		String argu = "";
		String system_charset = "GBK";// GBK----0
		int charset_type = 1;
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return null;
		}

		String nativeBytes = null;
		String nativeByte = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			System.out.println("分词结果为： " + nativeBytes);

			nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10, false);

			CLibrary.Instance.NLPIR_Exit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nativeByte;

	}

	public static String getKeywords(String sInput) {
		String argu = "";
		String system_charset = "GBK";// GBK----0
		// String system_charset = "UTF-8";
		int charset_type = 1;
		// int init_flag = CLibrary.Instance.NLPIR_Init(
		// argu.getBytes(system_charset), charset_type,
		// "0".getBytes(system_charset));
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return null;
		}

		String nativeBytes = null;
		String nativeByte = null;
		try {
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

//			System.out.println("分词结果为： " + nativeBytes);

//			 CLibrary.Instance.NLPIR_AddUserWord("茶叶n");
			// CLibrary.Instance.NLPIR_AddUserWord("阻尼器 n");
			// CLibrary.Instance.NLPIR_AddUserWord("无辜 n");
			// CLibrary.Instance.NLPIR_AddUserWord("砍死 v");
			// nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput,
			// 1);
			// System.out.println("增加用户词典后分词结果为： " + nativeBytes);

			// CLibrary.Instance.NLPIR_DelUsrWord("暴力恐怖袭击");
			// nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput,
			// 1);
			// System.out.println("删除用户词典后分词结果为： " + nativeBytes);

			nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 5, false);
//			 CLibrary.Instance.NLPIR_Exit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nativeByte;

	}

 



	 
	public static void main(String[] args) throws Throwable {
		 String s =Keywords
		 .getKeywords("父亲代儿相亲要求女方家底500万　　　　　上海婚介协会举办首场“父母相亲会”，女方家长1200多位，男方家长近400位，报名异常火爆！因场地限制，主办方只安排男女各200位家长参加。其中一位爸爸称：“我儿子87年的，复旦大学本科……希望女方家庭资产500万以上……");
		 System.out.println("关键词提取结果是：" + s);
	}
}
