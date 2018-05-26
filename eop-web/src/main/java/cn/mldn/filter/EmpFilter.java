package cn.mldn.filter;


import cn.mldn.util.web.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("eid") == null) {
            HttpServletResponse response = (HttpServletResponse) resp;
            CookieUtil cookieUtil = new CookieUtil(request, response);
            String eid = cookieUtil.loadEid();    // 读取保存的mid数据
            if (eid != null) {
                session.setAttribute("eid", eid);
            }
            chain.doFilter(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
