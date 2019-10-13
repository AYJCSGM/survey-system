package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.entity.Subject;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;

public class VoteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1 
			int id = Integer.parseInt(request.getParameter("id"));
			//2 
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject= subjectService.getSubject(id);
			//×ª·¢µ½reg.jsp
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/pages/vote.jsp")
			       .forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
