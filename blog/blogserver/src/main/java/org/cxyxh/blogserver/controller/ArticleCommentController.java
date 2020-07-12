package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.ArticleCommentService;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: CommentController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article/comment")
@Api(tags = "文章评论数据接口")
public class ArticleCommentController {

    @Autowired
    private ArticleCommentService commentService;

    /**
     * 根据页码，每页展示数量，评论对象，创建时间 查询评论
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param articleComment  评论对象
     * @param createDateScope 创建时间
     * @return
     */
    @ApiOperation(value = "查询文章评论", notes = "根据页码，每页显示数量，评论对象和创建时间查询文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "articleComment", value = "评论对象", required = false),
            @ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
    })
    @GetMapping("/")
    public RespPageBean getArticleCommentByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, ArticleComment articleComment, Date[] createDateScope) {
        return commentService.getArticleCommentByPage(page, size, articleComment, createDateScope);
    }

    /**
     * 根据评论ID，删除文章评论
     *
     * @param iarticleComment 评论ID
     * @return
     */
    @ApiOperation(value = "删除评论", notes = "根据评论ID，删除文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticleComment", value = "评论ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{iarticleComment}")
    public RespBean deleteArticleCommentById(@PathVariable Integer iarticleComment, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        int num = commentService.getArticleCommentChildrenById(iarticleComment);
        //如果没有子留言
        if (num == 0) {
            if (commentService.deleteArticleCommentById(iarticleComment) == 1) {
                remark = "删除评论成功，评论ID[{"+iarticleComment+"}],操作人ID[{"+user.getIuser()+"}],操作人名字[{"+user.getUsername()+"}]";
                request.setAttribute("remark",remark);
                return RespBean.ok("删除成功!");
            }
            remark = "删除评论失败，评论ID[{"+iarticleComment+"}],操作人ID[{"+user.getIuser()+"}],操作人名字[{"+user.getUsername()+"}]";
            request.setAttribute("remark",remark);
            return RespBean.error("删除失败!");
        } else {
            remark = "删除评论失败，该评论下有回复，评论ID[{"+iarticleComment+"}],操作人ID[{"+user.getIuser()+"}],操作人名字[{"+user.getUsername()+"}]";
            request.setAttribute("remark",remark);
            return RespBean.error("删除失败，该评论下有回复", -1);
        }

    }

    /**
     * 根据评论ID，不管是否有下级，强制删除评论
     *
     * @param iarticleComment
     * @return
     */
    @ApiOperation(value = "删除评论", notes = "根据评论ID，不管是否有下级，强制删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticleComment", value = "评论ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/enforce/{iarticleComment}")
    public RespBean enforceDeleteArticleCommentById(@PathVariable Integer iarticleComment, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        int num = commentService.enforceDeleteArticleCommentById(iarticleComment);
        if (num != 0) {
            remark = "删除评论成功，评论ID[{"+iarticleComment+"}],操作人ID[{"+user.getIuser()+"}],操作人名字[{"+user.getUsername()+"}]";
            request.setAttribute("remark",remark);
            return RespBean.ok("成功删除" + num + "条数据");
        }
        remark = "删除评论失败，评论ID[{"+iarticleComment+"}],操作人ID[{"+user.getIuser()+"}],操作人名字[{"+user.getUsername()+"}]";
        request.setAttribute("remark",remark);
        return RespBean.error("删除失败!");
    }
}
