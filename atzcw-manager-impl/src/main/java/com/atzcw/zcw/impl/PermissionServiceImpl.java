package com.atzcw.zcw.impl;

import com.atzcw.zcw.bean.TPermission;
import com.atzcw.zcw.bean.TRolePermission;
import com.atzcw.zcw.bean.TRolePermissionExample;
import com.atzcw.zcw.mapper.TPermissionMapper;
import com.atzcw.zcw.mapper.TRolePermissionMapper;
import com.atzcw.zcw.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zero
 * @create 2020-11-26 15:11
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    TPermissionMapper tPermissionMapper;

    @Autowired
    TRolePermissionMapper tRolePermissionMapper;

    @Override
    public List<TPermission> getPermission() {
        return tPermissionMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getCharacterHasPermission(Integer roleId) {

        TRolePermissionExample exa = new TRolePermissionExample();
        exa.createCriteria().andRoleidEqualTo(roleId);
        List<TRolePermission> tRolePermissions = tRolePermissionMapper.selectByExample(exa);

        if(CollectionUtils.isEmpty(tRolePermissions)) return null;

        List<Integer> collect = tRolePermissions.stream().map(TRolePermission::getPermissionid).collect(Collectors.toList());
        return collect;
    }
}
