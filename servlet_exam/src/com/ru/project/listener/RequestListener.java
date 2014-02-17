package com.ru.project.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by 成如 on 13-12-14.
 */
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener{

    /**
     * ServletRequestListener用于监听request的创建与销毁
     * @param servletRequestEvent
     */

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("一个新的request请求");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("request请求结束");
    }

    /**
     *ServletRequestAttributeListener 用于监听request属性的增删改
     * @param servletRequestAttributeEvent
     */

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("request增加属性，requestAttribute = " +
                servletRequestAttributeEvent.getServletRequest().getAttribute("requestAttribute"));
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("request删除属性，requestAttribute = " +
                servletRequestAttributeEvent.getServletRequest().getAttribute("requestAttribute"));
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("request修改属性，requestAttribute = " +
                servletRequestAttributeEvent.getServletRequest().getAttribute("requestAttribute"));
    }
}
