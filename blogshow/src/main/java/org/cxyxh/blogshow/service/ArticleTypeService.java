package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.ArticleType;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: com.cxyxh.blogshow.service
 * @ClassName: ArticleTypeService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 13:02
 * @Version: 1.0
 */
public interface ArticleTypeService {

	/**
	 * 获取所有的文章类型
	 *
	 * @return
	 */
	List<ArticleType> getArticleType();
}
