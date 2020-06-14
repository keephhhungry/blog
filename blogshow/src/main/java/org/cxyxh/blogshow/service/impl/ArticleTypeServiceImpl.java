package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.ArticleTypeMapper;
import org.cxyxh.blogshow.model.ArticleType;
import org.cxyxh.blogshow.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: com.cxyxh.blogshow.service.impl
 * @ClassName: ArticleTypeServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 13:02
 * @Version: 1.0
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

	@Autowired
	ArticleTypeMapper articleTypeMapper;

	/**
	 * 获取所有的文章类型
	 *
	 * @return
	 */
	@Override
	public List<ArticleType> getArticleType() {
		return articleTypeMapper.getArticleType();
	}
}
