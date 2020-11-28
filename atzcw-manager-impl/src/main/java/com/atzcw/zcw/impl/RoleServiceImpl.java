package com.atzcw.zcw.impl;

import com.atzcw.zcw.bean.TRole;
import com.atzcw.zcw.bean.TRoleExample;
import com.atzcw.zcw.bean.TRolePermissionExample;
import com.atzcw.zcw.mapper.TRoleMapper;
import com.atzcw.zcw.mapper.TRolePermissionMapper;
import com.atzcw.zcw.service.RoleService;
import com.atzcw.zcw.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-24 21:11
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    TRoleMapper tRoleMapper;

    @Autowired
    TRolePermissionMapper tRolePermissionMapper;

    @Override
    public List<TRole> getAllRoles(String key) {

        TRoleExample exa = new TRoleExample();

        if(!StringUtil.isEmpty(key)){
            exa.createCriteria().andNameLike("%"+key+"%");
        }

        return tRoleMapper.selectByExample(exa);
    }

    @Override
    public void delRoleById(Integer roleId) {
        tRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void addRole(TRole tRole) {
        tRoleMapper.insertSelective(tRole);
    }

    @Override
    public TRole getRoleInfoById(Integer id) {

        return tRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updatRoleInfo(TRole tRole) {
        tRoleMapper.updateByPrimaryKeySelective(tRole);
    }

    @Override
    public void changeCharacterPermission(Integer roleId, List<Integer> permissionId) {

        //删除
        TRolePermissionExample exa = new TRolePermissionExample();
        exa.createCriteria().andRoleidEqualTo(roleId);
        tRolePermissionMapper.deleteByExample(exa);

        //添加
        tRolePermissionMapper.changeCharacterPermission(roleId,permissionId);

    }
}
