package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.Article;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: ArticleController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 17:06
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	/**
	 * 获取首页上的文章总数
	 *
	 * @return
	 */
	@GetMapping("/getHomeArticleNum")
	public RespBean getHomeArticleNum() {
		int num = articleService.getHomeArticleNum();
		return RespBean.ok("", num);
	}

	/**
	 * 获取首页上的文章(10)
	 *
	 * @param iarticleType 文章类型id
	 * @return
	 */
	@GetMapping("/getHomeArticle")
	public RespBean getHomeArticle(Integer iarticleType) {
		List<Article> articles = articleService.getHomeArticle(iarticleType);
		return RespBean.ok("", articles);
	}

	/**
	 * 获取首页的热门文章(10)
	 *
	 * @return
	 */
	@GetMapping("getHotArticle")
	public RespBean getHotArticle() {
		List<Article> articles = articleService.getHotArticle();
		return RespBean.ok("", articles);
	}

	/**
	 * 根据文章id，获取文章
	 *
	 * @param iarticle 文章id
	 * @return
	 */
	@GetMapping("/{iarticle}")
	public RespBean getArticleById(@PathVariable Integer iarticle) {
		Article article = articleService.getArticleById(iarticle);
		return RespBean.ok("", article);
	}

	/**
	 * 根据文章id 新增文章阅读量
	 *
	 * @param iarticle
	 * @return
	 */
	@PutMapping("/addLookNum")
	public void addLookNum(Integer iarticle) {
		if (articleService.addLookNum(iarticle) == 1) {
			//写成功日志
		} else {
			//写失败日志
		}
	}
}
