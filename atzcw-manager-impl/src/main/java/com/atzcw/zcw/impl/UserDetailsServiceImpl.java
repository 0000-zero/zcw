package com.atzcw.zcw.impl;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.bean.TAdminExample;
import com.atzcw.zcw.bean.TPermission;
import com.atzcw.zcw.bean.TRole;
import com.atzcw.zcw.mapper.TAdminMapper;
import com.atzcw.zcw.mapper.TPermissionMapper;
import com.atzcw.zcw.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zero
 * @create 2020-11-26 20:15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TAdminMapper tAdminMapper;

    @Autowired
    TRoleMapper tRoleMapper;

    @Autowired
    TPermissionMapper tPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.根据账号查询用户信息
        TAdminExample exa = new TAdminExample();
        exa.createCriteria().andLoginacctEqualTo(username);
        List<TAdmin> tAdmins = tAdminMapper.selectByExample(exa);

        //账号不存在
        if(CollectionUtils.isEmpty(tAdmins) || tAdmins.size() > 1) return null;

        TAdmin admin = tAdmins.get(0);
        //2.查询当前管理员拥有的所有角色
        /**
         * SELECT tr.*
         * 	FROM `t_admin_role` tar
         * LEFT JOIN `t_role` tr ON tr.`id` = tar.`roleid`
         * WHERE tar.`adminid`=1
         */
        List<TRole> tRoles = tRoleMapper.getRoleByAdminId(admin.getId());

        //获取当前管理员所拥有的所有权限
        /**
         * SELECT DISTINCT tp.*
         * 	FROM `t_admin_role` tar
         * LEFT JOIN `t_role_permission` trp ON trp.`roleid` = tar.`roleid`
         * LEFT JOIN `t_permission` tp ON tp.`id` = trp.`permissionid`
         * WHERE tar.`adminid` = 1
         */
        List<TPermission> tPermissions = tPermissionMapper.getPermissionByAdminId(admin.getId());

        //3.将用户信息和角色权限信息封装为主体对象返回

        List<GrantedAuthority> authorities = new ArrayList<>();
        //TODO 权限
        //遍历角色集合，将角色名称拼接前缀ROLE_ 存到权限集合中
        if(tRoles != null && tRoles.size() > 0){
            for (TRole role : tRoles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }
        }

        //TODO 角色
        //遍历权限集合，将权限名称存到权限集合中
        if(!CollectionUtils.isEmpty(tPermissions)){
            for (TPermission permission : tPermissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }




        /**
         * public User(String username, String password,
         * 			Collection<? extends GrantedAuthority> authorities) {
         *
         * 	AuthorityUtils.createAuthorityList()
         */
        User user = new User(admin.getLoginacct(), admin.getUserpswd(), authorities);


        System.out.println("用户的权限信息："+user);
        this.user = user;
        return user;
    }

    public static User user;

}
