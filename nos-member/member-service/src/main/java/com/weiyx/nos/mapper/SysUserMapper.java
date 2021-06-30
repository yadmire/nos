package com.weiyx.nos.mapper;

import com.weiyx.nos.model.SysUser;

public interface SysUserMapper {
    /**
    * deleteByPrimaryKey
    * @param id id
    * @return int int
    */
    int deleteByPrimaryKey(Integer id);

    /**
    * insert
    * @param record record
    * @return int int
    */
    int insert(SysUser record);

    /**
    * insertSelective
    * @param record record
    * @return int int
    */
    int insertSelective(SysUser record);

    /**
    * selectByPrimaryKey
    * @param id id
    * @return SysUser SysUser
    */
    SysUser selectByPrimaryKey(Integer id);

    /**
    * updateByPrimaryKeySelective
    * @param record record
    * @return int int
    */
    int updateByPrimaryKeySelective(SysUser record);

    /**
    * updateByPrimaryKey
    * @param record record
    * @return int int
    */
    int updateByPrimaryKey(SysUser record);
}