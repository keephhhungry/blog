package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.exception.BlogException;
import org.cxyxh.blogserver.model.ArticleType;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: ArticleTypeService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:21
 * @Version: 1.0
 */
public interface ArticleTypeService {

	/**
	 * 根据页码和关键字 查询文章类型
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	RespPageBean getArticleTypeByPage(Integer page, Integer size, String keyword);

	/**
	 * 根据ID 删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 * @return
	 */
	Integer deleteArticleTypeById(Integer iarticleType);

	/**
	 * 强制删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 */
	Boolean enforceDeleteArticleTypeById(Integer iarticleType) throws BlogException;

	/**
	 * 根据文章类型对象 新增文章类型
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	Integer addArticleType(ArticleType articleType);

	/**
	 * 根据文章类型ID 修改该对象
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	Integer updateArticleTypeById(ArticleType articleType);

	/**
	 * 获取全部文章类型
	 *
	 * @return
	 */
	RespPageBean getArticleType();

	/**
	 * 根据文章id，置顶文章
	 *
	 * @param iaricle
	 * @return
	 */
	Integer topAricleById(Integer iaricle);


}
