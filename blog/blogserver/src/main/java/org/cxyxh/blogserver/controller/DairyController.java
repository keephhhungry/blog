package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.Diary;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: DairyController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/16 22:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/diary")
public class DairyController {

	@Autowired
	DiaryService diaryService;


	/**
	 * 根据页码，每页展示数量，日记对象，创建时间 查询日记
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param diary           日记对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getDiaryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Diary diary, Date[] createDateScope) {
		return diaryService.getDiaryByPage(page, size, diary, createDateScope);
	}


	/**
	 * 根据ID 删除日记
	 *
	 * @param idiary 日记ID
	 * @return
	 */
	@DeleteMapping("/{idiary}")
	public RespBean deleteDiaryById(@PathVariable Integer idiary) {
		if (diaryService.deleteDiaryById(idiary) == 1) {
			return RespBean.ok("删除成功");
		} else {
			return RespBean.error("删除失败");
		}
	}

	/**
	 * 新增日记
	 *
	 * @param diary 日记对象
	 * @return
	 */
	@PostMapping("/")
	public RespBean addDiary(@RequestBody Diary diary) {
		if (diaryService.addDiary(diary) == 1) {
			return RespBean.ok("新增成功");
		} else {
			return RespBean.error("新增失败");
		}
	}

	/**
	 * 根据日记id 修改日记
	 *
	 * @param diary
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateDiaryById(@RequestBody Diary diary) {
		if (diaryService.updateDiaryById(diary) == 1) {
			return RespBean.ok("修改成功");
		} else {
			return RespBean.error("修改失败");
		}
	}

}
