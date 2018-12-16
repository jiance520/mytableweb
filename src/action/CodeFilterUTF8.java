package action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 时间：2018年5月28日 下午8:18:58
 *
 */
public class CodeFilterUTF8 implements Filter {
	private String codestr;
	@Override
	public void destroy() {//摧毁
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		if(codestr!=null&&!codestr.equals("")){
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		}
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		codestr = arg0.getInitParameter("codestr");
	}
}
