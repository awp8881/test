package com.example.demo3.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(filterName = "TestFilter",urlPatterns = "/api")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("1110");
        System.out.println(servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println(servletResponse);

    }

    @Override
    public void destroy() {

    }
}
