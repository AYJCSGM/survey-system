package vip.itellyou.core.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper{

	private String enc;
	public void setEncoding(String enc){
		this.enc=enc;
	}
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name){
		String result = null;
		try {
			byte[] bytes = super.getParameter(name).getBytes("iso-8859-1");
			result =  new String(bytes,enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
