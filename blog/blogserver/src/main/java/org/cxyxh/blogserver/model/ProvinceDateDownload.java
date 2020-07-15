package org.cxyxh.blogserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.redis.connection.RedisServer;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 23:06
 * @describetion :
 */
@ApiModel(value = "省份访问数据下载实体类", description = "省份访问数据下载信息描述类")
public class ProvinceDateDownload {

    /* 省份ID */
    @ApiModelProperty(value = "省份ID", example = "0")
    private Integer iprovince;

    /* 省份名称 */
    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    /* 访问次数 */
    @ApiModelProperty(value = "访问次数")
    private Integer visits;


    @Override
    public String toString() {
        return "ProvinceDateDownload{" +
                "iprovince=" + iprovince +
                ", provinceName='" + provinceName + '\'' +
                ", visits='" + visits + '\'' +
                '}';
    }

    public Integer getIprovince() {
        return iprovince;
    }

    public void setIprovince(Integer iprovince) {
        this.iprovince = iprovince;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }
}
