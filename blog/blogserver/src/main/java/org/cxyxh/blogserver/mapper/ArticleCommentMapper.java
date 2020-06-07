package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.ArticleComment;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: CommentMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:48
 * @Version: 1.0
 */
public interface ArticleCommentMapper {

	/**
	 * 根据页码，每页展示数量，评论对象，创建时间 查询评论
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param articleComment  评论对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	List<ArticleComment> getArticleCommentByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("articleComment") ArticleComment articleComment, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 根据文章对象，创建时间，查询文章总数
	 *
	 * @param articleComment  评论对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	Long getTotal(@Param("articleComment") ArticleComment articleComment, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 查看该评论是否有子评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	Integer getArticleCommentChildrenById(Integer iarticleComment);

	/**
	 * 根据ID删除评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	Integer deleteArticleCommentById(Integer iarticleComment);

	/**
	 * 根据ID强制删除评论和子评论
	 *
	 * @param ids 评论ids
	 * @return
	 */
	Integer deleteArticleCommentByIds(@Param("ids") List<Integer> ids);

	/**
	 * 通过父id，查找子id
	 *
	 * @param ileaveMessage 父id
	 * @return
	 */
	List<Integer> getArticleCommentChildren(Integer ileaveMessage);
}
