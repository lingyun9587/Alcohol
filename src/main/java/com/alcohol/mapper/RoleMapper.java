package com.alcohol.mapper;

import com.alcohol.pojo.Role;

import java.util.List;

/**
 * 角色接口
 * @ 陈赓
 */
public interface RoleMapper {

    /**
     * 根据会员名、手机号、邮箱号查询角色
     * @param username
     * @return
     */
    List<Role> queryRolesByUserName(String username);
}
