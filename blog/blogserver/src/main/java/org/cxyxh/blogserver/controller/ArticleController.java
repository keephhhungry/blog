package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.ArticleService;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.cxyxh.blogserver.service.LogService;
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
 * @ClassName: Article
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/7 13:50
 * @Version: 1.0
 */

@RestController
@RequestMapping("/article/article")
@Api(tags = "文章数据接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;

    /**
     * 查询所有的文章类型
     *
     * @return
     */
    @ApiOperation(value = "查询文章类型", notes = "查询所有的文章类型")
    @GetMapping("/articleType")
    public RespPageBean getArticleType() {
        return articleTypeService.getArticleType();
    }

    /**
     * 根据页码，每页展示数量，文章对象，创建时间 查询文章
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param article         文章对象
     * @param createDateScope 创建时间
     * @return
     */
    @ApiOperation(value = "查询文章", notes = "根据页码，每页显示数量，文章对象和创建时间查询文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "article", value = "文章对象", required = false),
            @ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
    })
    @GetMapping("/")
    public RespPageBean getArticleByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Article article, Date[] createDateScope) {
        return articleService.getArticleByPage(page, size, article, createDateScope);
    }

    /**
     * 根据文章id，置顶文章
     *
     * @param iaricle
     * @return
     * @TODO
     */
//    @ApiOperation(value = "删除文章类型", notes = "根据文章类型ID，删除文章类型")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "iarticleType", value = "文章类型ID", required = true),
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "删除成功"),
//            @ApiResponse(code = 500, message = "删除失败")
//    })
//    @PutMapping("/top/{iaricle}")
//    public RespBean topAricleById(@PathVariable Integer iaricle) {
//        if (articleTypeService.topAricleById(iaricle) == 1) {
//            return RespBean.ok("置顶成功");
//        } else {
//            return RespBean.error("置顶失败");
//        }
//    }

    /**
     * 根据文章ID，删除文章
     *
     * @param iarticle 文章ID
     * @return
     */
    @ApiOperation(value = "删除文章", notes = "根据文章ID，删除文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticle", value = "文章ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{iarticle}")
    public RespBean deleteArticleById(@PathVariable Integer iarticle, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (articleService.deleteArticleById(iarticle) == 1) {
            remark = "删除文章成功，评论ID[{" + iarticle + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("删除成功");
        } else {
            remark = "删除文章失败，文章ID[{" + iarticle + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.error("删除失败");
        }
    }

    /**
     * 根据文章对象，添加文章
     *
     * @param article 文章对象
     * @return
     */
    @ApiOperation(value = "添加文章", notes = "根据文章对象，添加文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "文章对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PostMapping("/")
    public RespBean addArticle(@RequestBody Article article, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (articleService.addArticle(article) == 1) {
            remark = "添加文章成功，评论ID[{" + article.getIarticle() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("添加成功");
        } else {
            remark = "添加文章失败,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.error("添加失败");
        }
    }

    /**
     * 根据文章对象，修改文章
     *
     * @param article
     * @return
     */
    @ApiOperation(value = "修改文章", notes = "根据文章对象，修改文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "文章对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    @PutMapping("/")
    public RespBean updateArticleById(@RequestBody Article article, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (articleService.updateArticleById(article) == 1) {
            remark = "修改文章成功，评论ID[{" + article.getIarticle() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("修改成功");
        } else {
            remark = "修改文章失败，文章ID[{" + article.getIarticle() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.error("修改失败");
        }
    }
}
