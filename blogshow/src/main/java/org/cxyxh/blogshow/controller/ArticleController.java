package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.model.Article;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.ArticleService;
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
 * @ClassName: ArticleController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 17:06
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章数据接口")
public class ArticleController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ArticleService articleService;

    /**
     * 获取首页上的文章总数
     *
     * @return
     */
    @ApiOperation(value = "获取首页文章总数", notes = "获取首页上的文章总数")
    @GetMapping("/getHomeArticleNum")
    public RespBean getHomeArticleNum() {
        int num = articleService.getHomeArticleNum();
        return RespBean.ok("", num);
    }

    /**
     * 获取首页上的文章(7)
     *
     * @param iarticleType 文章类型id
     * @return
     */
    @ApiOperation(value = "获取首页文章", notes = "获取首页文章（获取前7篇）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticleType", value = "文章类型", required = false),
    })
    @GetMapping("/getHomeArticle")
    public RespBean getHomeArticle(Integer iarticleType) {
        List<Article> articles = articleService.getHomeArticle(iarticleType);
        return RespBean.ok("", articles);
    }

    /**
     * 获取首页的热门文章(10)
     *
     * @return
     */
    @ApiOperation(value = "获取首页热门文章", notes = "获取首页热门文章（获取前10篇）")
    @GetMapping("getHotArticle")
    public RespBean getHotArticle() {
        List<Article> articles = articleService.getHotArticle();
        return RespBean.ok("", articles);
    }

    /**
     * 根据文章id，获取文章
     *
     * @param iarticle 文章id
     * @return
     */
    @ApiOperation(value = "获取文章", notes = "根据文章ID，获取文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticle", value = "文章ID", required = true),
    })
    @GetMapping("/{iarticle}")
    public RespBean getArticleById(@PathVariable Integer iarticle) {
        Article article = articleService.getArticleById(iarticle);
        return RespBean.ok("", article);
    }

    /**
     * 根据文章id 新增文章阅读量
     *
     * @param iarticle
     * @return
     */
    @ApiOperation(value = "新增文章查看数量", notes = "根据文章ID，新增文章查看数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticle", value = "文章ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 500, message = "新增失败")
    })
    @PutMapping("/addLookNum")
    public void addLookNum(Integer iarticle) {
        articleService.addLookNum(iarticle);
    }
}
