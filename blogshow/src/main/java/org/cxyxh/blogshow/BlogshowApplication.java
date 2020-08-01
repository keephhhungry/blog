package org.cxyxh.blogshow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "org.cxyxh.blogshow.mapper")
@EnableSwagger2
public class BlogshowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogshowApplication.class, args);
	}

}
