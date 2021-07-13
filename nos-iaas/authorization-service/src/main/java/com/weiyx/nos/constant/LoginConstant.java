package com.weiyx.nos.constant;

public class LoginConstant {
    /**
     * 后台管理员登录
     */
    public static final String ADMIN_TYPE = "admin_type";
    /**
     * 用户/会员登录
     */
    public static final String MEMBER_TYPE = "member_type";
    /**
     * 管理员 ROLE_CODE 代码
     */
    public static final String ADMIN_ROLE_CODE = "ROLE_ADMIN";
    /**
     *
     */
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    /**
     * 根据用户名称查询用户信息的 sql 语句
     */
    public static final String QUERY_ADMIN_SQL = "SELECT `id`, `username`, `password`, `status` FROM sys_user WHERE username = ? ";

    public static final String QUERY_CUS_SQL = "SELECT `id`, `username`, `password`, `status` FROM sys_user WHERE phone = ? or email= ? ";

    public static final String QUERY_AUTHORITYBYUSER="SELECT a.authority_code FROM sys_authority a LEFT JOIN sys_role_authority ra ON a.id = ra.authority_id" +
            " LEFT JOIN sys_role r ON r.id=ra.role_id LEFT JOIN sys_user_role ur ON ur.role_code=r.id WHERE a.status=1 and ur.user_id=?";
}
