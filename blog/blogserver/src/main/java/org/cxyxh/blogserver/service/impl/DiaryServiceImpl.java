package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.DiaryMapper;
import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.Diary;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: DiaryServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/16 22:41
 * @Version: 1.0
 */
@Service
public class DiaryServiceImpl implements DiaryService {

	private final static Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);

	@Autowired
	DiaryMapper diaryMapper;

	/**
	 * 根据页码，每页展示数量，日记对象，创建时间 查询日记
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param diary           日记对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@Override
	public RespPageBean getDiaryByPage(Integer page, Integer size, Diary diary, Date[] createDateScope) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<Diary> diaries = diaryMapper.getDiaryByPage(page, size, diary, createDateScope);
		Long total = diaryMapper.getTotal(diary, createDateScope);
		RespPageBean bean = new RespPageBean();
		bean.setData(diaries);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 根据ID 删除日记
	 *
	 * @param idiary 日记ID
	 * @return
	 */
	@Override
	public Integer deleteDiaryById(Integer idiary) {
		return diaryMapper.deleteDiaryById(idiary);
	}

	/**
	 * 新增日记
	 *
	 * @param diary 日记对象
	 * @return
	 */
	@Override
	public Integer addDiary(Diary diary) {
		diary.setStatus(1);
		diary.setGmtCreate(new Date());
		diary.setGmtModified(new Date());
		return diaryMapper.addDiary(diary);
	}

	/**
	 * 根据日记id 修改日记
	 *
	 * @param diary
	 * @return
	 */
	@Override
	public Integer updateDiaryById(Diary diary) {
		diary.setGmtModified(new Date());
		return diaryMapper.updateDiaryById(diary);
	}
}
