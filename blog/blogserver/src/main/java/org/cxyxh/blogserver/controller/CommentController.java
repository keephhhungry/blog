package org.cxyxh.blogserver.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.cxyxh.blogserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: CommentController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("")
public class CommentController {

	@Autowired
	CommentService commentService;

}
