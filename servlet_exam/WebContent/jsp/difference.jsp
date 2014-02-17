<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-11-17
  Time: 下午5:06
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


            <div>
                1.ServletRequest中的public Object getAttribute( String name )

                <p>getAttribute( String name )可以得到由setAttribute()设置的参数值，就相当于是使用getAttribute()得到一
                    个自己定义的参数，而不是从客户端得到的参数。
                </p>
            </div>
            <div>
                2.ServletRequest中的public String getParameter( String name )
                <p>
                    它用来获取客户端通过get或post方法等传递过来的值，是从客户端传递过来的，一般指的是客户端提
                    交的表单组件的值
                </p>
            </div>
            <div>
                3.ServletConfig中的public void String getInitParameter( String name )
                <p>
                    它用来获取Servlet的配置文件的初始化参数的信息，也就是我们自己的web应用程序根目录下的WEB-
                    INF目录下的web.xml文件中的初始化参数信息。
                </p>
            </div>
        </div>
  </body>
</html>