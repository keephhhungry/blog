package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.Interceptor.LoginInterceptor;
import org.cxyxh.blogserver.exception.BlogException;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.ArticleService;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: ArticleTypeController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/3 22:21
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article/type")
@Api(tags = "文章类型数据接口")
public class ArticleTypeController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleTypeController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private LogService logService;

    /**
     * 根据页码，每页显示数量和关键字查询文章类型
     *
     * @param page    页码
     * @param size    每页展示数量
     * @param keyword 关键字
     * @return
     */
    @ApiOperation(value = "查询文章类型", notes = "根据页码，每页显示数量和关键字查询文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "keyword", value = "文章类型名称", required = false)
    })
    @GetMapping("/")
    public RespPageBean getArticleTypeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
        return articleTypeService.getArticleTypeByPage(page, size, keyword);
    }

    /**
     * 根据文章类型ID，删除文章类型
     *
     * @param iarticleType 文章类型ID
     * @return
     */
    @ApiOperation(value = "删除文章类型", notes = "根据文章类型ID，删除文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticleType", value = "文章类型ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{iarticleType}")
    public RespBean deleteArticleTypeById(@PathVariable Integer iarticleType, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        // 无法删除 "其他" 分组
        if (iarticleType == 1) {
            remark = "删除文章类型失败，该分组为默认类型，无法删除，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.warn(remark);
            return RespBean.error("该分组为默认类型，无法删除");
        } else {
            // 查看该类型下是否有文章
            List<Integer> ids = articleService.getArticelsByTypeId(iarticleType);
            if (ids.size() == 0) {
                if (articleTypeService.deleteArticleTypeById(iarticleType) == 1) {
                    remark = "删除文章类型成功，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                    request.setAttribute("remark", remark);
                    logger.info(remark);
                    return RespBean.ok("删除成功");
                } else {
                    remark = "删除文章类型失败，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                    request.setAttribute("remark", remark);
                    logger.error(remark);
                    return RespBean.error("删除失败");
                }
            } else {
                remark = "删除失败，该类型下仍有文章，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                request.setAttribute("remark", remark);
                logger.warn(remark);
                return RespBean.error("删除失败，该类型下仍有文章", -1);
            }
        }
    }

    /**
     * 根据文章类型ID，强制删除文章类型，并且把该类型下的文章转移至'其他'类型下
     *
     * @param iarticleType 文章类型ID
     * @return
     */
    @ApiOperation(value = "强制删除文章类型", notes = "根据文章类型ID，强制删除文章类型，并且把该类型下的文章转移至'其他'类型下")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iarticleType", value = "文章类型ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("enforce/{iarticleType}")
    public RespBean enforceDeleteArticleTypeById(@PathVariable Integer iarticleType, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        try {
            if (articleTypeService.enforceDeleteArticleTypeById(iarticleType)) {
                remark = "强制删除文章类型成功，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                request.setAttribute("remark", remark);
                logger.info(remark);
                return RespBean.ok("强制删除成功");
            } else {
                remark = "强制删除文章类型失败，文章类型ID[{" + iarticleType + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                request.setAttribute("remark", remark);
                logger.error(remark);
                return RespBean.ok("强制删除失败");
            }
        } catch (BlogException e) {
            remark = "删除文章类型失败，原因：" + e.getMessage();
            logger.error(remark);
            request.setAttribute("remark", remark);
            return RespBean.error(e.getMessage(), "");
        }
    }

    /**
     * 根据文章类型对象，添加文章类型
     *
     * @param articleType 文章类型对象
     * @return
     */
    @ApiOperation(value = "添加文章类型", notes = "根据文章类型对象，添加文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleType", value = "文章类型对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PostMapping("/")
    public RespBean addArticleType(@RequestBody ArticleType articleType, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (articleTypeService.addArticleType(articleType) == 1) {
            remark = "添加文章类型成功，文章类型ID[{" + articleType.getIarticleType() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("添加成功");
        } else {
            remark = "添加文章类型失败,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("添加失败");
        }
    }

    /**
     * 根据文章类型ID，修改文章类型
     *
     * @param articleType 文章类型对象
     * @return
     */
    @ApiOperation(value = "修改文章类型", notes = "根据文章类型ID，修改文章类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleType", value = "文章类型对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PutMapping("/")
    public RespBean updateArticleTypeById(@RequestBody ArticleType articleType, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (articleTypeService.updateArticleTypeById(articleType) == 1) {
            remark = "修改文章类型成功，文章类型ID[{" + articleType.getIarticleType() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("修改成功");
        } else {
            remark = "修改文章类型失败，文章类型ID[{" + articleType.getIarticleType() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("修改失败");
        }
    }
}
