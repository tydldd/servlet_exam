package com.ru.project.listener;
/**
 * Created by 成如 on 13-12-12.
 *这个监听器的所有行为都在com.ru.project.login.servlet.Login中完成
 *
 * HttpSessionListener用于监听session的创建和销毁
 * HttpSessionAttributeListener用于监听session中的增删改操作
 */

import com.ru.project.counter.ServletCounter;
import com.ru.project.utils.ReadWriteFile;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class Listeners implements HttpSessionListener, HttpSessionAttributeListener {

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }

}
