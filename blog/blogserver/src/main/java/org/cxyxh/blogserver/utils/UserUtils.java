package org.cxyxh.blogserver.utils;

import org.cxyxh.blogserver.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

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
    /**
     * 获取登录用户的信息
     * @return
     */
    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
