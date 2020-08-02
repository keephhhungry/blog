package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.User; /**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: UserService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 23:07
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
	Integer register(User user) throws Exception;

    /**
     * 修改用户名时测试是否可用
     *
     * @param username 用户名
     * @return
     */
    Integer findUserCountByUsername(String username);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);
}
