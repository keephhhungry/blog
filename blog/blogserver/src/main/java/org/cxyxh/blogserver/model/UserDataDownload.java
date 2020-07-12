package org.cxyxh.blogserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/12 22:21
 * @describetion :
 */
@ApiModel(value = "用户数据下载实体类", description = "用户数据下载信息描述类")
public class UserDataDownload {

    /* 主键 */
    @ApiModelProperty(value = "用户ID", example = "0")
    private Integer iuser;

    /* 用户登录名 */
    @ApiModelProperty(value = "用户登录名")
    private String username;

    /* 用户访问次数 */
    @ApiModelProperty(value = "用户访问次数")
    private String visits;


    @Override
    public String toString() {
        return "UserDataDownload{" +
                "iuser=" + iuser +
                ", username='" + username + '\'' +
                ", visits='" + visits + '\'' +
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

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }
}
