package org.cxyxh.blogserver.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.cxyxh.blogserver.mapper.ArticleMapper;
import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.ArticleComment;
import org.cxyxh.blogserver.model.LeaveMessage;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: ArticleServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/7 13:51
 * @Version: 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	/**
	 * 根据页码，每页展示数量，文章对象，创建时间 查询文章
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param article         文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@Override
	public RespPageBean getArticleByPage(Integer page, Integer size, Article article, Date[] createDateScope) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<Article> articles = articleMapper.getArticleByPage(page, size, article, createDateScope);
		Long total = articleMapper.getTotal(article, createDateScope);
		RespPageBean bean = new RespPageBean();
		bean.setData(articles);
		bean.setTotal(total);
		return bean;
	}


	/**
	 * 根据ID 删除文章
	 *
	 * @param iarticle 文章ID
	 * @return
	 */
	@Override
	public Integer deleteArticleById(Integer iarticle) {
		return articleMapper.deleteArticleById(iarticle);
	}

	/**
	 * 查看该文章类型下 文章的id
	 *
	 * @param iarticleType 文章类型id
	 * @return
	 */
	@Override
	public List<Integer> getArticelsByTypeId(Integer iarticleType) {
		return articleMapper.getArticelsByTypeId(iarticleType);
	}

	/**
	 * 新增文章
	 *
	 * @param article 文章对象
	 * @return
	 */
	@Override
	public Integer addArticle(Article article) {
		article.setGmtCreate(new Date());
		article.setGmtModified(new Date());
		article.setLookNum(0);
		article.setLikeNum(0);
		article.setCommentNum(0);
		article.setIsReptile(0);
		article.setIsTop(0);
		article.setStatus(1);
		if(article.getArticleContent()==null ||article.getArticleContent().equals("")){
			article.setArticleContent("这是默认内容");
		}
		return articleMapper.addArticle(article);
	}

	/**
	 * 根据文章id 修改文章
	 *
	 * @param article
	 * @return
	 */
	@Override
	public Integer updateArticleById(Article article) {
		article.setGmtModified(new Date());
		return articleMapper.updateArticleById(article);
	}
}
