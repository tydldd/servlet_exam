<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-17
  Time: 下午4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base
href="<%=basePath%>">
    <title></title>
  </head>
  <body>
        <div style="background: blanchedalmond">
           <p style="color: #ff0000"> 跳转页面</p>
            <p>
                重定向和转发有一个重要的不同：<br>
                当使用转发时，servlet容器将使用一个内部的方法来调用目标页面，新的页面继续处理同一个请求
                ，而浏览器将不会知道这个过程。<br>
                request.getRequestDispatcher("jsp/jumpPage.jsp").forward(req,resp);//转发<br>
                与之相反，重定向方式的含义是第一个页面通知浏览器发送一个新的页面请求。<br>
                response.sendRedirect("jsp/jumpPage.jsp");//重定向<br>
            </p>
        </div>
  </body>
</html>