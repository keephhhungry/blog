package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.UserAndRoleMapper;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.service.UserAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/2 23:06
 * @describetion :
 */
@Service
public class UserAndRoleServiceImpl implements UserAndRoleService {


    @Autowired
    private UserAndRoleMapper userAndRoleMapper;

    /**
     * 根据用户名，修改角色集合
     *
     * @param iuser
     * @param roles
     */
    @Transactional
    @Override
    public void updateRoleById(Integer iuser, List<Role> roles) {
        //根据用户ID，删除角色
        userAndRoleMapper.deleteRolesById(iuser);
        //添加角色
        if (roles != null && roles.size() != 0) {
            userAndRoleMapper.addRoles(iuser, roles);
        }
    }

    /**
     * 查看拥有该角色的用户数量
     *
     * @param irole
     * @return
     */
    @Override
    public Integer getUserNumByRoleId(Integer irole) {
        return userAndRoleMapper.getUserNumByRoleId(irole);
    }

}
