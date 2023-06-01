package vnnews.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnnews.constant.SystemConstant;
import vnnews.model.UserModel;
import vnnews.utils.SessionUtil;

public class AuthorizationFilter implements Filter {
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		String url = request.getRequestURI();
//		if (url.startsWith("/admin")) {
//		if(url.startsWith(request.getContextPath()+"/admin")) {
		if (url.contains("/admin")) {
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
			if (model != null) {
				if (model.getRole().getCode().equals(SystemConstant.ADMIN)) {
					filterchain.doFilter(servletRequest, servletResponse);
				} else if (model.getRole().getCode().equals(SystemConstant.USER)) {
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		
		} else {
			filterchain.doFilter(servletRequest, servletResponse); // not use filter
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
