<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-16
  Time: 下午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
  </head>
  <body>
    <jsp:forward page="jsp/login.jsp"/>
  </body>
</html>