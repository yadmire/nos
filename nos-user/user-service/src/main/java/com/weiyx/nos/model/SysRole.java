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

@ApiModel(value="com-weiyx-nos-model-SysRole")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "role_code")
    @ApiModelProperty(value="")
    private String roleCode;

    @TableField(value = "role_name")
    @ApiModelProperty(value="")
    private String roleName;

    @TableField(value = "description")
    @ApiModelProperty(value="")
    private String description;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private Integer createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value="")
    private Integer updateBy;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_CODE = "role_code";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}