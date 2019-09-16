package com.example.game.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUser {

    private String id;

    private String loginName;

    private String groupId;

    private String accountId;

    private String password;

    private String name;

    private String email;

    private String mobile;

    private String orgId;

    private Integer no;

    private String avatar;

    private String userType;

    private String loginIp;

    private LocalDateTime loginDate;

    private String state;

    private String remark;

    private String createBy;

    private LocalDateTime createDate;

    private String updateBy;

    private LocalDateTime updateDate;

    private String delFlag;

}
