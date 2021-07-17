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

@ApiModel(value="com-weiyx-nos-model-SysRoleAuthority")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_authority")
public class SysRoleAuthority {
    @TableId(value = "role_id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer roleId;

    @TableField(value = "authority_id")
    @ApiModelProperty(value="")
    private Integer authorityId;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private String createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_AUTHORITY_ID = "authority_id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";
}