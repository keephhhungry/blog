package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.Article;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: ArticleMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 17:08
 * @Version: 1.0
 */
public interface ArticleMapper {

	/**
	 * 获取首页上的文章总数
	 *
	 * @return
	 */
	Integer getHomeArticleNum();

	/**
	 * 获取首页上的文章(10)
	 *
	 * @param iarticleType 文章类型id
	 * @return
	 */
	List<Article> getHomeArticle(Integer iarticleType);

	/**
	 * 获取首页的热门文章(10)
	 *
	 * @return
	 */
	List<Article> getHotArticle();

	/**
	 * 根据文章id，获取文章
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	Article getArticleById(Integer iarticle);
}
