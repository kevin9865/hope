package com.hope.systemManager.frameManager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private String loginPage;
	
	private String reLoadUrl;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		String url=request.getRequestURI()+"?"+request.getQueryString();
		String loginFlag=(String) request.getParameter("login");

		if(session.getAttribute("UserContext") == null&&loginFlag==null){
			response.sendRedirect(request.getContextPath()+loginPage);
		} else if (session.getAttribute("UserContext") == null&&loginFlag!=null) {
			//session.setAttribute("approveContext", url);
			reLoadUrl=url;
			response.sendRedirect(request.getContextPath()+loginPage);
		} else if (session.getAttribute("UserContext") != null&&reLoadUrl!=null) {
//			String approveContextUrl=(String) session.getAttribute("approveContext");
//			session.removeAttribute("approveContext");
			String approveContextUrl=reLoadUrl;
			reLoadUrl=null;
			response.sendRedirect(approveContextUrl);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
//		if (session.getAttribute("UserContext") == null || session.isNew()) {
//			response.sendRedirect(loginPage);
//		} else {
//			filterChain.doFilter(servletRequest, servletResponse);
//		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		loginPage = config.getInitParameter("loginPage");
		
	}

}
