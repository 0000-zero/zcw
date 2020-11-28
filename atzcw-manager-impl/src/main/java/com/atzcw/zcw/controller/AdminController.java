package com.atzcw.zcw.controller;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.bean.TMenu;
import com.atzcw.zcw.contain.PageContain;
import com.atzcw.zcw.service.AdminService;
import com.atzcw.zcw.service.MenueService;
import com.atzcw.zcw.to.AdminTo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zero
 * @create 2020-11-23 21:16
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    MenueService menueService;

    //保存修改
    @PostMapping("/updateAdmin")
    public String updateAdmin(TAdmin tAdmin,HttpSession session){

        System.out.println("admin:"+tAdmin);

        adminService.updateAdmin(tAdmin);
        String ref = (String) session.getAttribute("ref");
        session.removeAttribute("ref");

        return "redirect:"+ref;
    }

    //跳转到编辑页面
    @PreAuthorize("hasAnyRole('SE - 软件工程师00') or hasAnyAuthority('user:update')")
    @GetMapping("/edit.html")
    public String toEditPage(Integer id,
                             @RequestHeader("referer") String ref,
                             Model model,
                             HttpSession session){

        TAdmin tAdmin = adminService.getAdminById(id);
        model.addAttribute("admin",tAdmin);
        session.setAttribute("ref",ref);
        return "admins/edit";
    }

    //批量删除
    @PreAuthorize("hasAnyAuthority('user:delete') and hasAnyRole('R4')")
    @GetMapping("/batchDelAdmin")
    public String batchDeleteAdmin(@RequestParam("ids") List<Integer> ids,
                                   @RequestHeader("referer") String referer) {

        //通过admin的id 批量删除admin
        //删除成功返回删除前的页面
        System.out.println("ids:" + ids);
        adminService.batchDeleteAdmin(ids);

        return "redirect:" + referer;

    }


    //添加管理员
    @PreAuthorize("hasAnyRole('PM - 项目经理110')")
    @PostMapping("/addAdmin")
    public String addAdmin(@Validated AdminTo adminTo, HttpSession session, Model model, RedirectAttributes ra, BindingResult result) {

        System.out.println("新增admin");

        //数据校验
        if (result.hasErrors()) {
            Map<String, String> collect = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return "redirect:/admin/add.html";
        } else {
            //添加成功返回到显示管理员页面的最后一页
            try {
                adminService.saveAdmin(adminTo);
                Integer maxPage = (Integer) session.getAttribute("maxPage");
                return "redirect:/admin/index?pageNum=" + (maxPage + 1);
            } catch (Exception e) {
                //添加失败，回到添加页面并返回错误信息
//            model.addAttribute("errorMsg",e.getMessage());
                ra.addFlashAttribute("errorMsg", e.getMessage());
                return "redirect:/admin/add.html";
            }
        }


    }

    //跳转到添加页面
    @GetMapping("/add.html")
    public String toAddPage() {
        return "admins/add";
    }

    /*
       与批量删除合并

    //删除指定id的admin
    @GetMapping("/delAdmin")
    public String delAdminById(Integer adminId,
                               @RequestHeader("referer") String referer ){

        adminService.delAdminById(adminId);
        return "redirect:"+referer;
    }*/

    // admin/index 查询所有admin 信息
    @GetMapping("/index")
    public String getAllAdminsInfo(Model model,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "") String key,
                                   HttpSession session) {

        //在查询业务执行之前启用分页，参数1：要查询的页码 ， 参数2：每一页查询多少条记录
        PageHelper.startPage(pageNum, PageContain.PAGE_SIZE);

        List<TAdmin> tAdmins = adminService.getAllAdminsInfo(key);

        //获取更加详细的分页数据对象   参数1：分页数据集合 ， 参数2：分页导航栏的页码数量
        PageInfo<TAdmin> pageInfo = new PageInfo<>(tAdmins, PageContain.NAVIGATE_PAGES);

        int maxPage = pageInfo.getPages();
        session.setAttribute("maxPage", maxPage);


        System.out.println(pageInfo);


        model.addAttribute("pageInfo", pageInfo);
        return "admins/user";
    }

    //注销
//    @GetMapping("/loginout")
//    public String loginout(HttpSession session) {
//        session.removeAttribute("admin");
//        session.invalidate();
//        return "redirect:/index";
//    }

//    //登录
//    @PostMapping("/doLogin")
//    public String dologin(String loginacct, String userpswd, HttpSession session, Model model) {
//
//        TAdmin tAdmin = adminService.doLogin(loginacct, userpswd);
//
////        byte[] digest = SecureUtil.md5().digest(userpswd);
//
//        //登录成功 跳转到main.jsp 重定向 防止表单重复提交
//        if (tAdmin != null) {
//            session.setAttribute("admin", tAdmin);
//            return "redirect:/admin/main.html";
//        }
//
//        //登录失败 跳转到登录页面，并且返回错误信息
//        model.addAttribute("errorMsg", "账户或密码错误");
//        return "login";
//    }

    //登录成功跳转到main.jsp
    @RequestMapping("/main.html")
    public String toMainPage(HttpSession session) {

        //查询当前用户的菜单栏
        List<TMenu> tMenus = menueService.getPmenus();
        session.setAttribute("pmenus", tMenus);

        return "admins/main";
    }

}
