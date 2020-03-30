package com.panshi.springbootweb.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  11:45
 * @Version
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter process...");
        filterChain.doFilter(request,response);
    }
}
