package org.cxyxh.blogshow.utils;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.utils
 * @ClassName: DefaultParams
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/29 21:46
 * @Version: 1.0
 */
public class DefaultParams {

	//用户名正则表达式
	public static final String USERNAME_PATTERN = "^[\\u4e00-\\u9fa5_a-zA-Z0-9·-]{2,16}$";

	//真实姓名正则表达式
	public static final String NAME_PATTERN = "^[\\u4e00-\\u9fa5_a-zA-Z0-9·-]{2,20}$";

	//密码正则表达式
	public static final String PASSWORD_PATTERN = "^[\\w~!@#$%^&*()_+{}|:\"<>?.-]{8,16}$";

	//email正则表达式
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

	//电话正则表达式
	public static final String TELEPHONE_PATTERN = "^1[3456789]\\d{9}$";

}
