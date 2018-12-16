package action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import util.JdbcUtil;

/**
 * @version 时间：2018年5月18日 下午1:25:48
 *
 */
public class Register extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	/**
	 * Constructor of the object.
	 */
	public Register() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> map = null;
	    String cardId = request.getParameter("cardId");
	    String password = request.getParameter("password");
	    map = JdbcUtil.queryOne("select * from carddata where cardid=? ", cardId);
	    
	    String value = request.getParameter("veryCode");
	    Object  vobj = request.getSession().getAttribute("rand");
	    if(value.equalsIgnoreCase((String)vobj)){
	    	logger.debug("验证码正确");
		    if(map!=null){
				logger.debug("注册失败");
		    	response.sendRedirect("register.jsp?flagid=2");
		    }
		    else
		    {	
		    	int num = JdbcUtil.executeUpdate("insert into carddata(cardid,password) values(?,?)", cardId,password);
				logger.debug("注册成功");
				response.sendRedirect("load.jsp");
		    }
	    }
	    else{
			logger.debug("验证码错误,注册失败");
			response.sendRedirect("register.jsp?flagid=2");
	    }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
