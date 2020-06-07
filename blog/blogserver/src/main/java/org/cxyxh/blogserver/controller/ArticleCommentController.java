package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.ArticleComment;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: CommentController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article/comment")
public class ArticleCommentController {

	@Autowired
	ArticleCommentService commentService;


	/**
	 * 根据页码，每页展示数量，评论对象，创建时间 查询评论
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param articleComment  评论对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getArticleCommentByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, ArticleComment articleComment, Date[] createDateScope) {
		return commentService.getArticleCommentByPage(page, size, articleComment, createDateScope);
	}

	/**
	 * 根据评论ID，删除评论
	 *
	 * @param iarticleComment 留言ID
	 * @return
	 */
	@DeleteMapping("/{iarticleComment}")
	public RespBean deleteArticleCommentById(@PathVariable Integer iarticleComment) {
		int num = commentService.getArticleCommentChildrenById(iarticleComment);
		//如果没有子留言
		if (num == 0) {
			if (commentService.deleteArticleCommentById(iarticleComment) == 1) {
				return RespBean.ok("删除成功!");
			}
			return RespBean.error("删除失败!");
		} else {
			return RespBean.error("删除失败，该评论下有回复", -1);
		}

	}

	/**
	 * 根据评论id，不管是否有下级，强制删除评论
	 *
	 * @param iarticleComment
	 * @return
	 */
	@DeleteMapping("/enforce/{iarticleComment}")
	public RespBean enforceDeleteArticleCommentById(@PathVariable Integer iarticleComment) {
		int num = commentService.enforceDeleteArticleCommentById(iarticleComment);
		if (num != 0) {
			return RespBean.ok("成功删除" + num + "条数据");
		}
		return RespBean.error("删除失败!");
	}
}
