package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.entity.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class DoLoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取浏览器提交的数据：用户名，密码和记住我
		String name=request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember=request.getParameter("remember");
		
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		
		HttpSession session = request.getSession();
		try{
			//2 创建业务逻辑类对象，调用业务方法完成登录
			UserService userService = new UserServiceImpl();
			user = userService.login(user);
			//3 衔接JSP
			//3.1 在session中记录用户对象
			session.setAttribute(User.SESSIONNAME, user);
			//3.2 检查是否勾选了记住我，是，则将用户编号记录到cookie中
			if(remember!=null){
				Cookie cookie=new Cookie(User.SESSIONNAME,user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			//3.3 重定向到投票项目列表页面
			response.sendRedirect(request.getContextPath()+"/list");
		}
		catch(ReTryException re){
			//数据回显			
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/login");
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
