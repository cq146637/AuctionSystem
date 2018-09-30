package org.sczs.auction.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getRequestURI().startsWith("/erp/Login.html")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            if (session == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/erp/Login.html");
            } else {
                if (session.getAttribute("erpUser") == null) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/erp/Login.html");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
