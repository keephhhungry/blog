package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.model
 * @ClassName: Menu
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:00
 * @Version: 1.0
 */
public class Menu {

    /* 主键 */
    private Integer imenu;

    /* 匹配路径 */
    private String url;

    /* 实际路径 */
    private String path;

    /* 组件名称 */
    private String component;

    /* 菜单名称 */
    private String name;

    /* 图标 */
    private String icon;

    /* 状态类 */
    private Meta meta;

    /* 父亲ID */
    private Integer parentId;

    /* 是否可用 */
    private boolean enabled;

    /* 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /* 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /* 子菜单 */
    private List<Menu> children;

    /* 角色组 */
    private List<Role> roles;

    public Integer getImenu() {
        return imenu;
    }

    public void setImenu(Integer imenu) {
        this.imenu = imenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Menu{");
        sb.append("imenu=").append(imenu);
        sb.append(", url='").append(url).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", component='").append(component).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", meta=").append(meta);
        sb.append(", parentId=").append(parentId);
        sb.append(", enabled=").append(enabled);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", children=").append(children);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
