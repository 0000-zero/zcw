package com.atzcw.zcw.controller;


import com.atzcw.zcw.bean.TRole;
import com.atzcw.zcw.contain.PageContain;
import com.atzcw.zcw.service.RoleService;
import com.atzcw.zcw.util.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-24 21:03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    //修改角色权限
    @ResponseBody
    @GetMapping("/changeCharacterPermission")
    public R changeCharacterPermission(@RequestParam("roleId") Integer roleId,
                                       @RequestParam("permissionId") List<Integer> permissionId){

        //先将当前操作的角色的所有权限删除
        //在根据传过来的数据，更新角色权限
        roleService.changeCharacterPermission(roleId,permissionId);

        return R.ok();
    }

    //更新role信息
    @ResponseBody
    @PostMapping("/updateRole")
    public R updatRoleInfo(TRole tRole){
        System.out.println("添加："+tRole);
        roleService.updatRoleInfo(tRole);
        return R.ok();
    }

    //查询rolexinxi
    @ResponseBody
    @GetMapping("/getRoleInfo")
    public R getRoleInfoById(Integer id){
        TRole tRole = roleService.getRoleInfoById(id);
        return R.ok().setData(tRole);
    }

    //新增role
    @ResponseBody
    @PostMapping("/addRole")
    public R addRole(TRole tRole){

        roleService.addRole(tRole);
        return R.ok();
    }

    //删除role
    @PreAuthorize("hasAuthority('role:delete') or hasAnyRole('TL - 组长')")
    @ResponseBody
    @GetMapping("/delRole")
    public R delRole(Integer roleId){
        roleService.delRoleById(roleId);
        return R.ok();
    }

    //查询role所有数据并返回
    @ResponseBody()
    @GetMapping("/getRoles")
    public R getAllRoles(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false,defaultValue = "")String key){

        PageHelper.startPage(pageNum, PageContain.ROUER_PAGE_SIZE);
        List<TRole> tRoles =  roleService.getAllRoles(key);

        PageInfo<TRole> pageInfo = new PageInfo<>(tRoles, PageContain.ROUER_NAVIGATE_PAGES);


//        String s = JSON.toJSONString(pageInfo);
//        System.out.println("fastJson:"+s);


        return R.ok().setData(pageInfo);
    }

    //跳转到角色页面
    @GetMapping("/index")
    public String toRolePage(){
        return "roles/role";
    }

}
