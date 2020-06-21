package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.ArticleComment;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: CommentController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/comment")
public class ArticleCommentController {

	@Autowired
	ArticleCommentService commentService;

	/**
	 * 根据文章id 查找评论
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	@GetMapping("/{iarticle}")
	public RespBean getCommentByArticleId(@PathVariable Integer iarticle) {
		List<ArticleComment> comments = commentService.getCommentByArticleId(iarticle);
		return RespBean.ok("", comments);
	}
}
