package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: LeaveMessage
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:19
 * @Version: 1.0
 */
public class LeaveMessage {

	/* 主键 */
	private Integer ileaveMessage;

	/* 用户id */
	private Integer iuser;

	/* 留言内容 */
	private String messageContext;

	/* 父级留言id */
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
	@DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@DateTimeFormat(pattern="yyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	/* 用户 */
	private User user;

	/* 子留言 */
	private List<LeaveMessage> children;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("LeaveMessage{");
		sb.append("ileaveMessage=").append(ileaveMessage);
		sb.append(", iuser=").append(iuser);
		sb.append(", messageContext='").append(messageContext).append('\'');
		sb.append(", parentId=").append(parentId);
		sb.append(", browser='").append(browser).append('\'');
		sb.append(", operatingSystem='").append(operatingSystem).append('\'');
		sb.append(", ip='").append(ip).append('\'');
		sb.append(", address='").append(address).append('\'');
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", user=").append(user);
		sb.append(", children=").append(children);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIleaveMessage() {
		return ileaveMessage;
	}

	public void setIleaveMessage(Integer ileaveMessage) {
		this.ileaveMessage = ileaveMessage;
	}

	public Integer getIuser() {
		return iuser;
	}

	public void setIuser(Integer iuser) {
		this.iuser = iuser;
	}

	public String getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
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

	public List<LeaveMessage> getChildren() {
		return children;
	}

	public void setChildren(List<LeaveMessage> children) {
		this.children = children;
	}
}
