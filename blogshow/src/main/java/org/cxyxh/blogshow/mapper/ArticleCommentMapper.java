package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.ArticleComment;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: CommentMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:25
 * @Version: 1.0
 */
public interface ArticleCommentMapper {

	/**
	 * 根据文章id 查找评论
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	List<ArticleComment> getCommentByArticleId(Integer iarticle);

	/**
	 * 根据评论id 查找子评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	List<ArticleComment> getCommentByCommentId(Integer iarticleComment);

	/**
	 * 新增文章评论
	 *
	 * @param articleComment
	 * @return
	 */
	Integer addArticleComment(ArticleComment articleComment);
}
