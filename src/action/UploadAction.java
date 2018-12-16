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
 * @version ʱ�䣺2018��5��31�� ����3:25:55
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
			//�½��ϴ�����
			DiskFileItemFactory df = new DiskFileItemFactory();//��������
			ServletFileUpload sfu = new ServletFileUpload(df);//
			sfu.setFileSizeMax(500*1024*1024);
			try {
				//ȡ����
				List<FileItem> flist = sfu.parseRequest(request);//������������ݣ������ϴ����ݵļ��ϣ�һ��name�������ݾ���һ��FileItem
				for(FileItem fit:flist){
					if(fit.isFormField()){//��ͨ������
						if(fit.getFieldName().equals("filename")){
							title=fit.getString("UTF-8");
						}
					}
					else{
						String filename = fit.getName();
						//C:\\demo\\1.png C:/demo/1.png �����ǵ�б�ܣ�һ���������磬������˫б�ܣ�һ�����ڱ��ء�
						//�ݴ�������filename�õ��Ĳ����ļ������Ǻ���·�����ļ���
						if(filename.lastIndexOf("\\")!=-1){
							filename = filename.substring(filename.lastIndexOf("\\")+1);
						}
						System.out.println(fit.getName());
						File file= new File(savepath, filename);//�����ļ���·���ļ���
						fit.write(file);//��������ϴ��Ķ���д��ָ���ļ�����
						String uripath = "images/"+filename;//���·��
						int num = JdbcUtil.executeUpdate("update userData set cardImage=?  where www=? and userName=? ",uripath,www,userName);
						logger.debug("�ϴ��ɹ���ִ����ͼƬ����"+num+"��"+title);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else{
			throw new ServletException("enctype ����multipart");
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
