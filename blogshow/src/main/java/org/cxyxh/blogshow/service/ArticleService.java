package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.Article;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: ArticleService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 17:07
 * @Version: 1.0
 */
public interface ArticleService {

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
}
