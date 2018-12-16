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
 * @version 时间：2018年5月18日 下午1:21:55
 *
 */
public class CheckUser extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	/**
	 * Constructor of the object.
	 */
	public CheckUser() {
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
        String id1 = request.getParameter("cardId");
        map = JdbcUtil.queryOne("select * from carddata where cardid=? ", id1);
		request.setAttribute("id1", id1);
        if(map!=null){
    		logger.debug("用户已存在"+id1);
        	response.sendRedirect("register.jsp?flagid=1&id1="+id1);//flagid放入作用域后，可以使用${param.flagid}取值重写后map的值
        }
        else
        {
    		logger.debug("用户可注册");
        	response.sendRedirect("register.jsp?flagid=0&id1="+id1);
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
