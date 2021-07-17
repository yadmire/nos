package com.weiyx.nos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-weiyx-nos-model-SysUserRole")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_role")
public class SysUserRole {
    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "role_code")
    @ApiModelProperty(value="")
    private String roleCode;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private String createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ROLE_CODE = "role_code";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";
}