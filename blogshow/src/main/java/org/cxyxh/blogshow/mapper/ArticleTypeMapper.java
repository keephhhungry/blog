package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: com.cxyxh.blogshow.mapper
 * @ClassName: ArticleTypeMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 13:03
 * @Version: 1.0
 */
@Mapper
public interface ArticleTypeMapper {

	/**
	 * 获取所有的文章类型
	 *
	 * @return
	 */
	List<ArticleType> getArticleType();
}
