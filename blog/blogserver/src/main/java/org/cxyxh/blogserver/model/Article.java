package org.cxyxh.blogserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.model
 * @ClassName: Article
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:19
 * @Version: 1.0
 */
@ApiModel(value = "文章实体类", description = "文章信息描述类")
public class Article {

    /* 主键 */
    @ApiModelProperty(value = "文章ID", example = "0")
    private Integer iarticle;

    /* 文章作者 */
    @ApiModelProperty(value = "文章作者")
    private String articleAuthor;

    /* 文章标题 */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    /* 文章简介 */
    @ApiModelProperty(value = "文章简介")
    private String articleIntroduction;

    /* 文章内容 */
    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    /* 文章字数 */
    @ApiModelProperty(value = "文章字数", example = "0")
    private Integer wordCount;

    /* 文章类型 */
    @ApiModelProperty(value = "文章类型", example = "0")
    private Integer iarticleType;

    /* 是否置顶 */
    @ApiModelProperty(value = "是否置顶", example = "0")
    private Integer isTop;

    /* 是否原创 */
    @ApiModelProperty(value = "是否原创", example = "0")
    private Integer isOriginal;

    /* 是否爬虫 */
    @ApiModelProperty(value = "是否爬虫", example = "0")
    private Integer isReptile;

    /* 查看数 */
    @ApiModelProperty(value = "查看数", example = "0")
    private Integer lookNum;

    /* 点赞数 */
    @ApiModelProperty(value = "点赞数", example = "0")
    private Integer likeNum;

    /* 文章状态 */
    @ApiModelProperty(value = "文章状态", example = "0")
    private Integer status;

    /* 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /* 修改时间 */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /* 文章类型类 */
    @ApiModelProperty(value = "文章类型类")
    private ArticleType articleType;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Article{");
        sb.append("iarticle=").append(iarticle);
        sb.append(", articleAuthor='").append(articleAuthor).append('\'');
        sb.append(", articleTitle='").append(articleTitle).append('\'');
        sb.append(", articleIntroduction='").append(articleIntroduction).append('\'');
        sb.append(", articleContent='").append(articleContent).append('\'');
        sb.append(", wordCount=").append(wordCount);
        sb.append(", iarticleType=").append(iarticleType);
        sb.append(", isTop=").append(isTop);
        sb.append(", isOriginal=").append(isOriginal);
        sb.append(", isReptile=").append(isReptile);
        sb.append(", lookNum=").append(lookNum);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", status=").append(status);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", articleType=").append(articleType);
        sb.append('}');
        return sb.toString();
    }

    public Integer getIarticle() {
        return iarticle;
    }

    public void setIarticle(Integer iarticle) {
        this.iarticle = iarticle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleIntroduction() {
        return articleIntroduction;
    }

    public void setArticleIntroduction(String articleIntroduction) {
        this.articleIntroduction = articleIntroduction;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getIarticleType() {
        return iarticleType;
    }

    public void setIarticleType(Integer iarticleType) {
        this.iarticleType = iarticleType;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Integer getIsReptile() {
        return isReptile;
    }

    public void setIsReptile(Integer isReptile) {
        this.isReptile = isReptile;
    }

    public Integer getLookNum() {
        return lookNum;
    }

    public void setLookNum(Integer lookNum) {
        this.lookNum = lookNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
