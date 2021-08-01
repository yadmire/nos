package com.weiyx.nos.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value="com-weiyx-nos-vo-SysUserVo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 账号状态：1正常，0 锁定
     */
    @ApiModelProperty(value="账号状态：1正常，0 未激活，-1 冻结")
    private Integer status;


    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;


    @ApiModelProperty(value="修改人")
    private String updateBy;


    @ApiModelProperty(value="修改时间")
    private Date updateTime;
}