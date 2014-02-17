<%@ page import="com.ru.project.login.entry.User" %>
<%@ page import="com.ru.project.counter.ServletCounter" %>
<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-17
  Time: 下午2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    User user = (User) request.getSession().getAttribute("user");
    ServletCounter sc = (ServletCounter) request.getSession().getServletContext().getAttribute("counter");
    String userName = user.getUserName();
    int counter = sc.getPeopleNumber();
%>
<%
    String url = response.encodeURL("jsp/sessionCookieDifference.jsp");
%>
<%
    ServletContext servletContext = request.getSession().getServletContext();
    int onlineCounter =  Integer.parseInt(request.getSession().getServletContext().getAttribute("onlineCounter").toString());
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <script type="text/javascript" src="js/main.js"></script>
  </head>
  <body>
        <div>主页面</div>
    <div>
        欢迎：<%=userName %>,您是第<%=counter%>个登陆的用户-----当前在线人数 <%=onlineCounter%> 人-------
        <p><input type="button" value="安全退出" onclick="logout()"></p>
    </div>
    <div><a href="/documents/解读Servlet生命周期.doc">Sevlet生命周期</a></div>
    <div><a href="/jumpPage">servlet跳转页面-重定向和转发</a></div>
    <div><a href="jsp/difference.jsp">getAttribute()和getParameter()的区别</a></div>
    <div><a href="/servletConfig">ServletConfig对象</a> --注：ServletConfig可以得到在web.xmlz中配置的参数信息</div>
    <div><a href="jsp/servletcontext.jsp">ServletContext对象可以让所有servlet访问</a></div>
    <div><a href="<%=url%>">Session和Cookie的区别</a></div>
  </body>
</html>