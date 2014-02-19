package com.ru.project.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description:加载log4j
 * User: NanChengRu
 * Date: 13-11-16
 * Time: 下午11:41
 * JDK: 1.6
 * version: 1.0
 */
public class Log4jInitMethod implements Servlet{
    private Logger log = Logger.getLogger(Log4jInitMethod.class);

    /**
     * 初始化操作:加载log4j配置
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String ROOTPATH = Log4jInitMethod.class.getResource("/resource/log4j/log4j.properties").getPath();
        /**
         *读取配置文件的方法
         *BasicConfigurator.configure ()： 自动快速地使用缺省Log4j环境。
         *PropertyConfigurator.configure ( String configFilename) ：读取使用Java
         *的特性文件编写的配置文件。
         *DOMConfigurator.configure ( String filename ) ：读取XML形式的配置文件。
         */
        PropertyConfigurator.configure(ROOTPATH);
        log.info("log4j被初始化-log");
        System.out.println("log4j被初始化-system");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        log.info("log4j的servlet结束（desrtoy）");
    }
}
