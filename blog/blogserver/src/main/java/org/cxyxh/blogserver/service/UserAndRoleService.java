package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.Role;

import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/2 23:05
 * @describetion :
 */
public interface UserAndRoleService {

    /**
     * 根据用户名，修改角色集合
     *
     * @param iuser
     * @param roles
     */
    void updateRoleById(Integer iuser, List<Role> roles);

    /**
     * 查看拥有该角色的用户数量
     *
     * @param irole
     * @return
     */
    Integer getUserNumByRoleId(Integer irole);

}
