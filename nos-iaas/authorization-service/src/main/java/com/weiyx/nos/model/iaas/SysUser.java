package com.weiyx.nos.model.iaas;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser {
    private Integer id; // 用户id
    private String username; // 用户名
    private String phone;
    private String email;
    private Integer state;
    private String password; // 密码
    @TableField("crate_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
}
