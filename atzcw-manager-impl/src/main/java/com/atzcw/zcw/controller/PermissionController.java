package com.atzcw.zcw.controller;

import com.atzcw.zcw.bean.TPermission;
import com.atzcw.zcw.service.PermissionService;
import com.atzcw.zcw.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-26 15:08
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;



    //查询当前操作角色已有的权限
    @GetMapping("getCharacterHasPermission")
    public R getCharacterHasPermission(Integer roleId){
        List<Integer> integerList = permissionService.getCharacterHasPermission(roleId);
        return R.ok().setData(integerList);
    }

    //查询所有权
    @GetMapping("/getAllPermission")
    public R getAllPermission(){
        List<TPermission> tPermissions =  permissionService.getPermission();
        return R.ok().setData(tPermissions);
    }

}
