package com.joe.commons.shiro;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/4/1
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: shiro realm
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.joe.entity.AdminAuth;
import com.joe.entity.AdminRole;
import com.joe.entity.AdminUser;
import com.joe.service.system.AdminAuthService;
import com.joe.service.system.AdminRoleService;
import com.joe.service.system.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminAuthService adminAuthService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        String userAccount = (String) principalCollection.getPrimaryPrincipal();

        List<String> permissionList = getUserPermission(userAccount);

        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissionList);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 把token转换成User对象
        AdminUser adminUserToken = getAdminUserToken(authenticationToken);

        // 如果查询到的对象为空，返回 null
        if (null == adminUserToken) {
            return null;
        }

        // 返回
        return new SimpleAuthenticationInfo(adminUserToken.getUserAccount(), adminUserToken.getUserPassword(), this.getName());
    }

    /**
     * 根据token中的帐号查询出用户密码
     *
     * @param authenticationToken token
     * @return 返回查询结果
     */
    private AdminUser getAdminUserToken(AuthenticationToken authenticationToken) {
        // 根据帐号查询出密码
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account", authenticationToken.getPrincipal());
        return adminUserService.getOne(adminUserQueryWrapper);
    }

    /**
     * 根据角色编号查询权限
     *
     * @param userAccount 用户账户
     * @return 返回权限结果
     */
    private List<String> getUserPermission(String userAccount) {
        // 查询用户的账户信息
        QueryWrapper<AdminUser> adminUserQueryWrapper  =new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account", userAccount);
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        List<AdminAuth> adminAuthList;
        if (StringUtils.equals("ARNO20191212092022P78908", adminUser.getRoleNo())) {
            adminAuthList = adminAuthService.list();
        } else {
            QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("role_no", adminUser.getRoleNo());
            AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

            String permissionString = adminRole.getRoleAuth();

            String[] permissionArray = permissionString.split(",");
            QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
            adminAuthQueryWrapper.in("auth_no", permissionArray);

            adminAuthList = adminAuthService.list(adminAuthQueryWrapper);
        }

        List<String> permissionList = Lists.newArrayList();
        for (AdminAuth adminAuth : adminAuthList) {
            permissionList.add(adminAuth.getAuthPermission());
        }

        return permissionList;
    }
}
