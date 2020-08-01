package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "日志实体类", description = "日志信息描述类")
public class Log {

    /* 日志类型 1.前台登录 2.后台登录 3.前台访问日志 4.后台操作日志 */
    public final static Integer FRONT_LOGIN_LOG = 1;
    public final static Integer BACKGROUND_LOGIN_LOG = 2;
    public final static Integer FRONT_OPERATE_LOG = 3;
    public final static Integer BACKGROUND_OPERATE_LOG = 4;

    /* 主键 */
    @ApiModelProperty(value = "日志ID", example = "0")
    private Integer ilog;

    /* 用户id */
    @ApiModelProperty(value = "用户ID", example = "0")
    private Integer iuser;

    /* 浏览器名字 */
    @ApiModelProperty(value = "浏览器名字")
    private String browserName;

    /* 浏览器版本 */
    @ApiModelProperty(value = "浏览器版本")
    private String browserVersion;

    /* 操作系统 */
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;

    /* ip */
    @ApiModelProperty(value = "ip")
    private String ip;

    /* 省份 */
    @ApiModelProperty(value = "省份")
    private String province;

    /* 城市 */
    @ApiModelProperty(value = "城市")
    private String city;

    /* url */
    @ApiModelProperty(value = "url")
    private String url;

    /* 请求参数 */
    @ApiModelProperty(value = "请求参数")
    private String operationalParameter;

    /* 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;

    /* 日志类型 1.前台登录 2.后台登录 3.前台访问日志 4.后台操作日志 */
    @ApiModelProperty(value = "日志类型", example = "0")
    private Integer logType;

    /* 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    /* 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    /* 操作者 */
    @ApiModelProperty(value = "用户类")
    private User user;

    @Override
    public String toString() {
        return "Log{" +
                "ilog=" + ilog +
                ", iuser=" + iuser +
                ", browserName='" + browserName + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", ip='" + ip + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", url='" + url + '\'' +
                ", operationalParameter='" + operationalParameter + '\'' +
                ", remark='" + remark + '\'' +
                ", logType=" + logType +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", user=" + user +
                '}';
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

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
