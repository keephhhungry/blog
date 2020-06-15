package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.Diary;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DiaryController {


	@Autowired
	DiaryService diaryService;

	/**
	 * 查找日记
	 *
	 * @return
	 */
	@GetMapping("/")
	public RespBean getDiary() {
		List<Diary> diaries = diaryService.getDiary();
		return RespBean.ok("", diaries);
	}

}
