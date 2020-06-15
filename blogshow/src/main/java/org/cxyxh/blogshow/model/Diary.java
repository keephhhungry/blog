package org.cxyxh.blogshow.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.model
 * @ClassName: Diary
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/15 20:00
 * @Version: 1.0
 */
public class Diary {

	/* 主键 */
	private Integer idiary;

	/* 日记标题 */
	private String diaryTitle;

	/* 日记内容 */
	private String diaryContent;

	/* 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

	/* 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Diary{");
		sb.append("idiary=").append(idiary);
		sb.append(", diaryTitle='").append(diaryTitle).append('\'');
		sb.append(", diaryContent='").append(diaryContent).append('\'');
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append('}');
		return sb.toString();
	}

	public Integer getIdiary() {
		return idiary;
	}

	public void setIdiary(Integer idiary) {
		this.idiary = idiary;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
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
}
