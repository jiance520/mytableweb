package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.JdbcUtil;

/**
 * @version 时间：2018年5月28日 下午10:12:21
 *权限控制 
 */
public class SessionFilter implements Filter {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String uri = request.getRequestURI();
		
		String qs = request.getQueryString();
		//logger.debug("qs:"+qs);
		
		String path = uri.substring(uri.lastIndexOf("/")+1);
		ArrayList<String> list = new ArrayList<String>();
			list.add("password.jsp");
		if(list.contains(path)){
			Object obj = request.getSession().getAttribute("cardId");
			if(obj==null){
				response.sendRedirect("load.jsp");
			}
			else{
				arg2.doFilter(request, response);
			}
		}
		else{
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
