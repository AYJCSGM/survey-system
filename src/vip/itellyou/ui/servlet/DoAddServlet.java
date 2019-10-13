package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.core.format.DateFormatter;
import vip.itellyou.entity.Option;
import vip.itellyou.entity.Subject;
import vip.itellyou.entity.User;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;

public class DoAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 获取浏览器提交的数据：主题标题，选择类型，各个选项的内容
		String sid = request.getParameter("id");
		String title = request.getParameter("title");
		int number = Integer.parseInt(request.getParameter("number"));
		//在一个页面中多个表单元素同名，服务器可以接收多个元素中的value值
		String[] os = request.getParameterValues("options");		
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        System.out.println(endTime);
		Subject subject = new Subject();		
		subject.setTitle(title);
		subject.setNumber(number);
		for(String ocontent:os){
			Option op = new Option();
			op.setContent(ocontent);
			
			subject.getOptions().add(op);
		}

		try {			
			// 2 创建业务逻辑类的对象，调用业务方法处理
			SubjectService subjectService=new SubjectServiceImpl();
			if(sid==null || sid.trim().length()==0){
				subjectService.add(subject,(User)request.getSession().getAttribute(User.SESSIONNAME));
			}
			else{
				subject.setId(Integer.parseInt(sid));
				subject.setStartTime(DateFormatter.toLong(startTime));
				subject.setEndTime(DateFormatter.toLong(endTime));
				subjectService.modify(subject,(User)request.getSession().getAttribute(User.SESSIONNAME));
			}
			// 3重定向到投票项目列表页面			
			response.sendRedirect(request.getContextPath() + "/list");
		} catch (ReTryException e) {
			HttpSession session = request.getSession();
			session.setAttribute("subject", subject);
			session.setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/m/add");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
