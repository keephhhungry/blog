package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.Diary;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: DiaryService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/15 20:03
 * @Version: 1.0
 */
public interface DiaryService {

	/**
	 * 查找日记
	 *
	 * @return
	 */
	List<Diary> getDiary();
}
