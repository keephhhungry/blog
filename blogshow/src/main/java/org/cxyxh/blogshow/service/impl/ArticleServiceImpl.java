package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.ArticleMapper;
import org.cxyxh.blogshow.model.Article;
import org.cxyxh.blogshow.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: ArticleServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 17:07
 * @Version: 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	/**
	 * 获取首页上的文章总数
	 *
	 * @return
	 */
	@Override
	public Integer getHomeArticleNum() {
		return articleMapper.getHomeArticleNum();
	}

	/**
	 * 获取首页上的文章(10)
	 *
	 * @param iarticleType 文章类型id
	 * @return
	 */
	@Override
	public List<Article> getHomeArticle(Integer iarticleType) {
		return articleMapper.getHomeArticle(iarticleType);
	}

	/**
	 * 获取首页的热门文章(10)
	 *
	 * @return
	 */
	@Override
	public List<Article> getHotArticle() {
		return articleMapper.getHotArticle();
	}

	/**
	 * 根据文章id，获取文章
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	@Override
	public Article getArticleById(Integer iarticle) {
		return articleMapper.getArticleById(iarticle);
	}
}
