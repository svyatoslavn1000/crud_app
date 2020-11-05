package ru.geekbrains.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "HeaderFooterFilter", urlPatterns = "/*")
public class HeaderFooterFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().println("<h1>Header from filter</h1>");
        chain.doFilter(request, response);
        response.getWriter().println("<h1>Footer from filter</h1>");
    }

    @Override
    public void destroy() {

    }
}
