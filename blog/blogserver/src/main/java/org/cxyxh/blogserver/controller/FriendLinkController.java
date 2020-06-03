package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: FriendLink
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/1 23:31
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/friendlink")
public class FriendLinkController {

	@Autowired
	FriendLinkService friendLinkService;

	/**
	 * 根据页码和关键字 查询友链
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getFriendLinkByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
		return friendLinkService.getFriendLinkByPage(page, size, keyword);
	}

	/**
	 * 根据ID 删除友链
	 *
	 * @param ifriendLink 友链ID
	 * @return
	 */
	@DeleteMapping("/{ifriendLink}")
	public RespBean deleteFriendLinkById(@PathVariable Integer ifriendLink) {
		if (friendLinkService.deleteFriendLinkById(ifriendLink) == 1) {
			return RespBean.ok("删除成功");
		} else {
			return RespBean.error("删除失败");
		}
	}

	/**
	 * 根据友链对象 新增友链
	 *
	 * @param friendLink 用户对象
	 * @return
	 */
	@PostMapping("/")
	public RespBean addFriendLink(@RequestBody FriendLink friendLink) {
		if (friendLinkService.addFriendLink(friendLink) == 1) {
			return RespBean.ok("添加成功");
		} else {
			return RespBean.error("添加失败");
		}
	}

	/**
	 * 根据友链ID 修改该对象
	 *
	 * @param friendLink 友链对象
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateFriendLinkById(@RequestBody FriendLink friendLink) {
		if (friendLinkService.updateFriendLinkById(friendLink) == 1) {
			return RespBean.ok("修改成功");
		} else {
			return RespBean.error("修改失败");
		}
	}

}
