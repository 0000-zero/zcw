package com.atzcw.zcw.controller;

import com.atzcw.zcw.bean.TMenu;
import com.atzcw.zcw.service.MenueService;
import com.atzcw.zcw.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-25 19:42
 */
@RequestMapping("/menu")
@Controller
public class MenueController {

    @Autowired
    MenueService menueService;

    //获取menu数据
    @ResponseBody
    @GetMapping("/getMenus")
    public R getMenus(){
        List<TMenu> pmenus = menueService.getPmenus();
        return R.ok().setData(pmenus);
    }

    //转发到菜单页面
    @GetMapping("/index")
    public String menuPage(){
        return "menus/menu";
    }

}
