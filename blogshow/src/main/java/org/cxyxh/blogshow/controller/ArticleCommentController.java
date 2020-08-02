package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.model.ArticleComment;
import org.cxyxh.blogshow.model.LeaveMessage;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.ArticleCommentService;
import org.cxyxh.blogshow.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: CommentController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "文章评论数据接口")
public class ArticleCommentController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ArticleCommentService commentService;

    /**
     * 根据文章id 查找评论
     *
     * @param iarticle 文章id
     * @return
     */
    @ApiOperation(value = "查询评论", notes = "根据文章ID查询相关评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticle", value = "文章ID", required = true),
    })
    @GetMapping("/{iarticle}")
    public RespBean getCommentByArticleId(@PathVariable Integer iarticle) {
        List<ArticleComment> comments = commentService.getCommentByArticleId(iarticle);
        return RespBean.ok("", comments);
    }


    /**
     * 新增文章评论
     *
     * @param articleComment
     * @return
     */
    @ApiOperation(value = "新增评论", notes = "新增评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleComment", value = "文章对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 500, message = "新增失败")
    })
    @PostMapping("/")
    public RespBean addArticleComment(ArticleComment articleComment) {
        User user = UserUtils.getCurrentUser(request);
        String remark = "";
        if (commentService.addArticleComment(request, articleComment) == 1) {
            remark = "新增评论成功，评论ID[{" + articleComment.getIarticleComment() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("");
        } else {
            remark = "新增评论失败,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("系统错误，请稍后再试");
        }
    }
}
