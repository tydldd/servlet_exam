package com.ru.project.login.servlet;

import com.google.gson.Gson;
import com.ru.project.counter.ServletCounter;
import com.ru.project.login.entry.User;
import com.ru.project.login.service.LoginSerInter;
import com.ru.project.login.service.imp.LoginSerImp;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Description:登陆操作
 * User: NanChengRu
 * Date: 13-11-17
 * Time: 下午12:58
 * JDK: 1.6
 * version: 1.0
 */
public class Login extends HttpServlet{
    private Logger log = Logger.getLogger(Login.class);
    private String message;
    LoginSerInter loginSer = new LoginSerImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        log.debug("用户名：" + userName + "密码：" + password);

        try {
            //得到user对象
            Object[] obj = loginSer.getUser(userName,password);
            if (obj != null){
                //修改总登陆人数和在线人数
                updateCounter(req);
                //将user对象放入session
                putUserToSession(req, obj[1].toString());
                //添加/修改/删除servletContext属性,触发ServletContextAttributeListener监听器
                addServletContextAttribute(req);
                modifyServletContextAttribute(req);
                removeServletContextAttribute(req);
                //处理cookie
                handleCookie(req, resp);
                //处理session
                handleSession(req);
                //处理request
                handleRequest(req);

                //将需要返回的数据转换成json格式  返回
                Gson gson = new Gson();
                String message = gson.toJson("success_" + req.getSession().getId());
                out.print(message);
            }else {
                Gson gson = new Gson();
                String message = gson.toJson("fail");
                out.print(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 处理request
     */
    private void handleRequest(HttpServletRequest request){
        request.setAttribute("requestAttribute", "request添加一个属性");
        request.setAttribute("requestAttribute", "修改request属性");
        request.removeAttribute("requestAttribute");
    }

    /**
     * 处理sesssion
     * @param request
     */
    private void handleSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        //添加session属性
        session.setAttribute("handleSession", "添加一个session属性");
        //修改session属性
        session.setAttribute("handleSession", "修改一个session属性");
        //删除session属性
        session.removeAttribute("handleSession");
    }

    /**
     * cookie操作
     * @param request
     */
    private void handleCookie(HttpServletRequest request,  HttpServletResponse response){
        //得到cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            System.out.println("***************************--开始");
            System.out.println("cookie路径：" + cookie.getPath());
            System.out.println("cookie最大生命周期：" + cookie.getMaxAge());
            System.out.println("cookie名：" + cookie.getName());
            System.out.println("cookie值：" + cookie.getValue());
            System.out.println("cookie描述：" + cookie.getComment());
            System.out.println("cookie：" + cookie.toString());
            System.out.println("***************************--结束");
        }
        //将JSESSIONID放入cookie
        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    /**
     * 将总登陆人数 + 1
     * @param request
     */
    private void putLogingCounter(HttpServletRequest request){
        ServletCounter counter = (ServletCounter) request.getSession().getServletContext().getAttribute("counter");
        counter.setPeopleNumber(counter.getPeopleNumber() + 1);
    }

    /**
     * 登陆成功，在线人数+1
     * @param request
     */
    private void putOnlineCounter(HttpServletRequest request){
        int onlineCounter = (Integer)request.getSession().getServletContext().getAttribute("onlineCounter");
        request.getSession().getServletContext().setAttribute("onlineCounter", onlineCounter + 1);
    }

    /**
     * 将sessionid放入servletcontext.同时更新总登陆人数和在线人数
     * @param request
     */
    private void updateCounter(HttpServletRequest request){
        String sessionId =  request.getSession().getId();
        List<String> list = (List<String>) request.getSession().getServletContext().getAttribute("sessionList");
        //假如是新session
        if (!list.contains(sessionId)){
            //将sessionid放入servletcontext属性
            list.add(sessionId);
            System.out.println("新增的session = " + sessionId);
            //修改servletcontext的总登陆人数属性
            putLogingCounter(request);
            //修改在线人数、
            putOnlineCounter(request);
        }
    }


    /**
     * 将user对象放入session
     * @param request
     * @param userName
     */
    private void putUserToSession(HttpServletRequest request, String userName){
        User user = new User();
        user.setUserName(userName);
        request.getSession().setAttribute("user", user);
        System.out.println("用户信息：" + ((User) request.getSession().getAttribute("user")).getUserName());
    }

    /**
     * 添加一个servletContext属性
     * @param request
     */
    private void addServletContextAttribute(HttpServletRequest request){
        ServletContext sc = request.getSession().getServletContext();
        sc.setAttribute("addContext", "添加了一个servletContext属性");
    }

    /**
     * 修改servletContext属性
     * @param request
     */
    private void modifyServletContextAttribute(HttpServletRequest request){
        ServletContext sc = request.getSession().getServletContext();
        sc.setAttribute("addContext", "修改servletContext属性addContext");
    }

    /**
     * 删除servletContext属性
     * @param request
     */
    private void removeServletContextAttribute(HttpServletRequest request){
        ServletContext sc = request.getSession().getServletContext();
        sc.removeAttribute("addContext");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果是post提交直接使用doGet方法
        this.doGet(req,resp);
    }
}
