package entity;

public class WeixinArticle {
	private String weixinNickName;
	private String weixinId;
	/**
	 * user_name
	 */
	private String userName;

	/**
	 * 发布日期
	 */
	private String postDate;
	/**
	 * 发布时间戳
	 */
	private String ct;
	/**
	 * 文章标题
	 */
	private String articleTitle;
	/**
	 * 文章描述
	 */
	private String articleDesc;

	/**
	 * 文章地址链接
	 */
	private String articleLink;

	/**
	 * 文章正文
	 */
	private String articleContext;
	/**
	 * 关键词
	 */
	private String articleKeywords;

	/**
	 * 文章来源地址链接
	 */
	private String articleSourceUrl;
	/**
	 * source_username
	 */
	private String sourceUsername;

	public WeixinArticle() {
		super();
	}

	public WeixinArticle(String weixinNickName, String weixinId, String userName, String postDate, String ct,
			String articleTitle, String articleDesc, String articleLink, String articleKeywords, String articleSourceUrl,
			String sourceUsername) {
		super();
		this.weixinNickName = weixinNickName;
		this.weixinId = weixinId;
		this.userName = userName;
		this.postDate = postDate;
		this.ct = ct;
		this.articleTitle = articleTitle;
		this.articleDesc = articleDesc;
		this.articleLink = articleLink;
		this.articleKeywords = articleKeywords;
		this.articleSourceUrl = articleSourceUrl;
		this.sourceUsername = sourceUsername;
	}
	
	public WeixinArticle(String weixinNickName, String weixinId, String userName, String postDate, String ct,
			String articleTitle, String articleDesc, String articleLink, String articleContext,String articleKeywords, String articleSourceUrl,
			String sourceUsername) {
		super();
		this.weixinNickName = weixinNickName;
		this.weixinId = weixinId;
		this.userName = userName;
		this.postDate = postDate;
		this.ct = ct;
		this.articleTitle = articleTitle;
		this.articleDesc = articleDesc;
		this.articleLink = articleLink;
		this.articleKeywords = articleKeywords;
		this.articleSourceUrl = articleSourceUrl;
		this.articleContext = articleContext;
		this.sourceUsername = sourceUsername;
	}
	

	public WeixinArticle(String weixinNickName , String weixinId , String postDate , String ct , String articleTitle ,
			String articleLink ,  String articleContext ) {
		super();
		this.weixinNickName = weixinNickName;
		this.weixinId = weixinId;
		this.postDate = postDate;
		this.ct = ct;
		this.articleTitle = articleTitle;
		this.articleLink = articleLink;
		this.articleContext = articleContext;
	}

	public String getWeixinNickName() {
		return weixinNickName;
	}

	public void setWeixinNickName(String weixinNickName) {
		this.weixinNickName = weixinNickName;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public String getArticleContext() {
		return articleContext;
	}

	public void setArticleContext(String articleContext) {
		this.articleContext = articleContext;
	}

	public String getArticleSourceUrl() {
		return articleSourceUrl;
	}

	public void setArticleSourceUrl(String articleSourceUrl) {
		this.articleSourceUrl = articleSourceUrl;
	}

	public String getSourceUsername() {
		return sourceUsername;
	}

	public void setSourceUsername(String sourceUsername) {
		this.sourceUsername = sourceUsername;
	}

	public String getArticleKeywords() {
		return articleKeywords;
	}

	public void setArticleKeywords(String articleKeywords) {
		this.articleKeywords = articleKeywords;
	}

}
