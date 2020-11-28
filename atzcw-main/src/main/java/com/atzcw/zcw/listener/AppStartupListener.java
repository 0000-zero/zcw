package com.atzcw.zcw.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author zero
 * @create 2020-11-23 21:02
 */
public class AppStartupListener implements ServletContextListener {

    //全局上下文对象创建后立即调用
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //向域中设置全局上下文对象
        servletContextEvent.getServletContext().setAttribute("PATH",servletContextEvent.getServletContext().getContextPath());

    }

    //全局上下文对象销毁前调用：收尾操作
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
