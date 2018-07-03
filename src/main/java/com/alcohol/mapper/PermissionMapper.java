package com.alcohol.mapper;

import com.alcohol.pojo.Permission;

public interface PermissionMapper {

    /**
     * 添加用户角色权限
     * @param permission
     * @return
     */
    int insertInfo(Permission permission);
}
