package com.alcohol.pojo;

import lombok.Data;

/**
 * 胡博
 * 用户权限表
 */
@Data
public class Permission {
    private Long permissionId;
    private Long userId;
    private Long roleId;
}
