package com.zl.lqian.service.impl;

import com.zl.lqian.common.CommonConst;
import com.zl.lqian.common.MD5;
import com.zl.lqian.entity.Action;
import com.zl.lqian.entity.RoleAuth;
import com.zl.lqian.entity.User;
import com.zl.lqian.entity.UserRoles;
import com.zl.lqian.mapper.ActionMapper;
import com.zl.lqian.mapper.RoleAuthMapper;
import com.zl.lqian.mapper.UserMapper;
import com.zl.lqian.mapper.UserRolesMapper;
import com.zl.lqian.service.SsoAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AgentLoginServiceImpl implements SsoAuthHandler {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRolesMapper userRolesMapper;

    @Autowired
    RoleAuthMapper roleAuthMapper;

    @Autowired
    ActionMapper actionMapper;
    @Override
    @Transactional("serviceTransactionManager")  //这里是指定哪个数据源的事务管理
    public Authentication auth(Authentication authentication) {
        User user = null;
        // 用户名密码登录
        String userName = authentication.getPrincipal().toString();
        user = userMapper.findUserById(userName);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // Hash算法
        String  passwordHashValue = MD5.GetMD5Code(MD5.GetMD5Code(user.getHashKey() + "-" + authentication.getCredentials()) + CommonConst.NEW_HASH_SET_KEY);
        if (!passwordHashValue.equals(user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 登录成功，更新最后登录时间
        userMapper.updateLastLoginTime(user.getId());
        //这里设置权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //判断是不是admin
        if (user.getAuthType() == CommonConst.ADMIN_TYPE) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + "admin");
            authorities.add(grantedAuthority);
            return new UsernamePasswordAuthenticationToken(user.getUserId(), authentication.getCredentials(), authorities);
        }
        //取得该用户的权限
        List<String> userStringRoles = new ArrayList<String>();
        if (userStringRoles == null){
            List<UserRoles> userRoles = userRolesMapper.findByUserId(user.getId());
            userRoles.stream().forEach(currentUserRoles -> {
                userStringRoles.add(String.valueOf(currentUserRoles.getRole_id()));
            });
        }
        //取得这些角色所具有的权限
        Set<String> actionSet = new HashSet<>();
        if (userStringRoles != null){
            userStringRoles.stream().forEach(str->{
                List<Integer> actionIdList = new ArrayList<>();
                List<RoleAuth> roleAuthList = roleAuthMapper.findByRole_id(Integer.valueOf(str));
                if (roleAuthList != null && roleAuthList.size() > 0) {
                    for (RoleAuth currentRoleAuth : roleAuthList) {
                        actionIdList.add(Integer.valueOf(currentRoleAuth.getAction_id()));
                    }
                }
                if (actionIdList != null && actionIdList.size() > 0) {
                    List<Action> actionList = actionMapper.findByIdList(actionIdList);
                    for (Action act : actionList) {

                        if (!actionSet.contains(act.getAction_name())) {
                            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + act.getAction_name());
                            authorities.add(grantedAuthority);
                            actionSet.add(act.getAction_name());
                        }
                    }
                }
            });
        }
        return new UsernamePasswordAuthenticationToken(user.getUserId(), authentication.getCredentials(), authorities);
    }
}
