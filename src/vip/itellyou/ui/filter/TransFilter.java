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

import vip.itellyou.core.dao.DbHelper;
import vip.itellyou.entity.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class TransFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ����ת��
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			// ��������
			DbHelper.getConnection();
			DbHelper.beginTrans();
			
			// ������
			chain.doFilter(request, response);
			
			//�ύ����
			DbHelper.commitTrans();
			DbHelper.close();
		} catch (Exception e) {
			try {
				DbHelper.rollbackTrans();
				DbHelper.close();
				throw new RuntimeException(e);
			} catch (Exception e1) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
