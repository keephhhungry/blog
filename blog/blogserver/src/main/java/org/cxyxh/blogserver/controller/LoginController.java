package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.utils.VerificationCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: LoginController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 14:47
 * @Version: 1.0
 */
@RestController
public class LoginController {

	@GetMapping("/login")
	public RespBean login() {
		return RespBean.error("尚未登录");
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
