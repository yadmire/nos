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

@ApiModel(value="com-weiyx-nos-model-SysUserDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_details")
public class SysUserDetails {
    @TableId(value = "user_id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "user_idn")
    @ApiModelProperty(value="")
    private String userIdn;

    @TableField(value = "user_idtype")
    @ApiModelProperty(value="")
    private String userIdtype;

    @TableField(value = "realname")
    @ApiModelProperty(value="")
    private String realname;

    @TableField(value = "email")
    @ApiModelProperty(value="")
    private String email;

    @TableField(value = "job")
    @ApiModelProperty(value="")
    private String job;

    @TableField(value = "birthday")
    @ApiModelProperty(value="")
    private Date birthday;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_IDN = "user_idn";

    public static final String COL_USER_IDTYPE = "user_idtype";

    public static final String COL_REALNAME = "realname";

    public static final String COL_EMAIL = "email";

    public static final String COL_JOB = "job";

    public static final String COL_BIRTHDAY = "birthday";
}