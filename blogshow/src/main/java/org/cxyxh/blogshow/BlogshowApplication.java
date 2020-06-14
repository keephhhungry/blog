package org.cxyxh.blogshow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.cxyxh.blogshow.mapper")
public class BlogshowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogshowApplication.class, args);
	}

}
