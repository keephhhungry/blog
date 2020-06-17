package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.ArticleMapper;
import org.cxyxh.blogserver.mapper.ArticleTypeMapper;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.cxyxh.blogserver.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	ArticleMapper articleMapper;
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
		List<ArticleType> articleTypes = articleTypeMapper.getArticleTypeByPage(page, size, keyword);
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
	 * 强制删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 */
	@Transactional
	@Override
	public Boolean enforceDeleteArticleTypeById(Integer iarticleType) {
		//1.先把该类型下的文章id全部找到
		List<Integer> ids = articleMapper.getArticelsByTypeId(iarticleType);
		//2.先把该类型下的文章全部转移到 "其他" 类型下
		Integer result1 = articleMapper.updateArticleType(ids, DefaultParams.DEFAULT_ARTICLE_TYPE);
		//3.把改类型删除掉
		Integer result2 = deleteArticleTypeById(iarticleType);
		if (result1 != 0 && result2 == 1) {
			return true;
		} else {
			return false;
		}
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
		articleType.setGmtModified(new Date());
		return articleTypeMapper.updateArticleTypeById(articleType);
	}

	/**
	 * 获取全部文章类型
	 *
	 * @return
	 */
	@Override
	public RespPageBean getArticleType() {
		List<ArticleType> articleTypes = articleTypeMapper.getArticleType();
		RespPageBean respPageBean = new RespPageBean();
		respPageBean.setData(articleTypes);
		return respPageBean;
	}

	/**
	 * 根据文章id，置顶文章
	 *
	 * @param iaricle
	 * @return
	 */
	@Override
	@Transactional
	public Integer topAricleById(Integer iaricle) {
		//先取消原先置顶
//		if(articleTypeMapper.cancleTopAricle()==1){
//			//再置顶当前文章
//			if(){
//		}
		return null;
	}


}
