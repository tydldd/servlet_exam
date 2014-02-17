package com.ru.project.pagejump.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: servlet实现页面跳转
 * User: NanChengRu
 * Date: 13-11-17
 * Time: 下午4:34
 * JDK: 1.6
 * version: 1.0
 */
public class JumpPage extends HttpServlet{
    /**
     *  重定向和转发有一个重要的不同：当使用转发时，JSP容器将使用一个内部的方法来调用目标页面，新的页面继续处理同一个请求
     *  而浏览器将不会知道这个过程。 与之相反，重定向方式的含义是第一个页面通知浏览器发送一个新的页面请求。
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("jsp/jumpPage.jsp");//重定向
        //req.getRequestDispatcher("jsp/jumpPage.jsp").forward(req,resp);//转发
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
