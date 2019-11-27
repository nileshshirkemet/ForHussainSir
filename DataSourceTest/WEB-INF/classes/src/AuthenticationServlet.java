package sales.web.app;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@javax.servlet.annotation.WebServlet({"/login", "/logout"})
public class AuthenticationServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String customerId = request.getParameter("customerId");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		CustomerBean bean = (CustomerBean)session.getAttribute("customer");
		if(bean == null){
			bean = new CustomerBean();
			session.setAttribute("customer", bean);
		}
		try{
			if(bean.authenticate(customerId, password))
				response.sendRedirect("protected/order.jspx");
			else
				response.sendRedirect("customer.jspx?failed=true");
		}catch(Exception e){
			response.sendError(500, e.getMessage());
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/product.jspx");
		rd.forward(request, response);
	}
}

