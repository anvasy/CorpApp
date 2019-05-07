package com.anvasy.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    private Logger logger = org.apache.log4j.Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { logger.info("AdminFilter initialized"); }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(true);

        if(session.getAttribute("role")== null) {
            res.sendRedirect("login");
        } else if(session.getAttribute("role").equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("AdminFilter destroyed");
    }
}