package org.cxyxh.blogserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ProjectName: vhr
 * @Package: org.javaboy.vhr.model
 * @ClassName: RespPageBean
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/26 16:42
 * @Version: 1.0
 */
@ApiModel(value = "分页返回实体类", description = "分页返回信息描述类")
public class RespPageBean {

    /* 数据总数 */
    @ApiModelProperty(value = "数据总数", example = "0")
    private Long total;

    /* 返回数据 */
    @ApiModelProperty(value = "数据")
    private List<?> data;

    @Override
    public String toString() {
        return "RespPageBean{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
