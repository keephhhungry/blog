package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Article;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: Article
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/7 13:51
 * @Version: 1.0
 */
public interface ArticleMapper {

	/**
	 * 根据页码，每页展示数量，文章对象，创建时间 查询文章
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param article         文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	List<Article> getArticleByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("article") Article article, @Param("createDateScope") Date[] createDateScope);


	/**
	 * 根据文章对象，创建时间，查询文章总数
	 *
	 * @param article         文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	Long getTotal(@Param("article") Article article, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 根据ID 删除文章
	 *
	 * @param iarticle 文章ID
	 * @return
	 */
	Integer deleteArticleById(Integer iarticle);

	/**
	 * 查看该文章类型下 文章数量
	 *
	 * @param iarticleType
	 * @return
	 */
	Integer getArticleNum(Integer iarticleType);

	/**
	 * 查询该文章类型下 文章的id集合
	 *
	 * @param iarticleType
	 * @return
	 */
	List<Integer> getArticelsByTypeId(Integer iarticleType);

	/**
	 * 批量修改文章的类型至其他
	 *
	 * @param ids 要修改的文章id集合
	 * @return
	 */
	Integer updateArticleType(@Param("ids") List<Integer> ids, @Param("iarticleType") Integer iarticleType);

	/**
	 * 新增文章
	 *
	 * @param article 文章对象
	 * @return
	 */
	Integer addArticle(Article article);

	/**
	 * 根据文章id 修改文章
	 *
	 * @param article
	 * @return
	 */
	Integer updateArticleById(Article article);
}
