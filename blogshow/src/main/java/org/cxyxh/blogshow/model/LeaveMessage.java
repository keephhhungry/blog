package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "留言实体类", description = "留言信息描述类")
public class LeaveMessage {

    /* 主键 */
    @ApiModelProperty(value = "留言ID", example = "0")
    private Integer ileaveMessage;

    /* 用户id */
    @ApiModelProperty(value = "留言ID", example = "0")
    private Integer iuser;

    /* 留言内容 */
    @ApiModelProperty(value = "留言内容")
    private String messageContext;

    /* 直接父级留言id */
    @ApiModelProperty(value = "直接父级留言ID", example = "0")
    private Integer parentId;

    /* 根父级留言id */
    @ApiModelProperty(value = "根父级留言ID", example = "0")
    private Integer originalParentId;

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

    /* 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /* 修改时间 */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /* 用户类 */
    @ApiModelProperty(value = "用户类")
    private User user;

    /* 子留言集合 */
    @ApiModelProperty(value = "子留言集合")
    private List<LeaveMessage> children;

    @Override
    public String toString() {
        return "LeaveMessage{" +
                "ileaveMessage=" + ileaveMessage +
                ", iuser=" + iuser +
                ", messageContext='" + messageContext + '\'' +
                ", parentId=" + parentId +
                ", originalParentId=" + originalParentId +
                ", browserName='" + browserName + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", ip='" + ip + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", user=" + user +
                ", children=" + children +
                '}';
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

    public Integer getOriginalParentId() {
        return originalParentId;
    }

    public void setOriginalParentId(Integer originalParentId) {
        this.originalParentId = originalParentId;
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
