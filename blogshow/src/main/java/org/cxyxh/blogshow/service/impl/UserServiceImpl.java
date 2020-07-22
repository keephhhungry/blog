package org.cxyxh.blogshow.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.cxyxh.blogshow.mapper.UserMapper;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.cxyxh.blogshow.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: UserServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 23:08
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer register(User user) {
        //五个正则判断
        boolean usernameMatches = user.getUsername().matches(DefaultParams.USERNAME_PATTERN);
        if (!usernameMatches) {
            return -2;
        }
        boolean nameMatches = user.getName().matches(DefaultParams.NAME_PATTERN);
        if (!nameMatches) {
            return -2;
        }
        boolean passwordMatches = user.getPassword().matches(DefaultParams.PASSWORD_PATTERN);
        if (!passwordMatches) {
            return -2;
        }
        boolean emailMatches = user.getEmail().matches(DefaultParams.EMAIL_PATTERN);
        if (!emailMatches) {
            return -2;
        }
        boolean telephoneMatches = user.getTelephone().matches(DefaultParams.TELEPHONE_PATTERN);
        if (!telephoneMatches) {
            return -2;
        }
        //判断用户名重复问题
        Integer num = userMapper.findUserCountByUsername(user.getUsername());
        if (num != 0) {
            return -1;
        }
        //密码加密


        user.setRemark("");
        user.setEnabled(true);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        //插入数据
        return userMapper.register(user);
    }

    /**
     * 修改用户名时测试是否可用
     *
     * @param username 用户名
     * @return
     */
    @Override
    public Integer findUserCountByUsername(String username) {
        return userMapper.findUserCountByUsername(username);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        //五个正则判断
        boolean usernameMatches = user.getUsername().matches(DefaultParams.USERNAME_PATTERN);
        if (!usernameMatches) {
            return -2;
        }
        boolean nameMatches = user.getName().matches(DefaultParams.NAME_PATTERN);
        if (!nameMatches) {
            return -2;
        }
        boolean emailMatches = user.getEmail().matches(DefaultParams.EMAIL_PATTERN);
        if (!emailMatches) {
            return -2;
        }
        boolean telephoneMatches = user.getTelephone().matches(DefaultParams.TELEPHONE_PATTERN);
        if (!telephoneMatches) {
            return -2;
        }
        //判断用户名重复问题 判断该用户名在数据库是否存在，如果存在，是否是本身
        Integer num = userMapper.findUserCountByUsername(user.getUsername());
        if (num != 0) {
            User findUser = userMapper.findUserByUsername(user.getUsername());
            if (findUser != null && !findUser.getIuser().equals(user.getIuser())) {
                return -1;
            }
        }
        user.setGmtModified(new Date());
        return userMapper.updateUser(user);
    }

}
