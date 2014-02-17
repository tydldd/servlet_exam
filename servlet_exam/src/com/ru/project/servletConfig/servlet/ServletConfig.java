package com.ru.project.servletConfig.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: ServletConfig可以得到在web.xml中配置的参数信息
 * User: NanChengRu
 * Date: 13-11-17
 * Time: 下午8:54
 * JDK: 1.6
 * version: 1.0
 */
public class ServletConfig extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过servlet的参数设置编码
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        //得到web.xml中配置的servlet参数--this.getInitParameter("encoding")= this.getServletConfig().getInitParameter()；
        out.println("web.xml param1:" + this.getInitParameter("encoding"));
        out.println("web.xml param2:" + this.getInitParameter("param1"));
        //得到全局参数
        out.println("global param:" + this.getInitParameter("globalparam"));
        out.println("servletConfig");//直接在新页面打印字符串
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
