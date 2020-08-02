package org.cxyxh.blogserver.Interceptor;

import org.apache.commons.lang3.StringUtils;
import org.cxyxh.blogserver.controller.ArticleController;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.GenLog;
import org.cxyxh.blogserver.utils.UserAgentUtils;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/10 23:58
 * @describetion :
 */
@Configuration
public class LoginInterceptor implements HandlerInterceptor  {

    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String remark = (String)request.getAttribute("remark");
        if(!StringUtils.isBlank(remark)){
            Log log = GenLog.genLog(request);
            logService.addLog(log);
        }
    }

}
