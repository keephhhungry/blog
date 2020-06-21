package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.ArticleCommentMapper;
import org.cxyxh.blogshow.model.ArticleComment;
import org.cxyxh.blogshow.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: CommentServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:25
 * @Version: 1.0
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	ArticleCommentMapper articleCommentMapper;

	/**
	 * 根据文章id 查找评论
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	@Override
	public List<ArticleComment> getCommentByArticleId(Integer iarticle) {
		List<ArticleComment> comments = articleCommentMapper.getCommentByArticleId(iarticle);
		for (ArticleComment comment : comments) {
			List<ArticleComment> children = articleCommentMapper.getCommentByCommentId(comment.getIarticleComment());
			comment.setChildren(children);
		}
		return comments;
	}
}
