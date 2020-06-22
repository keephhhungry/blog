package org.cxyxh.blogshow.controller;

import org.apache.commons.lang3.StringUtils;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LoginService;
import org.cxyxh.blogshow.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: LoginController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/22 21:04
 * @Version: 1.0
 */
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * 登录
	 *
	 * @param request
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@PostMapping("/doLogin")
	public RespBean login(HttpServletRequest request, String username, String password, String code) {
		System.out.println("username="+username);
		System.out.println("password="+password);
		System.out.println("code="+code);
		HttpSession session = request.getSession();
		String verifyCode = (String) session.getAttribute("verify_code");
		//判断是否有数据
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)) {
			return RespBean.error(" 请输入相关数据", -3);
		}
		//判断验证码
		if (!StringUtils.equalsIgnoreCase(code, verifyCode)) {
			return RespBean.error("验证码错误，请重新输入", -2);
		}
		User user = loginService.login(username, password);
		if (user != null) {
			user.setPassword("");
			session.setAttribute("user", user);
			return RespBean.ok("登录成功", user);
		}
		return RespBean.ok("账户或者密码错误，请重新输入", -1);


	}


	/**
	 * 获取验证码
	 *
	 * @param request
	 * @param resp
	 * @throws IOException
	 */
	@GetMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		VerificationCode code = new VerificationCode();
		BufferedImage image = code.getImage();
		String text = code.getText();
		HttpSession session = request.getSession(true);
		session.setAttribute("verify_code", text);
		VerificationCode.output(image, resp.getOutputStream());
	}
}
