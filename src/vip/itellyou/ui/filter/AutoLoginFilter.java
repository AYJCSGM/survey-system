package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.entity.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 向下转型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 检查是否已经登录，如果已经登录则不需要自动登录功能
		HttpSession session = req.getSession();
		if (session.getAttribute(User.SESSIONNAME) == null) {
			// 到cookie中查找用户编号
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (User.SESSIONNAME.equals(cookie.getName())) {
					// 获取用户id
					int id = Integer.parseInt(cookie.getValue());
					// 创建业务逻辑对象，调用方法获取用户对象
					try {
						UserService userService = new UserServiceImpl();
						User user = userService.getUser(id);
						userService.online(user, true);
						// 写入session，模拟登录成功
						session.setAttribute(User.SESSIONNAME, user);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}			
		}
		// 向后放行
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
