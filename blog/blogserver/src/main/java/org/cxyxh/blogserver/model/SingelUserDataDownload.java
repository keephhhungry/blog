package org.cxyxh.blogserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/12 22:21
 * @describetion :
 */
@ApiModel(value = "单个用户数据下载实体类", description = "单个用户数据下载信息描述类")
public class SingelUserDataDownload {

    /* 主键 */
    @ApiModelProperty(value = "用户ID", example = "0")
    private Integer iuser;

    /* 用户登录名 */
    @ApiModelProperty(value = "用户登录名")
    private String username;

    /* 用户IP */
    @ApiModelProperty(value = "用户IP")
    private String ip;

    /* 省份 */
    @ApiModelProperty(value = "省份")
    private String province;

    /* 城市 */
    @ApiModelProperty(value = "城市")
    private String city;

    /* 登录时间 */
    @ApiModelProperty(value = "登录时间")
    private String gmtCreate;

    @Override
    public String toString() {
        return "SingelUserDataDownload{" +
                "iuser=" + iuser +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                '}';
    }

    public Integer getIuser() {
        return iuser;
    }

    public void setIuser(Integer iuser) {
        this.iuser = iuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
