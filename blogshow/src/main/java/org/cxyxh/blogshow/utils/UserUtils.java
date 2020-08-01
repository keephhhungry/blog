package org.cxyxh.blogshow.utils;

import org.cxyxh.blogshow.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.utils
 * @ClassName: UserUtil
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:35
 * @Version: 1.0
 */
public class UserUtils {

    private final static Logger logger = LoggerFactory.getLogger(UserUtils.class);

    /**
     * 获取登录用户的信息
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request) {
        User user = null;
        try {
            user = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            logger.warn("获取登录用户的信息失败，用户可能未登录");
        }
        return user;
    }
}
