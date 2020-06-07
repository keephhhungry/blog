package org.cxyxh.blogserver.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller.converter
 * @ClassName: DateConverter
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/30 13:12
 * @Version: 1.0
 */
@Component
public class DateConverter implements Converter<String, Date> {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date convert(String source) {
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
