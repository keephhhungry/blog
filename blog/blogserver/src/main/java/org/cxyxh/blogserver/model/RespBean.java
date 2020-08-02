package org.cxyxh.blogserver.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName: vuehrr
 * @Package: org.cxyxh.vhr.model
 * @ClassName: RespBean
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/23 22:06
 * @Version: 1.0
 */
@ApiModel(value = "返回实体类", description = "返回信息描述类")
public class RespBean {

    /* 状态码 */
    @ApiModelProperty(value = "状态码", example = "0")
    private Integer status;

    /* 返回消息 */
    @ApiModelProperty(value = "返回消息")
    private String msg;

    /* 返回数据 */
    @ApiModelProperty(value = "返回数据")
    private Object obj;

    @Override
    public String toString() {
        return "RespBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    private RespBean() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
