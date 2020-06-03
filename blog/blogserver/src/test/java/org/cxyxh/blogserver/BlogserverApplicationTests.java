package org.cxyxh.blogserver;

import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.FriendLinkService;
import org.cxyxh.blogserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class BlogserverApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	FriendLinkService friendLinkService;

	@Test
	void contextLoads() {
		addFriendLink();
	}

	void addFriendLink(){
		for (int i = 0; i < 50; i++) {
			FriendLink friendLink = new FriendLink();
			friendLink.setLinkName("测试"+i);
			friendLink.setLinkUrl("url"+i);
			friendLink.setRemark("备注"+i);
			friendLink.setGmtCreate(new Date());
			friendLink.setGmtModified(new Date());
			friendLinkService.addFriendLink(friendLink);
		}
	}

	void addUser() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode("123");
		for (int i = 100; i < 200; i++) {
			User user = new User();
			user.setUsername("Test" + i);
			user.setName("测试账号" + i);
			user.setPassword(encode);
			user.setAge(1);
			user.setSex(1);
			user.setEmail("5440022@qq.com");
			user.setTelephone("1300356445");
			user.setUserFace("userface");
			user.setRemark("备注");
			user.setEnabled(true);
			user.setGmtCreate(new Date());
			user.setGmtModified(new Date());
			userService.addUser(user);
		}
	}

}
