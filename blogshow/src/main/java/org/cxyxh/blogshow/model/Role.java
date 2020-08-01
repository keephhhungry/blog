package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "角色实体类", description = "角色信息描述类")
public class Role {

    /* 主键 */
    @ApiModelProperty(value = "角色ID", example = "0")
    private Integer irole;

    /* 角色英文名 */
    @ApiModelProperty(value = "角色英文名")
    private String name;

    /* 角色中文名 */
    @ApiModelProperty(value = "角色中文名")
    private String nameZh;

    /* 描述 */
    @ApiModelProperty(value = "角色描述")
    private String description;

    /* 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /* 修改时间 */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /* 菜单 */
    @ApiModelProperty(value = "菜单集合")
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
