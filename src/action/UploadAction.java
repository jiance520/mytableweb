package action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import util.JdbcUtil;

/**
 * @version 时间：2018年5月31日 下午3:25:55
 *
 */
public class UploadAction extends HttpServlet {
	private static Logger logger = Logger.getLogger(UploadAction.class.getName());
	/**
	 * Constructor of the object.
	 */
	public UploadAction() {
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
		doPost(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String www = request.getParameter("www");
		String userName = request.getParameter("userName");
		String savepath = request.getServletContext().getRealPath("/images");
		System.out.println(savepath);
		boolean flag = ServletFileUpload.isMultipartContent(request);
		if(flag){
			String title = "";
			//新建上传对象
			DiskFileItemFactory df = new DiskFileItemFactory();//解析工厂
			ServletFileUpload sfu = new ServletFileUpload(df);//
			sfu.setFileSizeMax(500*1024*1024);
			try {
				//取数据
				List<FileItem> flist = sfu.parseRequest(request);//解析请求的内容，返回上传内容的集合，一个name请求数据就是一个FileItem
				for(FileItem fit:flist){
					if(fit.isFormField()){//普通表单字体
						if(fit.getFieldName().equals("filename")){
							title=fit.getString("UTF-8");
						}
					}
					else{
						String filename = fit.getName();
						//C:\\demo\\1.png C:/demo/1.png 反的是单斜杠，一般用于网络，正的是双斜杠，一般用于本地。
						//容错处理，假如filename得到的不是文件名而是含有路径的文件名
						if(filename.lastIndexOf("\\")!=-1){
							filename = filename.substring(filename.lastIndexOf("\\")+1);
						}
						System.out.println(fit.getName());
						File file= new File(savepath, filename);//保存文件的路和文件名
						fit.write(file);//输出流把上传的对象写入指定文件流。
						String uripath = "images/"+filename;//相对路径
						int num = JdbcUtil.executeUpdate("update userData set cardImage=?  where www=? and userName=? ",uripath,www,userName);
						logger.debug("上传成功，执行了图片更新"+num+"次"+title);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else{
			throw new ServletException("enctype 不是multipart");
		}
		response.sendRedirect("password.jsp");
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
