package com.weiyx.nos.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ：weiyuxin
 * @date ：2021/7/12
 */
@Data
@TableName("sys_role_authority")
public class SysRoleAuthority {
    @TableField("role_id")
    private Integer roleId;
    @TableField("authority_id")
    private Integer authorityId;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
}
