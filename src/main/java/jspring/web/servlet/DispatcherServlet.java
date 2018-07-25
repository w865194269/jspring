package jspring.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends FrameworkServlet {

	
	private static final long serialVersionUID = 1L;
	
	static{
		//注解扫描，Controller,Method等映射
		System.out.println("DispatcherSrevlet static ");
		
	}
	
	/**
	 * 根据request与response进行请求分发
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getRequestURI());
		response.getOutputStream().write(new String("Hello").getBytes());
		
	}

}
