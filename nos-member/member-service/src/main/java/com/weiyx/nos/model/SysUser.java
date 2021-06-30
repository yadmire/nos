package com.weiyx.nos.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.weiyx.nos.model.SysUser")
@Data
public class SysUser {

    @ApiModelProperty(value="主键")
    private Integer id;


    @ApiModelProperty(value="用户名")
    private String username;


    @ApiModelProperty(value="电话")
    private String phone;


    @ApiModelProperty(value="密码")
    private String password;


    @ApiModelProperty(value="省")
    private String province;


    @ApiModelProperty(value="市")
    private String city;


    @ApiModelProperty(value="创建人")
    private String createby;


    @ApiModelProperty(value="创建时间")
    private Date createtime;


    @ApiModelProperty(value="修改人")
    private String updateby;


    @ApiModelProperty(value="修改时间")
    private Date updatetime;
}