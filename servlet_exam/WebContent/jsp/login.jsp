<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-17
  Time: 上午11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
  <head>
    <base href="<%=basePath%>">
    <title>用户登陆</title>
    <script type="text/javascript" language="javascript" src="js/jQuery1.9.1-min.js"></script>
    <script type="text/javascript" language="javascript" src="js/login.js"></script>
  </head>
  <body>
      <div class="user">SERVLET用户登录</div>
      <div>
          <p>姓　名：<input id="userName" type="text" value="ru" alt="请输入姓名"/></p>
          <p>密　码：<input id="password" type="password" value="123456" alt="请输入密码"></p>
          <p>
              <span class="verifyCode">验证码：<input id="verifyCode" type="text"></span>
              <img id="randImage" src="jsp/verifyCodeImg.jsp?Math.random();"/>
          </p>
          <input type="button" value="登录" onclick="logCheck()">
      </div>
  </body>
</html>