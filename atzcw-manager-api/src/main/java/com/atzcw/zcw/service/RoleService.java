package com.atzcw.zcw.service;

import com.atzcw.zcw.bean.TRole;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-24 21:11
 */
public interface RoleService {
    List<TRole> getAllRoles(String key);

    void delRoleById(Integer roleId);

    void addRole(TRole tRole);

    TRole getRoleInfoById(Integer id);

    void updatRoleInfo(TRole tRole);

    void changeCharacterPermission(Integer roleId, List<Integer> permissionId);
}
