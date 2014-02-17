<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-18
  Time: 下午4:37
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
    <div>
        <p>&nbsp;&nbsp;&nbsp;WEB<span style="font-family:宋体">容器在启动时，它会为每个</span><span style="font-family:Times New Roman">WEB</span><span style="font-family:宋体">应用程序都创建一个对应的</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象，它代表当前</span><span style="font-family:Times New Roman">web</span><span style="font-family:宋体">应用。</span></p>
        <p>&nbsp;&nbsp;&nbsp;ServletConfig<span style="font-family:宋体">对象中维护了</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象的引用，开发人员在编写</span><span style="font-family:Times New Roman">servlet</span><span style="font-family:宋体">时，可以通过</span><span style="font-family:Times New Roman">ServletConfig.getServletContext</span><span style="font-family:宋体">方法获得</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象。</span></p>
        <p>&nbsp;&nbsp;由于一个<span style="font-family:Times New Roman">WEB</span><span style="font-family:宋体">应用中的所有</span><span style="font-family:Times New Roman">Servlet</span><span style="font-family:宋体">共享同一个</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象，因此</span><span style="font-family:Times New Roman">Servlet</span><span style="font-family:宋体">对象之间可以通过</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象来实现通讯。</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象通常也被称之为</span><span style="font-family:Times New Roman">context</span><span style="font-family:宋体">域对象。</span></p>
    </div>
    <div>
        <p>1.多个<span style="font-family:Times New Roman">Servlet</span><span style="font-family:宋体">通过</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象实现数据共享。</span></p>
        <p>在<span style="font-family:Times New Roman">InitServlet</span><span style="font-family:宋体">的</span><span style="font-family:Times New Roman">Service</span><span style="font-family:宋体">方法中利用</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象存入需要共享的数据</span></p>
        <p>/*<span style="font-family:宋体">获取</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象</span><span style="font-family:Times New Roman">*/&nbsp;&nbsp;</span></p>
        <p>ServletContext&nbsp;context&nbsp;=&nbsp;this.getServletContext();&nbsp;&nbsp;&nbsp;</p>
        <p>//<span style="font-family:宋体">存入共享的数据&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
        <p>context.setAttribute(&quot;name&quot;,&nbsp;&quot;haha&quot;);&nbsp;</p>
        <p>在其它的<span style="font-family:Times New Roman">Servlet</span><span style="font-family:宋体">中利用</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象获取共享的数据&nbsp;&nbsp;&nbsp;</span></p>
        <p>/*<span style="font-family:宋体">获取</span><span style="font-family:Times New Roman">ServletContext</span><span style="font-family:宋体">对象</span><span style="font-family:Times New Roman">*/&nbsp;&nbsp;</span></p>
        <p>ServletContext&nbsp;context&nbsp;=&nbsp;this.getServletContext();&nbsp;&nbsp;&nbsp;</p>
        <p>//<span style="font-family:宋体">获取共享的数据&nbsp;&nbsp;&nbsp;</span></p>
        <p>String&nbsp;name&nbsp;=&nbsp;context.getAttribute(&quot;name&quot;);&nbsp;&nbsp;&nbsp;</p>
        <p>System.out.println(&quot;<span style="font-family:宋体">共享的内容&#20540;是</span><span style="font-family:Times New Roman">:&quot;&#43;name);&nbsp;&nbsp;</span></p>
    </div>
  </body>
</html>