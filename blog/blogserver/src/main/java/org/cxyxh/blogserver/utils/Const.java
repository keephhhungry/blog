package org.cxyxh.blogserver.utils;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.utils
 * @ClassName: DefaultParams
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/29 21:46
 * @Version: 1.0
 */
public class Const {

	/* 默认密码 */
	public static final String DEFAULT_PASSWORD = "123";
	/* 登录日志 */
	public static final Integer LOGIN_LOG = 1;
	/* 前台日志 */
	public static final Integer VISIT_LOG = 2;
	/* 后台日志 */
	public static final Integer OPERATE_LOG = 3;
	/* 默认文章类型id */
	public static final Integer DEFAULT_ARTICLE_TYPE = 1;

}
