package com.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: Article
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:19
 * @Version: 1.0
 */
public class Article {

	/* 主键 */
	private Integer iarticle;

	/* 文章作者 */
	private String articleAuthor;

	/* 文章标题 */
	private String articleTitle;

	/* 文章简介 */
	private String articleIntroduction;

	/* 文章内容 */
	private String articleContent;

	/* 文章类型 */
	private Integer iarticleType;

	/* 是否置顶 */
	private Integer isTop;

	/* 是否原创 */
	private Integer isOriginal;

	/* 是否爬虫 */
	private Integer isReptile;

	/* 查看数 */
	private Integer lookNum;

	/* 评论数 */
	private Integer commentNum;

	/* 收藏数 */
	private Integer collectNum;

	/* 状态 */
	private Integer status;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	/* 文章类型 */
	private ArticleType articleType;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Article{");
		sb.append("iarticle=").append(iarticle);
		sb.append(", articleAuthor='").append(articleAuthor).append('\'');
		sb.append(", articleTitle='").append(articleTitle).append('\'');
		sb.append(", articleIntroduction='").append(articleIntroduction).append('\'');
		sb.append(", articleContent='").append(articleContent).append('\'');
		sb.append(", iarticleType='").append(iarticleType).append('\'');
		sb.append(", isTop=").append(isTop);
		sb.append(", isOriginal=").append(isOriginal);
		sb.append(", isReptile=").append(isReptile);
		sb.append(", lookNum=").append(lookNum);
		sb.append(", commentNum=").append(commentNum);
		sb.append(", collectNum=").append(collectNum);
		sb.append(", status=").append(status);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", articleType=").append(articleType);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIarticle() {
		return iarticle;
	}

	public void setIarticle(Integer iarticle) {
		this.iarticle = iarticle;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleIntroduction() {
		return articleIntroduction;
	}

	public void setArticleIntroduction(String articleIntroduction) {
		this.articleIntroduction = articleIntroduction;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getIarticleType() {
		return iarticleType;
	}

	public void setIarticleType(Integer iarticleType) {
		this.iarticleType = iarticleType;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Integer isOriginal) {
		this.isOriginal = isOriginal;
	}

	public Integer getIsReptile() {
		return isReptile;
	}

	public void setIsReptile(Integer isReptile) {
		this.isReptile = isReptile;
	}

	public Integer getLookNum() {
		return lookNum;
	}

	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
