package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.omg.CORBA.INTERNAL;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: FriendLink
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/1 23:32
 * @Version: 1.0
 */
public class FriendLink {

	/* 主键 */
	private Integer ifriendLink;

	/* 友链名称 */
	private String linkName;

	/* 友链地址 */
	private String linkUrl;

	/* 友链介绍 */
	private String remark;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtModified;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FriendLink{");
		sb.append("ifriendLink=").append(ifriendLink);
		sb.append(", linkName='").append(linkName).append('\'');
		sb.append(", linkUrl='").append(linkUrl).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIfriendLink() {
		return ifriendLink;
	}

	public void setIfriendLink(Integer ifriendLink) {
		this.ifriendLink = ifriendLink;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}
