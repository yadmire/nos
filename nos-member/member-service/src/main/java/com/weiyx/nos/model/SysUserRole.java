package com.weiyx.nos.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：weiyuxin
 * @date ：2021/7/12
 */
@Data
@TableName("sys_user_role")
public class SysUserRole {

    @TableField("user_id")
    private Integer userId;
    @TableField("roleCode")
    private String roleCode;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private String createTime;
}
