package org.cxyxh.blogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.cxyxh.blogserver.mapper")
public class BlogserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogserverApplication.class, args);
	}

}
