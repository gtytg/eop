package cn.mldn.util.web.filter;

import javax.servlet.*;
import java.io.IOException;
public class EncodingFilter implements Filter {
	private String charset ;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.charset = filterConfig.getInitParameter("charset") ;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(this.charset);
		resp.setCharacterEncoding(this.charset);
		chain.doFilter(req, resp);
	}

}
