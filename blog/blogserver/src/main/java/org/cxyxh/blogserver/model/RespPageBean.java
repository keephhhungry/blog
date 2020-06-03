package org.cxyxh.blogserver.model;

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
public class RespPageBean {
    private Long total;

    private List<?> data;

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
