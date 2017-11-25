package com.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.bean.entity.UserTable;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;


@SuppressWarnings("serial")
public class uploadServlet extends HttpServlet {

	UserTableService userService = new UserTableServiceImpl();
	UserTable usertable = new UserTable();
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前用户名
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		//通过用户名查询此用户信息
		ResultSet rs = userService.findUserByUsername(username);
		//获取此用户id
		int userid = 0;
	    try {
			if(rs.next()){
				 userid = rs.getInt("userid");
				usertable.setUserid(userid);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//查询当前用户
		//1、创建 DiskFileItemFactory类。
		DiskFileItemFactory factory = new DiskFileItemFactory(10*1024, new File("f:/temp/"));
		//2、创建ServletFileUpload类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//3、设置字符编码集
		upload.setHeaderEncoding("utf-8");
		//4、解析数据。
	
			//每一个请求的文件
		List<FileItem> list=null;
		String fileName = null;
			try {
			    list = upload.parseRequest(request);
				FileItem file = list.get(0);
				//解析文件
				//得到文件名(getName())
				fileName = file.getName();
				
				//设置用户头像
				usertable.setUserimg(fileName);
				InputStream in = file.getInputStream();
				/**
				 * 4.把文件数据内容存储到服务器端的硬盘中
				 */
				//url获取 
				
				String path = request.getRealPath("upload") + "\\" + fileName;
				
				FileUtils.copyInputStreamToFile(in, new File(path));
				/**
				 * 5.文件上传完毕，手动清理缓存文件
				 */
				
				file.delete();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
 
		usertable.setUserimg(fileName);
		System.out.println(fileName);
		userService.updateImg(usertable.getUserid(),usertable);
		
		session.setAttribute("username",username);
		
		System.out.println(request.getRealPath("upload") + "\\" + usertable.getUserimg());
		
		session.setAttribute("userimg", usertable.getUserimg());
		
		request.getRequestDispatcher("MyFindJsp.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
