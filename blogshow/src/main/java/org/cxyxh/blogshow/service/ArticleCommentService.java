package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.ArticleComment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: CommentService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:25
 * @Version: 1.0
 */
public interface ArticleCommentService {

	/**
	 * 根据文章id 查找评论
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	List<ArticleComment> getCommentByArticleId(Integer iarticle);

	/**
	 * 新增文章评论
	 *
	 * @param articleComment
	 * @return
	 */
	Integer addArticleComment(HttpServletRequest request, ArticleComment articleComment);
}
