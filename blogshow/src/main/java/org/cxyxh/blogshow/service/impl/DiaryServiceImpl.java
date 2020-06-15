package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.DiaryMapper;
import org.cxyxh.blogshow.model.Diary;
import org.cxyxh.blogshow.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: DiaryServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/15 20:03
 * @Version: 1.0
 */
@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryMapper diaryMapper;

	/**
	 * 查找日记
	 *
	 * @return
	 */
	@Override
	public List<Diary> getDiary() {
		return diaryMapper.getDiary();
	}
}
