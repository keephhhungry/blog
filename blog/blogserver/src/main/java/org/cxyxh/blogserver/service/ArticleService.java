package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.exception.BlogException;
import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.RespPageBean;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: ArticleService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/7 13:50
 * @Version: 1.0
 */
public interface ArticleService {

	/**
	 * 根据页码，每页展示数量，文章对象，创建时间 查询文章
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param article         文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	RespPageBean getArticleByPage(Integer page, Integer size, Article article, Date[] createDateScope);

	/**
	 * 根据ID 删除文章
	 *
	 * @param iarticle 文章ID
	 * @return
	 */
	Integer deleteArticleById(Integer iarticle);

	/**
	 * 查看该文章类型下 文章的id
	 *
	 * @param iarticleType 文章类型id
	 * @return
	 */
	List<Integer> getArticelsByTypeId(Integer iarticleType);

	/**
	 * 新增文章
	 *
	 * @param article 文章对象
	 * @return
	 */
	Integer addArticle(Article article) throws BlogException;

	/**
	 * 根据文章id 修改文章
	 *
	 * @param article
	 * @return
	 */
	Integer updateArticleById(Article article);
}
