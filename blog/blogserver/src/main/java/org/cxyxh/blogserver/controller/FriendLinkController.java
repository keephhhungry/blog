package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.Interceptor.LoginInterceptor;
import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.FriendLinkService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "友链数据接口")
public class FriendLinkController {

    private final static Logger logger = LoggerFactory.getLogger(FriendLinkController.class);

    @Autowired
    FriendLinkService friendLinkService;

    /**
     * 根据页码，每页显示数量和关键字查询友链
     *
     * @param page    页码
     * @param size    每页展示数量
     * @param keyword 关键字
     * @return
     */
    @ApiOperation(value = "查询友链", notes = "根据页码，每页显示数量和关键字查询友链")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false),
    })
    @GetMapping("/")
    public RespPageBean getFriendLinkByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
        return friendLinkService.getFriendLinkByPage(page, size, keyword);
    }

    /**
     * 根据友链ID，删除友链
     *
     * @param ifriendLink 友链ID
     * @return
     */
    @DeleteMapping("/{ifriendLink}")
    @ApiOperation(value = "删除友链", notes = "根据友链ID，删除友链")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ifriendLink", value = "友链ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    public RespBean deleteFriendLinkById(@PathVariable Integer ifriendLink, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (friendLinkService.deleteFriendLinkById(ifriendLink) == 1) {
            remark = "删除友链成功，友链ID[{" + ifriendLink + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("删除成功");
        } else {
            remark = "删除友链失败，友链ID[{" + ifriendLink + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.error("删除失败");
        }
    }

    /**
     * 根据友链对象，添加友链
     *
     * @param friendLink 友链对象
     * @return
     */
    @ApiOperation(value = "添加友链", notes = "根据友链对象，添加友链")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendLink", value = "友链对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PostMapping("/")
    public RespBean addFriendLink(@RequestBody FriendLink friendLink, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (friendLinkService.addFriendLink(friendLink) == 1) {
            remark = "添加友链成功，友链ID[{" + friendLink.getIfriendLink() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("添加成功");
        } else {
            remark = "添加友链失败,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("添加失败");
        }
    }

    /**
     * 根据友链对象，修改友链
     *
     * @param friendLink 友链对象
     * @return
     */
    @ApiOperation(value = "修改友链", notes = "根据友链对象，修改友链")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "friendLink", value = "友链对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    @PutMapping("/")
    public RespBean updateFriendLinkById(@RequestBody FriendLink friendLink, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (friendLinkService.updateFriendLinkById(friendLink) == 1) {
            remark = "修改友链成功，友链ID[{" + friendLink.getIfriendLink() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("修改成功");
        } else {
            remark = "修改友链失败，友链ID[{" + friendLink.getIfriendLink() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("修改失败");
        }
    }

}
