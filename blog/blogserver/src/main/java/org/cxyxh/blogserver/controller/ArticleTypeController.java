package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.ArticleType;
import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: ArticleTypeController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:21
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article/type")
public class ArticleTypeController {

	@Autowired
	ArticleTypeService articleTypeService;

	/**
	 * 根据页码和关键字 查询文章类型
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getArticleTypeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
		return articleTypeService.getArticleTypeByPage(page, size, keyword);
	}

	/**
	 * 根据ID 删除文章类型
	 *
	 * @param iarticleType 文章类型ID
	 * @return
	 */
	@DeleteMapping("/{iarticleType}")
	public RespBean deleteArticleTypeById(@PathVariable Integer iarticleType) {
		if (articleTypeService.deleteArticleTypeById(iarticleType) == 1) {
			return RespBean.ok("删除成功");
		} else {
			return RespBean.error("删除失败");
		}
	}

	/**
	 * 根据文章类型对象 新增文章类型
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	@PostMapping("/")
	public RespBean addArticleType(@RequestBody ArticleType articleType) {
		if (articleTypeService.addArticleType(articleType) == 1) {
			return RespBean.ok("添加成功");
		} else {
			return RespBean.error("添加失败");
		}
	}

	/**
	 * 根据文章类型ID 修改该对象
	 *
	 * @param articleType 文章类型对象
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateArticleTypeById(@RequestBody ArticleType articleType) {
		if (articleTypeService.updateArticleTypeById(articleType) == 1) {
			return RespBean.ok("修改成功");
		} else {
			return RespBean.error("修改失败");
		}
	}
}
