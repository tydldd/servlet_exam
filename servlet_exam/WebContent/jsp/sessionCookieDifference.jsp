<%--
  Created by IntelliJ IDEA.
  User: 成如
  Date: 13-12-12
  Time: 下午5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session和cookie的区别</title>
</head>
<body>
    <div style="background: aquamarine">
        cookie 和session 的区别：<br/>

        1、cookie数据存放在客户的浏览器上，session数据放在服务器上。<br/>

        2、cookie不是很安全，别人可以分析存放在本地的COOKIE并进行COOKIE欺骗
        考虑到安全应当使用session。<br/>

        3、session会在一定时间内保存在服务器上。当访问增多，会比较占用你服务器的性能
        考虑到减轻服务器性能方面，应当使用COOKIE。<br/>

        4、单个cookie保存的数据不能超过4K，很多浏览器都限制一个站点最多保存20个cookie。<br/>

        5、所以个人建议：<br/>
        将登陆信息等重要信息存放为SESSION<br/>
        其他信息如果需要保留，可以放在COOKIE中<br/>
    </div>

    <div style="background: #ddeeff">
        <p style="color: #ff0000">
            一个浏览器对应一个session（浏览器访问服务器时，服务器创建一个session）。也对应一个会话级别的 cookie,这个cookie用于存储session的id。
            cookie名称：JSESSIONID,cookie的值是session的id。s<br>
            注：如果打开两个同一种浏览器对应两个不同的session和会话cookie。<br>
            使用浏览器访问服务器时，先查找JSESSIONID这个cookie，有则使用原来的，没有创建一个JSESSIONID cookie。<br>
            每一个链接都需要得到sessinid
        </p>
        <p>
            1、各个浏览器存cookie的地方不一样，浏览器直接的内核神马的都不同，不可能相互读取cookie<br/>
            如果是同一种浏览器，多个标签页共享的话，再生成cookie的时候添加cookie的有效期；否则cookie为会话cookie，<br/>
            这种客户端是不会把cookie存到硬盘上的，其他标签也无法获取到cookie<br/>
            <br/>
        </p>
        <p>
            2、如果不人为的创建cookie，则打开浏览器访问服务器时，会产生一个会话级别的cookie，及这个cookie会随着浏览器关闭而消失。<br/>
            这个cookie的名称是：JSESSIONID，值类似：8AC58DA4A168C1934515CAD50A4A57CA每次重启浏览器访问都会不同。<br/>
            这个cookie的作用是让服务器知道此浏览器对应的session是哪一个。同一个浏览器（打开两个IE的话是两个浏览器）的不同页签，
            会自动查找JSESSIONID这个cookie的sessionid，然后通过JSESSIONID，访问同一个session。<br>
            注：如果浏览器自动保存cookie的话，会保存在客户端文件中。这个cookie只有一个。<br>
        </p>
    </div>
    <div style="background: antiquewhite">
        <p style="color: #dd0000">
           1\ 如果想关闭浏览器后，重启同一浏览器，访问服务器时使用原来的session。
        </p>
        可以人为创建名称为JSESSIONID的cookie，这样关闭浏览器依然可以在访问服务器时浏览器自动查找JSESSIONID这个cookie的session id。<br>
        只要服务器端的session还未超时，就可以使用容一个session了。<br>
        <div>
            //将JSESSIONID放入cookie<br>
            Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());<br>
            cookie.setMaxAge(3600);<br>
            response.addCookie(cookie);<br>
        </div>

        <p style="color: #dd0000">
            2、 既然浏览器需要cookie才能得到sessionid，如果禁用cookie，那么新链接就得不到jessionid，也就得不到session。<br>
        </p>
        <div>
            (1)可以在链接后面直接加上jsseionid---";jsessionid=*******************"
            如： window.location.href = "jsp/main.jsp;jsessionid=815A9849C4848F5CC068085D26D452EF"
            （2）所谓URL重写就是当客户从一个页面重新连接到一个页面时，通过向这个新的URL添加参数，把session对象的id传过去，<br>
            这样能够保证session对象是完全相同的。
            <p style="color: #ff0000; font-size: 20">
                可以使用response对象调用encodeURL()或encodeRedirectURL()方法实现ＵＲＬ重写:<br>
                区别是encodeRedirectUrl()是在sendRedirect重定向的时候使用的，而encodeUrl在一般情况下使用，如href="".<br>
                如：a href="<%out.println(response.encodeURL("/encodeurl"));%>"<br>
                "resp.sendRedirect(resp.encodeRedirectURL("jsp/redirectPage.jsp"))"
            </p><br>
            <p >
                方法的执行：首先判断当前的Servlet是否执行了HttpSession对象的invalidate()方法(当前session是否失效，<br>
                失效后重新建立新的session)，如果已经执行返回参数URL。接下来判断客户端是否禁用了Cookie，<br>
                没有禁用直接返回参数URL，如果禁用，则在参数URL中附加session ID，返回编码后的URL
            </p>
            <a href="<%out.println(response.encodeURL("jsp/redirectPage.jsp"));%>">cookie禁用，重写url--测试encodeURL()</a><br>
            <a href="<%out.println(response.encodeURL("/encodeurl"));%>">cookie禁用，重写url--测试encodeRedirectURL()</a>
        </div>
    </div>

    <br>
    <br>

    <div>
        <a href="jessionAndCookie_jsessionid/jsessionid.htm" target="_blank">session和cookie的联系以及jssioncookie点我</a>
    </div>
</body>
</html>
