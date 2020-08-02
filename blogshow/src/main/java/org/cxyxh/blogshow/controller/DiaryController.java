package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.model.Diary;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: DiaryController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/15 20:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("/diary")
@Api(tags = "日记数据接口")
public class DiaryController {

	private final static Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	DiaryService diaryService;

	/**
	 * 查找日记
	 *
	 * @return
	 */
	@ApiOperation(value = "获取日记", notes = "获取日记")
	@GetMapping("/")
	public RespBean getDiary() {
		List<Diary> diaries = diaryService.getDiary();
		return RespBean.ok("", diaries);
	}

}
