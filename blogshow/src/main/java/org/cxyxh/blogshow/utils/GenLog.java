package org.cxyxh.blogshow.utils;

import org.cxyxh.blogshow.model.Address;
import org.cxyxh.blogshow.model.Log;
import org.cxyxh.blogshow.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/1 20:01
 * @describetion :
 */
public class GenLog {

    /**
     * 生成日志对象
     *
     * @param request
     * @return
     */
    public static Log genLog(HttpServletRequest request) {
        //获取路径
        String url = request.getScheme()
                + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getServletPath();
        //获取参数
        String parameter = request.getQueryString();
        if (parameter != null) {
            url += "?" + parameter;
        }
        //根据工具类获取数据
        String ip = UserAgentUtils.getIp(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        String browserVersion = UserAgentUtils.getBrowserVersion(request);
        String osName = UserAgentUtils.getOsName(request);
        Address addr = IPConvertUtils.getAddrByIp(ip);
        String remark = (String) request.getAttribute("remark");
        User user = (User) request.getSession().getAttribute("user");

        Log log = new Log();
        log.setProvince(addr.getProvince());
        log.setCity(addr.getCity());
        log.setIp(ip);
        log.setBrowserName(browserName);
        log.setBrowserVersion(browserVersion);
        log.setOperatingSystem(osName);
        if (user != null) {
            log.setIuser(user.getIuser());
        } else {
            log.setIuser(null);
        }
        log.setGmtCreate(new Date());
        log.setGmtModified(new Date());
        log.setUrl(url);
        log.setOperationalParameter(parameter);
        log.setLogType(Log.FRONT_OPERATE_LOG);
        log.setRemark(remark);

        return log;
    }

}
