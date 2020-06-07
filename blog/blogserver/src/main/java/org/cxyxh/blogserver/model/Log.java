package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: Log
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:25
 * @Version: 1.0
 */
public class Log {

	/* 主键 */
	private Integer ilog;

	/* 用户id */
	private Integer iuser;

	/* 浏览器 */
	private String browser;

	/* 操作系统 */
	private String operatingSystem;

	/* ip */
	private String ip;

	/* 具体地址 */
	private String address;

	/* url */
	private String url;

	/* 请求参数 */
	private String operationalParameter;

	/* 备注 */
	private String remark;

	/* 日志类型 1.登录 2.前台访问日志 3.后台操作日志 */
	private Integer logType;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	/* 操作者 */
	private User user;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Log{");
		sb.append("ilog=").append(ilog);
		sb.append(", iuser=").append(iuser);
		sb.append(", browser='").append(browser).append('\'');
		sb.append(", operatingSystem='").append(operatingSystem).append('\'');
		sb.append(", ip='").append(ip).append('\'');
		sb.append(", address='").append(address).append('\'');
		sb.append(", url='").append(url).append('\'');
		sb.append(", operationalParameter='").append(operationalParameter).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", logType=").append(logType);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", user=").append(user);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIlog() {
		return ilog;
	}

	public void setIlog(Integer ilog) {
		this.ilog = ilog;
	}

	public Integer getIuser() {
		return iuser;
	}

	public void setIuser(Integer iuser) {
		this.iuser = iuser;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperationalParameter() {
		return operationalParameter;
	}

	public void setOperationalParameter(String operationalParameter) {
		this.operationalParameter = operationalParameter;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
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
}
