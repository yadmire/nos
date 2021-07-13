/**
* @mbg.generated
* generator on Tue Jun 29 21:59:35 CST 2021
*/
package com.weiyx.nos.service.impl;

import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements com.weiyx.nos.service.SysUserService {
    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper=sysUserMapper;
    }

    /**
    * deleteByPrimaryKey
    * @param id id
    * @return int int
    */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    /**
    * insert
    * @param record record
    * @return int int
    */
    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    /**
    * insertSelective
    * @param record record
    * @return int int
    */
    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    /**
    * selectByPrimaryKey
    * @param id id
    * @return SysUser SysUser
    */
    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
    * updateByPrimaryKeySelective
    * @param record record
    * @return int int
    */
    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    /**
    * updateByPrimaryKey
    * @param record record
    * @return int int
    */
    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }
}