package com.example.game.dao;


import com.example.game.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(@Param("loginName") String loginName);

    SysUser selectByEmail(@Param("email") String email);

    List<SysUser> selectByEmailExcludeOneself(@Param("id") String id, @Param("email") String email);

    SysUser selectByAccountId(@Param("accountId") String accountId);
}