package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.model.ArticleType;
import org.cxyxh.blogshow.model.FriendLink;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.FriendLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: FriendLinkController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 15:37
 * @Version: 1.0
 */
@RestController
@RequestMapping("/friendLink")
@Api(tags = "文章评论数据接口")
public class FriendLinkController {

	private final static Logger logger = LoggerFactory.getLogger(FriendLinkController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	FriendLinkService friendLinkService;

	/**
	 * 查询全部友情链接
	 *
	 * @return
	 */
	@ApiOperation(value = "获取友链", notes = "获取友链")
	@GetMapping("/")
	public RespBean getAllFriendLink() {
		List<FriendLink> friendLinks = friendLinkService.getAllFriendLink();
		return RespBean.ok("", friendLinks);
	}
}
