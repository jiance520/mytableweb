package action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.impl.UserDataService;
import util.JdbcUtil;
import entity.UserData;
/**
 * @version 时间：2018年5月17日 下午11:32:52
 *
 */
public class QueryNote extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	/**
	 * Constructor of the object.
	 */
	public QueryNote() {
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
        String www = request.getParameter("www");
        String userName = request.getParameter("userName");
        HashMap<String,Object> map = JdbcUtil.queryOne("select * from userData where userName=? and www=?", userName,www);
        
//        使用mybatis查询，map
//        UserData userData = (new UserDataService()).selectByPrimaryKey(www, userName);
//        HashMap<String,Object> map = new HashMap();
//        map.put("WWW", userData.getWww());
//        map.put("USERNAME", userData.getUsername());
//        map.put("NOTE", userData.getNote());
//        map.put("CARDIMAGE", userData.getCardimage());
        
		logger.debug("查询备注成功");
        
        request.setAttribute("noteMap", map);
		RequestDispatcher rd = request.getRequestDispatcher("password.jsp");
		rd.forward(request,response);
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
