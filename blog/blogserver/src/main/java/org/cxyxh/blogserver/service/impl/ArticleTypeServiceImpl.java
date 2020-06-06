package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.ArticleTypeMapper;
import org.cxyxh.blogserver.model.ArticleType;
import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: ArticleTypeServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:21
 * @Version: 1.0
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

	@Autowired
	ArticleTypeMapper articleTypeMapper;

	/**
	 * 根据页码和关键字 查询文章类型
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	@Override
	public RespPageBean getArticleTypeByPage(Integer page, Integer size, String keyword) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<ArticleType> articleTypes = articleTypeMapper.getArticleTypeByPage( page,  size, keyword);
		Long total = articleTypeMapper.getTotal(keyword);
		RespPageBean bean = new RespPageBean();
		bean.setData(articleTypes);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 根据ID 删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 * @return
	 */
	@Override
	public Integer deleteArticleTypeById(Integer iarticleType) {
		return articleTypeMapper.deleteArticleTypeById(iarticleType);
	}

	/**
	 * 根据文章类型对象 新增文章类型
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	@Override
	public Integer addArticleType(ArticleType articleType) {
		articleType.setGmtCreate(new Date());
		articleType.setGmtModified(new Date());
		return articleTypeMapper.addArticleType(articleType);
	}

	/**
	 * 根据文章类型ID 修改该对象
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	@Override
	public Integer updateArticleTypeById(ArticleType articleType) {
		return articleTypeMapper.updateArticleTypeById(articleType);
	}
}
