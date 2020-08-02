package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Role;

import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/2 23:06
 * @describetion :
 */
public interface UserAndRoleMapper {

    /**
     * 根据用户名，修改角色集合
     *
     * @param iuser
     */
    void deleteRolesById(Integer iuser);

    /**
     * 添加角色
     *
     * @param iuser
     * @param roles
     */
    void addRoles(@Param("iuser") Integer iuser, @Param("roles")List<Role> roles);

    /**
     * 查看拥有该角色的用户数量
     *
     * @param irole
     * @return
     */
    Integer getUserNumByRoleId(@Param("irole") Integer irole);
}
