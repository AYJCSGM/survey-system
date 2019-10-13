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
		// 1 ��ȡ������ύ�����ݣ�������⣬ѡ�����ͣ�����ѡ�������
		String sid = request.getParameter("id");
		String title = request.getParameter("title");
		int number = Integer.parseInt(request.getParameter("number"));
		//��һ��ҳ���ж����Ԫ��ͬ�������������Խ��ն��Ԫ���е�valueֵ
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
			// 2 ����ҵ���߼���Ķ��󣬵���ҵ�񷽷�����
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
			// 3�ض���ͶƱ��Ŀ�б�ҳ��			
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
