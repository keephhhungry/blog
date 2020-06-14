package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.ArticleType;
import org.cxyxh.blogshow.model.FriendLink;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class FriendLinkController {

	@Autowired
	FriendLinkService friendLinkService;

	/**
	 * 查询全部友情链接
	 *
	 * @return
	 */
	@GetMapping("/")
	public RespBean getAllFriendLink() {
		List<FriendLink> friendLinks = friendLinkService.getAllFriendLink();
		return RespBean.ok("", friendLinks);
	}
}
