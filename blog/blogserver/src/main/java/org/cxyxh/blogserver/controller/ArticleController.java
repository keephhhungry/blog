package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleService;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: Article
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/7 13:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleTypeService articleTypeService;

	/**
	 * 获取所有的文章类型
	 *
	 * @return
	 */
	@GetMapping("/articleType")
	public RespPageBean getArticleType() {
		return articleTypeService.getArticleType();
	}

	/**
	 * 根据页码，每页展示数量，文章对象，创建时间 查询文章
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param article         文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getArticleByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Article article, Date[] createDateScope) {
		return articleService.getArticleByPage(page, size, article, createDateScope);
	}

	/**
	 * 根据文章id，置顶文章
	 *
	 * @param iaricle
	 * @return
	 */
	@PutMapping("/top/{iaricle}")
	public RespBean topAricleById(@PathVariable Integer iaricle) {
		if (articleTypeService.topAricleById(iaricle) == 1) {
			return RespBean.ok("置顶成功");
		} else {
			return RespBean.error("置顶失败");
		}
	}

	/**
	 * 根据ID 删除文章
	 *
	 * @param iarticle 文章ID
	 * @return
	 */
	@DeleteMapping("/{iarticle}")
	public RespBean deleteArticleById(@PathVariable Integer iarticle) {
		if (articleService.deleteArticleById(iarticle) == 1) {
			return RespBean.ok("删除成功");
		} else {
			return RespBean.error("删除失败");
		}
	}

	/**
	 * 新增文章
	 *
	 * @param article 文章对象
	 * @return
	 */
	@PostMapping("/")
	public RespBean addArticle(@RequestBody Article article) {
		if (articleService.addArticle(article) == 1) {
			return RespBean.ok("新增成功");
		} else {
			return RespBean.error("新增失败");
		}
	}

	/**
	 * 根据文章id 修改文章
	 *
	 * @param article
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateArticleById(@RequestBody Article article) {
		if (articleService.updateArticleById(article) == 1) {
			return RespBean.ok("修改成功");
		} else {
			return RespBean.error("修改失败");
		}
	}
}
