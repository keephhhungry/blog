package org.cxyxh.blogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "org.cxyxh.blogserver.mapper")
@EnableScheduling
@EnableSwagger2
public class BlogserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogserverApplication.class, args);
	}

}
