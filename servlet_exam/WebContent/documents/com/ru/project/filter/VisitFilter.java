package com.ru.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * 访问过滤器
 * Created by 成如 on 13-12-14.
 */
public class VisitFilter implements Filter{
    private FilterConfig fc = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
        System.out.println("******过滤器初始化！*******");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contentPath = request.getContextPath();
        System.out.println("访问路径：" + contentPath);

        String uri = request.getRequestURI();
        System.out.println("uri:" + uri);
        System.out.println("url:" + request.getRequestURL().toString());

        if(uri.contains(".jsp") || uri.contains("/*")){
            if(!uri.contains("login.jsp") && !uri.contains("verifyCodeImg.jsp") && !uri.contains("/login")){
                System.out.println("****需要被过滤");
                HttpSession session = request.getSession();
                if (session.isNew() || session.getAttribute("user") == null){
                    response.sendRedirect("jsp/login.jsp");
                }
            }
        }

        //沿过滤器链将请求传递到下一个过滤器,或发送到目的地
        System.out.println("***不需要被过滤");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("********销毁过滤器**********");
    }
}
