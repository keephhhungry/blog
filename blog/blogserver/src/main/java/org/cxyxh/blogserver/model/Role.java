package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.model
 * @ClassName: Role
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:00
 * @Version: 1.0
 */
public class  Role {

    /* 主键 */
    private Integer irole;

    /* 角色英文名 */
    private String name;

    /* 角色中文名 */
    private String nameZh;

    /* 描述 */
    private String description;

    /* 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    /* 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtModified;

    /* 菜单 */
    private List<Menu> menus;

    public Integer getIrole() {
        return irole;
    }

    public void setIrole(Integer irole) {
        this.irole = irole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("irole=").append(irole);
        sb.append(", name='").append(name).append('\'');
        sb.append(", nameZh='").append(nameZh).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", menus=").append(menus);
        sb.append('}');
        return sb.toString();
    }
}
