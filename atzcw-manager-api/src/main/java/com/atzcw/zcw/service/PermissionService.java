package com.atzcw.zcw.service;

import com.atzcw.zcw.bean.TPermission;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-26 15:11
 */
public interface PermissionService {
    List<TPermission> getPermission();

    List<Integer> getCharacterHasPermission(Integer roleId);


}
