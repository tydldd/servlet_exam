package com.ru.project.listener;

import com.ru.project.counter.ServletCounter;
import com.ru.project.utils.ReadWriteFile;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * *这个监听器的所有行为都在com.ru.project.login.servlet.Login中完成
 *
 * ServletContextListener用于监听servlet环境的创建和销毁
 * ServletContextAttributeListener接口用于监听ServletContext环境域中增、删、改操作 注：当创建 ServletContext时，触发
 *ServletContextAttributeListener的attributeAdded()方法
 *
 * Created by 成如 on 13-12-12.
 */
public class ServletContextListeners implements ServletContextListener, ServletContextAttributeListener {

    Logger logger = Logger.getLogger(Listeners.class);

    /**
     * ServletContextListener接口，它能够监听ServletContext对象的生命周期，实际上就是监听Web应用的生命周期。

     *当Servlet容器启动或终止Web应用时，会触发ServletContextEvent事件，该事件由 ServletContextListener 来处理。
     * 在 ServletContextListener 接口中定义了处理ServletContextEvent事件的两个方法。

     *contextInitialized(ServletContextEvent sce)：当Servlet容器启动Web应用时调用该方法。
     * 在调用完该方法之后，容器再对Filter初始化，并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。

     *contextDestroyed(ServletContextEvent sce)：当Servlet容器终止Web应用时调用该方法。
     * 在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        int number = 0;
        System.out.println("servletContext环境创建，web启动");
        ServletContext sc = sce.getServletContext();
        //servletcontgext保存一个sessonid列表，这个列表在登陆成功且是一个新的session时，+1
        List<String> list = new ArrayList<String>();
        sc.setAttribute("sessionList", list);

        //总登陆计数器对象
        ServletCounter counter = new ServletCounter();
        String filePath = sc.getRealPath("/documents/counter");
        String num = ReadWriteFile.ReadFile(filePath);
        if (num != null && !num.equals("")){
            number = Integer.parseInt(num);
        }

        counter.setPeopleNumber(number);
        sc.setAttribute("counter",counter);

        //在线人数
        int onlineCounter = 0;
        sc.setAttribute("onlineCounter", onlineCounter);
        System.out.println("servletContext环境创建时，注册登录次数计数器。总登陆人数" + number +
            "当前在线人数：" + sc.getAttribute("onlineCounter"));

    }

    public void contextDestroyed(ServletContextEvent sce) {
        String filePath = sce.getServletContext().getRealPath("/documents/counter");

        //web关闭时，将登陆次数写入文件,
        ServletCounter counter = (ServletCounter) sce.getServletContext().getAttribute("counter");
        String number = counter.getPeopleNumber() + "";
        ReadWriteFile.writeFile(number, filePath);

        System.out.println("ServletContext被销毁，web关闭");
    }

    /**
     * ServletContextAttributeListener接口用于监听servletcontext环境域中增、删、改操作
     *
     */

    //当程序向application范围添加属性时触发该方法
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        String addContext = (String) servletContextAttributeEvent.getServletContext().getAttribute("addContext");
        System.out.println("添加的servletcontext属性，addContext = " + addContext);
    }

    //当程序从application范围删除属性时触发该方法
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        String addContext = (String) servletContextAttributeEvent.getServletContext().getAttribute("addContext");
        System.out.println("删除servletcontext属性，addContext = " + addContext);
    }

    //当application范围的属性被替换时触发该方法
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        String addContext = (String) servletContextAttributeEvent.getServletContext().getAttribute("addContext");
        System.out.println("修改servletcontext属性，addContext = " + addContext);
    }
}
