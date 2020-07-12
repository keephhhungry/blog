package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.UserMapper;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.UserService;
import org.cxyxh.blogserver.utils.DefaultParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service.impl
 * @ClassName: UserServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 0:12
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService{

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userMapper.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("账号不存在！");
        }
        user.setRoles(userMapper.getUserRolesById(user.getIuser()));
        return user;
    }


    /**
     * 分页查询用户信息
     * @param page 页码
     * @param size 每页显示数量
     * @param user 查询条件
     * @param createDateScope 创建时间区间
     * @return
     */
    @Override
    public RespPageBean getUserByPage(Integer page, Integer size, User user, Date[] createDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<User> users = userMapper.getUserByPage( page,  size,  user,createDateScope);
        Long total = userMapper.getTotal(user,createDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(users);
        bean.setTotal(total);
        return bean;
    }

    /**
     * 添加用户 用户对象
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(DefaultParams.DEFAULT_PASSWORD);
        user.setPassword(encode);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        user.setEnabled(true);
        return userMapper.addUser(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param iuser 用户ID
     * @return
     */
    @Override
    public Integer deleteUserById(Integer iuser) {
        return userMapper.deleteUserById(iuser);
    }

    /**
     * 根据ID数组，批量删除用户
     *
     * @param ids 用户ID数组
     * @return
     */
    @Override
    public Integer deleteUserByIds(Integer[] ids) {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 根据用户ID，重置用户密码
     *
     * @param iuser 用户ID
     * @return
     */
    @Override
    public Integer resetPasswordById(Integer iuser) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String defaultPassword = encode.encode(DefaultParams.DEFAULT_PASSWORD);
        return userMapper.resetPasswordById(iuser,defaultPassword);
    }

    /**
     * 根据用户ID，修改用户信息
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        user.setGmtModified(new Date());
        return userMapper.updateUser(user);
    }

    /**
     * 获取所有的角色
     *
     * @return
     */
    @Override
    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }


}
