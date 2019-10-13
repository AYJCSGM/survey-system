package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.entity.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class DoRegServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取浏览器提交的数据：账号，密码和确认密码
		String name=request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
				
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);			
		
		try {			
			//2 创建业务逻辑类的对象，调用业务方法处理
			UserService userService = new UserServiceImpl();
			userService.register(user);
			
			//3 TODO 重定向到登录页面，暂时显示结果
			//request.getRequestDispatcher("/WEB-INF/pages/success.jsp")
			//       .forward(request, response);
			response.sendRedirect(request.getContextPath()+"/login");
		} catch (ReTryException e) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/reg");
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
