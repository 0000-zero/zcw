package com.atzcw.zcw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zero
 * @create 2020-11-26 18:41
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    //授权方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO 1.对请求进行授权
        //permitAll()   授权所有人都可以访问
        //匹配的地址都是浏览器访问资源的地址
        http.authorizeRequests()
                .antMatchers("/", "/index", "/static/**", "/login.html").permitAll()
                ///.hasAnyRole() 授权资源拥有指定任意角色可以访问
                //.hasAnyAuthority() 授权资源拥有指定任意权限可以访问
                //.antMatchers("/admin/delAdmin").hasAnyAuthority("ROLE_SA - 架构师","ROLE_SE - 软件工程师","user:delete")
                // 底层会调用hasAnyAuthority()并在role前面拼接ROLE_前缀传入
                .anyRequest().authenticated();//其他任意的请求都需要认证

        //TODO 2.配置浏览器提交主体创建的登录表单【配置浏览器如何提交登录请求，接收到请求后再提交给主体创建的方法处理】
        http.formLogin()
                .loginPage("/login.html") //指定登录页面
                .loginProcessingUrl("/dologin") //浏览器提交由springsecurity处理登录url地址
                .usernameParameter("loginacct") //浏览器提交登录请求的账号 key 密码 key
                .passwordParameter("userpswd")
                .defaultSuccessUrl("/admin/main.html"); //登录成功后重定向的地址
        //提交登录请求的表单是post方式提交的，为了避免CSRF攻击[跨站点攻击]，springsecurity默认开启了csrf验证
        // 需要验证请求参数中的_csrf  token，如果没有则报错，一般禁用csrf功能
        http.csrf().disable();

        //TODO 3.异常处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                //判断请求是同步请求还是异步请求
                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                    //异步请求
                    response.getWriter().write("403");
                }else {
                    //同步请求
                    String message = e.getMessage();
                    request.setAttribute("errorMsg",message);
                    //转发到错误页面
                    request.getRequestDispatcher("/WEB-INF/pages/error/4xx.jsp").forward(request,response);
                }
            }
        });

        //TODO 4.注销
        http.logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/index");

    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    //创建主体
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //浏览器如何提交请求给此方法处理
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        //userDetailsService()设置自定义登录业务类对象的方法
    }
}
