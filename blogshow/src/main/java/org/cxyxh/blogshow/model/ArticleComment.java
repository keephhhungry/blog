package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: Comment
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:19
 * @Version: 1.0
 */
public class ArticleComment {

	/* 主键 */
	private Integer iarticleComment;

	/* 文章id */
	private Integer iarticle;

	/* 用户id */
	private Integer iuser;

	/* 评论内容 */
	private String commentContext;

	/* 父级ID */
	private Integer parentId;

	/* 浏览器 */
	private String browser;

	/* 操作系统 */
	private String operatingSystem;

	/* ip */
	private String ip;

	/* 地址 */
	private String address;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	/* 用户 */
	private User user;

	/* 文章 */
	private Article article;

	/* 子评论 */
	private List<ArticleComment> children;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ArticleComment{");
		sb.append("iarticleComment=").append(iarticleComment);
		sb.append(", iarticle=").append(iarticle);
		sb.append(", iuser=").append(iuser);
		sb.append(", commentContext='").append(commentContext).append('\'');
		sb.append(", parentId=").append(parentId);
		sb.append(", browser='").append(browser).append('\'');
		sb.append(", operatingSystem='").append(operatingSystem).append('\'');
		sb.append(", ip='").append(ip).append('\'');
		sb.append(", address='").append(address).append('\'');
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", user=").append(user);
		sb.append(", article=").append(article);
		sb.append(", children=").append(children);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIarticleComment() {
		return iarticleComment;
	}

	public void setIarticleComment(Integer iarticleComment) {
		this.iarticleComment = iarticleComment;
	}

	public Integer getIarticle() {
		return iarticle;
	}

	public void setIarticle(Integer iarticle) {
		this.iarticle = iarticle;
	}

	public Integer getIuser() {
		return iuser;
	}

	public void setIuser(Integer iuser) {
		this.iuser = iuser;
	}

	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<ArticleComment> getChildren() {
		return children;
	}

	public void setChildren(List<ArticleComment> children) {
		this.children = children;
	}
}
