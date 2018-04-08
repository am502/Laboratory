package ru.itis.kpfu.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by artur on 11.10.16.
 */
public class UserAuthFilter implements Filter{

    private List<String> urlList;

    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("allow-urls");
        urlList = new ArrayList<String>();
        Collections.addAll(urlList, initParameter.split(","));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        boolean allowedRequest = false;

        for (String s : urlList) {
            if(servletPath.startsWith(s)){
                allowedRequest = true;
            }
        }

        if(!allowedRequest){
            HttpSession session = req.getSession(false);

            if(session == null || session.getAttribute("session_uname") == null){
                 resp.sendRedirect("/login");
            }
        }

        chain.doFilter(req, resp);
    }

    public void destroy() {
        urlList = null;
    }
}
