package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.Diary;
import org.cxyxh.blogserver.model.RespPageBean;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: DiaryService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/16 22:41
 * @Version: 1.0
 */
public interface DiaryService {
	/**
	 * 根据页码，每页展示数量，日记对象，创建时间 查询日记
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param diary           日记对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	RespPageBean getDiaryByPage(Integer page, Integer size, Diary diary, Date[] createDateScope);

	/**
	 * 根据ID 删除日记
	 *
	 * @param idiary 日记ID
	 * @return
	 */
	Integer deleteDiaryById(Integer idiary);

	/**
	 * 新增日记
	 *
	 * @param diary 日记对象
	 * @return
	 */
	Integer addDiary(Diary diary);

	/**
	 * 根据日记id 修改日记
	 *
	 * @param diary
	 * @return
	 */
	Integer updateDiaryById(Diary diary);
}
