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

@ApiModel(value="com-weiyx-nos-model-SysChangeLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_change_log")
public class SysChangeLog {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "`table`")
    @ApiModelProperty(value="")
    private String table;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private String createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "old_value")
    @ApiModelProperty(value="")
    private String oldValue;

    @TableField(value = "new_value")
    @ApiModelProperty(value="")
    private String newValue;

    @TableField(value = "change_type")
    @ApiModelProperty(value="")
    private String changeType;

    public static final String COL_ID = "id";

    public static final String COL_TABLE = "table";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_OLD_VALUE = "old_value";

    public static final String COL_NEW_VALUE = "new_value";

    public static final String COL_CHANGE_TYPE = "change_type";
}