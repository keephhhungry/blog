package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "文章类型实体类",description = "文章类型信息描述类")
public class ArticleType {

	/* 文章类型ID */
	@ApiModelProperty(value = "文章评论ID", example = "0")
	private Integer iarticleType;

	/* 文章类型名称 */
	@ApiModelProperty(value = "文章类型名称")
	private String typeName;

	/* 该类型文章数量 */
	@ApiModelProperty(value = "该类型文章数量", example = "0")
	private Integer articleNum;

	/* 创建时间 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ArticleType{");
		sb.append("iarticleType=").append(iarticleType);
		sb.append(", typeName='").append(typeName).append('\'');
		sb.append(", articleNum=").append(articleNum);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append('}');
		return sb.toString();
	}


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

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}



}
