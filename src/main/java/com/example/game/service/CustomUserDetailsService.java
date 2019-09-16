package com.example.game.service;

import com.example.game.dao.SysUserDao;
import com.example.game.entity.SecurityUserDetails;
import com.example.game.entity.SysUser;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        SysUser user = sysUserDao.selectByEmail(loginName);
        return ofNullable(user).map(this::fetchUserDetailsFromDb)
                .orElseThrow(() -> new UsernameNotFoundException("can not find user"));
    }

    private UserDetails fetchUserDetailsFromDb(SysUser user) {
//        List<SysGroupMenu> sysGroupMenus = sysGroupMenuDao.selectMenuListByGroupId(user.getGroupId());
//        List<String> menuIds = sysUserMenuDao.selectUserMenuListByUserId(user.getId()).stream()
//                .filter(userMenu -> sysGroupMenus.stream()
//                        .map(SysGroupMenu::getMenuId)
//                        .collect(toList())
//                        .contains(userMenu.getMenuId()))
//                .map(SysUserMenu::getMenuId).collect(toList());
//        List<SimpleGrantedAuthority> authorities = sysMenuDao.selectSysMenuList()
//                .stream().filter(m -> menuIds.contains(m.getId()) && StringUtils.isNotEmpty(m.getPermission()))
//                .map(authority -> new SimpleGrantedAuthority(join("_", "ROLE", authority.getPermission())))
//                .collect(toList());
        return populateUserDetails(user, Lists.newArrayList());
    }

    private SecurityUserDetails populateUserDetails(final SysUser user,
                                                    final List<SimpleGrantedAuthority> authorities) {
        return new SecurityUserDetails(
                user.getOrgId(), user.getId(),
                user.getGroupId(), user.getUserType(),
                user.getLoginName(), user.getPassword(),
                user.getAvatar(), authorities,
                user.getEmail()
        );
    }
}