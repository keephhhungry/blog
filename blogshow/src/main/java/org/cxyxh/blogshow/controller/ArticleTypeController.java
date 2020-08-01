package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.model.ArticleType;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: com.cxyxh.blogshow.controller
 * @ClassName: ArticleTypeController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 13:01
 * @Version: 1.0
 */
@RestController
@RequestMapping("/articleType")
@Api(tags = "文章类型数据接口")
public class ArticleTypeController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	ArticleTypeService articleTypeService;

	/**
	 * 获取所有的文章类型
	 *
	 * @return
	 */
	@ApiOperation(value = "获取所有文章类型", notes = "获取所有文章类型")
	@GetMapping("/")
	public RespBean getAllArticleType() {
		List<ArticleType> articleTypes =  articleTypeService.getArticleType();
		return RespBean.ok("",articleTypes);
	}



}
