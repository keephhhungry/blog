package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.ArticleComment;
import org.cxyxh.blogserver.model.RespPageBean;

import java.util.Date; /**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: CommentService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:47
 * @Version: 1.0
 */
public interface ArticleCommentService {

	/**
	 * 根据页码，每页展示数量，评论对象，创建时间 查询评论
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param articleComment  评论对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	RespPageBean getArticleCommentByPage(Integer page, Integer size, ArticleComment articleComment, Date[] createDateScope);

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
	 * @param iarticleComment 评论id
	 * @return
	 */
	Integer enforceDeleteArticleCommentById(Integer iarticleComment);
}
