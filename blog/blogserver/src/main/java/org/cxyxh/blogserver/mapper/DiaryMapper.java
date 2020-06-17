package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Diary;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: DiaryMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/16 22:42
 * @Version: 1.0
 */
public interface DiaryMapper {

	/**
	 * 根据页码，每页展示数量，日记对象，创建时间 查询日记
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param diary           日记对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	List<Diary> getDiaryByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("diary") Diary diary, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 根据日记对象，创建时间，查询日记总数
	 *
	 * @param diary           文章对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	Long getTotal(@Param("diary") Diary diary, @Param("createDateScope") Date[] createDateScope);

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
