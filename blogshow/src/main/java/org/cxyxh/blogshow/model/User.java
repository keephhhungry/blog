package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.model
 * @ClassName: User
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 9:53
 * @Version: 1.0
 */
public class User{

	/* 主键 */
	private Integer iuser;

	/* 用户登录名 */
	private String username;

	/* 密码 */
	private String password;

	/* 用户真实姓名 */
	private String name;

	/* 年龄 */
	private Integer age;

	/* 性别1.男性 0.女性 */
	private Integer sex;

	/* 邮箱 */
	private String email;

	/* 电话 */
	private String telephone;

	/* 头像 */
	private String userFace;

	/* 备注 */
	private String remark;

	/* 启用状态 */
	private boolean enabled;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	/* 角色 */
	private List<Role> roles;

	@Override
	public String toString() {
		return "User{" +
				"iuser=" + iuser +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				", sex=" + sex +
				", email='" + email + '\'' +
				", telephone='" + telephone + '\'' +
				", userFace='" + userFace + '\'' +
				", remark='" + remark + '\'' +
				", enabled=" + enabled +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				", roles=" + roles +
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserFace() {
		return userFace;
	}

	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
