<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-12-13
  Time: 下午3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>111</title>
</head>
<body>

    使用：resp.sendRedirect(resp.encodeRedirectURL("jsp/redirectPage.jsp"));<br/>
    页面重定向--jessionid =
        <%
            String sessionid = request.getSession().getId();
            out.println(sessionid);
        %>
</body>
</html>
