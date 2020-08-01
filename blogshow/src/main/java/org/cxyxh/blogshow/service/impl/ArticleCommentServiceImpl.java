package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.ArticleCommentMapper;
import org.cxyxh.blogshow.model.Address;
import org.cxyxh.blogshow.model.ArticleComment;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.ArticleCommentService;
import org.cxyxh.blogshow.utils.IPConvertUtils;
import org.cxyxh.blogshow.utils.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: CommentServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 11:25
 * @Version: 1.0
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    /**
     * 根据文章id 查找评论
     *
     * @param iarticle 文章id
     * @return
     */
    @Override
    public List<ArticleComment> getCommentByArticleId(Integer iarticle) {
        List<ArticleComment> comments = articleCommentMapper.getCommentByArticleId(iarticle);
        for (ArticleComment comment : comments) {
            List<ArticleComment> children = articleCommentMapper.getCommentByCommentId(comment.getIarticleComment());
            comment.setChildren(children);
        }
        return comments;
    }

    /**
     * 新增文章评论
     *
     * @param request
     * @param articleComment
     * @return
     */
    @Override
    public Integer addArticleComment(HttpServletRequest request, ArticleComment articleComment) {
        //根据工具类获取数据
        String ip = UserAgentUtils.getIp(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        String browserVersion = UserAgentUtils.getBrowserVersion(request);
        String osName = UserAgentUtils.getOsName(request);
        Address addr = IPConvertUtils.getAddrByIp(ip);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //赋值
        articleComment.setProvince(addr.getProvince());
        articleComment.setCity(addr.getCity());
        articleComment.setIp(ip);
        articleComment.setBrowserName(browserName);
        articleComment.setBrowserVersion(browserVersion);
        articleComment.setOperatingSystem(osName);
        articleComment.setIuser(user.getIuser());
        articleComment.setGmtCreate(new Date());
        articleComment.setGmtModified(new Date());
        return articleCommentMapper.addArticleComment(articleComment);
    }
}
