package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.entity.Record;
import vip.itellyou.entity.User;
import vip.itellyou.service.RecordService;
import vip.itellyou.service.impl.RecordServiceImpl;

public class DoVoteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ������ύ�����ݣ��˺ţ������ȷ������
		int subjectId=Integer.parseInt(request.getParameter("subjectId"));
		String[] ids = request.getParameterValues("options");
		
		User user= (User)request.getSession().getAttribute(User.SESSIONNAME);
		List<Record> records = new ArrayList<Record>();
		for(int i=0;i<ids.length;i++){
			Record record = new Record();
			record.getUser().setId(user.getId());
			record.getSubject().setId(subjectId);
			record.getOption().setId(Integer.parseInt(ids[i]));
			
			records.add(record);
		}
		
		try {			
			//2 ����ҵ���߼���Ķ��󣬵���ҵ�񷽷�����
			RecordService recordService=new RecordServiceImpl();
			recordService.vote(records);
			
			//3 TODO �ض��򵽵�¼ҳ�棬��ʱ��ʾ���			
			response.sendRedirect(request.getContextPath()+"/list");
		} catch (ReTryException e) {
			request.getSession().setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/m/vote");
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
