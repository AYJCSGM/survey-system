package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import vip.itellyou.core.filter.MyHttpServletRequest;

public class EncodingFilter implements Filter {
	private FilterConfig config;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		//....?id=1&name=张三  getParameter()
		HttpServletRequest req = (HttpServletRequest)request;
		if("GET".equals(req.getMethod().toUpperCase())){
			MyHttpServletRequest temp = new MyHttpServletRequest(req);
			temp.setEncoding(encoding);
			request=temp;
		}		
		
		//放行
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {		
		this.config = config;
	}

}
