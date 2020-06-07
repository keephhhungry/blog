package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.ArticleService;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	ArticleService articleService;
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
		// 无法删除 "其他" 分组
		if (iarticleType == 1) {
			return RespBean.error("该分组为默认类型，无法删除");
		} else {
			// 查看该类型下是否有文章
			List<Integer> ids =  articleService.getArticelsByTypeId(iarticleType);
			if (ids.size() == 0) {
				if (articleTypeService.deleteArticleTypeById(iarticleType) == 1) {
					return RespBean.ok("删除成功");
				} else {
					return RespBean.error("删除失败");
				}
			} else {
				return RespBean.error("删除失败，该类型下仍有文章", -1);
			}
		}
	}

	/**
	 * 根据ID 强制删除文章类型，并且把该类型下的文章转移至"其他"类型下
	 *
	 * @param iarticleType 文章类型ID
	 * @return
	 */
	@DeleteMapping("enforce/{iarticleType}")
	public RespBean enforceDeleteArticleTypeById(@PathVariable Integer iarticleType) {
		if(articleTypeService.enforceDeleteArticleTypeById(iarticleType)){
			return RespBean.ok("强制删除成功");
		}else {
			return RespBean.ok("强制删除失败");
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
