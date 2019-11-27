package sales.web.app;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@javax.servlet.annotation.WebFilter("/protected/*")
public class AuthorizationFilter implements Filter{

	public void init(FilterConfig config){}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain next) throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
		CustomerBean bean = (CustomerBean)session.getAttribute("customer");
		if(bean == null || bean.getId() == null)
			response.sendRedirect("../customer.jspx");
		else{
			response.setHeader("Cache-Control", "no-cache,no-store");
			next.doFilter(req, res);
		}
			
	}

	public void destroy(){}
}

