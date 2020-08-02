package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.exception.BlogException;
import org.cxyxh.blogshow.mapper.UserMapper;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.cxyxh.blogshow.utils.Const;
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
    public Integer register(User user) throws BlogException {

        //五个正则判断
        boolean usernameMatches = user.getUsername().matches(Const.USERNAME_PATTERN);
        if (!usernameMatches) {
            throw new BlogException("用户名输入不合法");
        }
        boolean nameMatches = user.getName().matches(Const.NAME_PATTERN);
        if (!nameMatches) {
            throw new BlogException("用户姓名输入不合法");
        }
        boolean passwordMatches = user.getPassword().matches(Const.PASSWORD_PATTERN);
        if (!passwordMatches) {
            throw new BlogException("用户密码输入不合法");
        }
        boolean emailMatches = user.getEmail().matches(Const.EMAIL_PATTERN);
        if (!emailMatches) {
            throw new BlogException("邮件地址输入不合法");
        }
        boolean telephoneMatches = user.getTelephone().matches(Const.TELEPHONE_PATTERN);
        if (!telephoneMatches) {
            throw new BlogException("电话不输入合法");
        }
        //判断用户名重复问题
        Integer num = userMapper.findUserCountByUsername(user.getUsername());
        if (num != 0) {
            throw new BlogException("该用户名已存在");
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
        boolean usernameMatches = user.getUsername().matches(Const.USERNAME_PATTERN);
        if (!usernameMatches) {
            return -2;
        }
        boolean nameMatches = user.getName().matches(Const.NAME_PATTERN);
        if (!nameMatches) {
            return -2;
        }
        boolean emailMatches = user.getEmail().matches(Const.EMAIL_PATTERN);
        if (!emailMatches) {
            return -2;
        }
        boolean telephoneMatches = user.getTelephone().matches(Const.TELEPHONE_PATTERN);
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
