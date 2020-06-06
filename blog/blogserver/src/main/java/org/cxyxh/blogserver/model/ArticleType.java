package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: ArticleType
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:19
 * @Version: 1.0
 */
public class ArticleType {

	/* 主键 */
	private Integer iarticleType;
	/* 类型名称 */

	private String typeName;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtModified;

	public Integer getIarticleType() {
		return iarticleType;
	}

	public void setIarticleType(Integer iarticleType) {
		this.iarticleType = iarticleType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ArticleType{");
		sb.append("iarticleType=").append(iarticleType);
		sb.append(", typeName='").append(typeName).append('\'');
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append('}');
		return sb.toString();
	}
}
