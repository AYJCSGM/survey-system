package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;

public class ModifyServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			SubjectService subjectService = new SubjectServiceImpl();
			List subjects = subjectService.getSubjects();
			
			request.setAttribute("subjects", subjects);
			request.getRequestDispatcher("/WEB-INF/pages/modify.jsp")
			       .forward(request, response);
		} catch (Exception e) {			
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
