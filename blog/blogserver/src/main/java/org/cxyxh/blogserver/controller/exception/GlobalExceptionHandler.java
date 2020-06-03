package org.cxyxh.blogserver.controller.exception;

import org.cxyxh.blogserver.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller.exception
 * @ClassName: GlobalException
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/30 13:13
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(SQLException.class)
	public RespBean sqlException(SQLException e) {
		if (e instanceof SQLIntegrityConstraintViolationException) {
			return RespBean.error("该数据有关联数据，操作失败!");
		}
		return RespBean.error("数据库异常，操作失败!");
	}
}