package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.ArticleType;
import org.cxyxh.blogserver.model.RespPageBean;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: ArticleTypeMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:21
 * @Version: 1.0
 */
public interface ArticleTypeMapper {

	/**
	 * 根据页码和关键字 查询文章类型
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	List<ArticleType> getArticleTypeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") String keyword);

	/**
	 * 根据关键字 查询数据条数
	 *
	 * @param keyword 关键字
	 * @return
	 */
	Long getTotal(String keyword);

	/**
	 * 根据ID 删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 * @return
	 */
	Integer deleteArticleTypeById(Integer iarticleType);

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
	List<ArticleType> getArticleType();
}
