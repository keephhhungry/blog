package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.controller.UserController;
import org.cxyxh.blogserver.mapper.ArticleCommentMapper;
import org.cxyxh.blogserver.model.Article;
import org.cxyxh.blogserver.model.ArticleComment;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.ArticleCommentService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: CommentServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 23:47
 * @Version: 1.0
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	private final static Logger logger = LoggerFactory.getLogger(ArticleCommentServiceImpl.class);

	@Autowired
	ArticleCommentMapper articleCommentMapper;

	/**
	 * 根据页码，每页展示数量，评论对象，创建时间 查询评论
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param articleComment  评论对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@Override
	public RespPageBean getArticleCommentByPage(Integer page, Integer size, ArticleComment articleComment, Date[] createDateScope) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<ArticleComment> articleComments = articleCommentMapper.getArticleCommentByPage(page, size, articleComment, createDateScope);
		Long total = articleCommentMapper.getTotal(articleComment, createDateScope);
		RespPageBean bean = new RespPageBean();
		bean.setData(articleComments);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 查看该评论是否有子评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	@Override
	public Integer getArticleCommentChildrenById(Integer iarticleComment) {
		return articleCommentMapper.getArticleCommentChildrenById(iarticleComment);
	}

	/**
	 * 根据ID删除评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	@Override
	public Integer deleteArticleCommentById(Integer iarticleComment) {
		return articleCommentMapper.deleteArticleCommentById(iarticleComment);
	}

	/**
	 * 根据ID强制删除评论和子评论
	 *
	 * @param iarticleComment 评论id
	 * @return
	 */
	@Override
	public Integer enforceDeleteArticleCommentById(Integer iarticleComment) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(iarticleComment);
		//递归获取所有的子留言id集合
		for (int i = 0; i < ids.size(); i++) {
			List<Integer> leaveMessageChildren = getArticleCommentChildren(ids.get(i));
			ids.addAll(leaveMessageChildren);
		}
		return articleCommentMapper.deleteArticleCommentByIds(ids);
	}

	/**
	 * 通过父id，查找子id
	 *
	 * @param ileaveMessage 父id
	 * @return
	 */
	public List<Integer> getArticleCommentChildren(Integer ileaveMessage) {
		return articleCommentMapper.getArticleCommentChildren(ileaveMessage);
	}

}
