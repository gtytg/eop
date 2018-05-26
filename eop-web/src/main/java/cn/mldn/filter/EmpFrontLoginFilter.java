package cn.mldn.filter;

import cn.mldn.util.bean.ResourceUtil;
import cn.mldn.util.web.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmpFrontLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req ;
        HttpSession session = request.getSession() ;
        if (session.getAttribute("eid") == null) {	// 用户未登录
            HttpServletResponse response = (HttpServletResponse) resp ;
            CookieUtil cookieUtil = new CookieUtil(request,response) ;
            String eid = cookieUtil.loadEid() ;	// 读取保存的eid数据
            if (eid != null) {
                session.setAttribute("eid", eid);
                chain.doFilter(req, resp);
            } else {	// 跳转到登录页
                req.getRequestDispatcher("/login_pre.action").forward(req, resp);
            }
        } else {	// 用户已登录
            chain.doFilter(req, resp);
        }
    }
}
