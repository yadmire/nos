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

@ApiModel(value="com-weiyx-nos-model-SysAuthority")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_authority")
public class SysAuthority {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "authority_code")
    @ApiModelProperty(value="")
    private String authorityCode;

    @TableField(value = "authority_name")
    @ApiModelProperty(value="")
    private String authorityName;

    @TableField(value = "description")
    @ApiModelProperty(value="")
    private String description;

    @TableField(value = "`status`")
    @ApiModelProperty(value="")
    private Integer status;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private Integer createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    private Date updateTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value="")
    private Integer updateBy;

    public static final String COL_ID = "id";

    public static final String COL_AUTHORITY_CODE = "authority_code";

    public static final String COL_AUTHORITY_NAME = "authority_name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_BY = "update_by";
}