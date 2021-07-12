package com.weiyx.nos.model.iaas;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ：weiyuxin
 * @date ：2021/7/12
 */
@Data
@TableName("sys_role")
public class SysRole {
    private Integer id;
    @TableField("role_code")
    private String roleCode;
    private String description;
    @TableField("crate_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;

}
