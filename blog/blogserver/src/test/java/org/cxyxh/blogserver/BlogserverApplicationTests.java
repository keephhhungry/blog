package org.cxyxh.blogserver;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.*;
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

	@Autowired
	ArticleTypeService articleTypeService;

	@Autowired
	LogService logService;

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
		addArticle();
	}


	void addArticle() {
		for (int i = 0; i < 50; i++) {
			Article article = new Article();
			article.setArticleAuthor("作者" + i);
			article.setArticleContent("文章内容" + i);
			article.setArticleIntroduction("文章简介" + i);
			article.setArticleTitle("文章标题" + i);
			article.setIarticleType(1);
			article.setStatus(1);
			article.setIsOriginal(1);
			articleService.addArticle(article);
		}
	}

	void addLog() {
		for (int i = 0; i < 50; i++) {
			Log log = new Log();
			log.setIuser(2);
			log.setAddress("测试地址" + i);
			log.setBrowser("测试浏览器" + i);
			log.setGmtCreate(new Date());
			log.setGmtModified(new Date());
			log.setIp("测试IP" + i);
			log.setLogType(3);
			log.setOperatingSystem("操作系统" + i);
			log.setOperationalParameter("操作参数" + i);
			log.setRemark("备注" + i);
			log.setUrl("路径+" + i);
			logService.addLog(log);
		}
	}

	void addArticleType() {
		for (int i = 0; i < 50; i++) {
			ArticleType articleType = new ArticleType();
			articleType.setTypeName("测试" + i);
			articleType.setGmtModified(new Date());
			articleType.setGmtCreate(new Date());
			articleTypeService.addArticleType(articleType);
		}
	}

	void addFriendLink() {
		for (int i = 0; i < 50; i++) {
			FriendLink friendLink = new FriendLink();
			friendLink.setLinkName("测试" + i);
			friendLink.setLinkUrl("url" + i);
			friendLink.setRemark("备注" + i);
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
