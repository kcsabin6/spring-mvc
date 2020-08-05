package com.modelRev.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AccessSecurityFilter implements Filter {

	private Set<String> allowedResources = new HashSet<String>();
	// HashSet used for search operation
	// Set doesn't allow duplicates

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		allowedResources.add("/login.jsp");
		allowedResources.add("/auth");
		allowedResources.add("/fact");
		allowedResources.add("/error.jsp");
		allowedResources.add("/signup.jsp");
		allowedResources.add("/forgetPassword.jsp");
		allowedResources.add("/signup");
		allowedResources.add("/ajaxSearchProfileServlet");
		allowedResources.add("/check.jsp");
		allowedResources.add("/display.jsp");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// HttpServletRequest is type-casted into request
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		// http://localhost:8080/vehicle/profiles
		String resourcePath = httpServletRequest.getServletPath();
		if (allowedResources.contains(resourcePath) || resourcePath.contains("/img") || resourcePath.contains("htm")) {
			chain.doFilter(request, response);
		}

		else {
			// avoid making new session
			HttpSession session = httpServletRequest.getSession(false);
			if (session != null && session.getAttribute("userData") != null) {
				// ohooo user is still logged in, let me go for more process
				chain.doFilter(request, response);
			} else {
				// user is not logged in now or session is expired
				HttpServletResponse httpServletResponse = (HttpServletResponse)response;
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error.jsp");
				// http://localhost:8080/vehicle is ContextPath
			}
		}
	}

	@Override
	public void destroy() {

	}
}
