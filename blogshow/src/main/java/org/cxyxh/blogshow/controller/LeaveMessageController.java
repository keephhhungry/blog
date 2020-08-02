package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.model.LeaveMessage;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.RespPageBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LeaveMessageService;
import org.cxyxh.blogshow.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: LeaveMessageController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/20 10:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/leaveMessage")
@Api(tags = "留言数据接口")
public class LeaveMessageController {

    private final static Logger logger = LoggerFactory.getLogger(LeaveMessageController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    LeaveMessageService leaveMessageService;

    /**
     * 根据页码和每页展示数量 获取留言集合
     *
     * @param page 页码
     * @param size 每页展示数量
     * @return
     */
    @ApiOperation(value = "获取留言", notes = "根据页码，每页显示数量，获取留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
    })
    @GetMapping("/")
    public RespPageBean getLeaveMessage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return leaveMessageService.getLeaveMessageByPage(page, size);
    }


    /**
     * 新增留言
     *
     * @param leaveMessage
     * @return
     */
    @ApiOperation(value = "新增留言", notes = "新增留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "leaveMessage", value = "留言对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 500, message = "新增失败")
    })
    @PostMapping("/")
    public RespBean addLeaveMessage(LeaveMessage leaveMessage) {
        User user = UserUtils.getCurrentUser(request);
        String remark = "";
        if (leaveMessageService.addLeaveMessage(request, leaveMessage) == 1) {
            remark = "新增留言成功，留言ID[{" + leaveMessage.getIleaveMessage() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("");
        } else {
            remark = "新增留言失败,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("系统错误，请稍后再试");
        }
    }

}
