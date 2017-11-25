package com.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收需要下载的文件img
	
		
		String img = request.getParameter("userimg");
		if(img!=null && !img.equals("")){
			
			//文件名称
			String fileName = img;
			//文件所在路径
			
			InputStream in = this.getServletContext().getResourceAsStream("/upload/"+fileName);
			
			
			/**
			 * 对中文的文件名加入URLEncoder加密
			 *  请求发送数据：  request 带中文  ->  浏览器传输（URLEncoder） -》( 服务器获取（URLDecoder）)
				响应发送数据：  response 带中文( URLEncoder  ) -> 浏览器传输 - 》  浏览器获取(URLDecoder)
			 */
			fileName = URLEncoder.encode(fileName, "utf-8");
			/**
			 * 设置一个响应头: Content-Disposition  告诉浏览器以下载的方法打开该资源
			 */
			/**
			 * 注意： 不同的浏览器在识别content-disposition值的内容有差异
			 * 	     IE: attachment;filename=1.jpg
			 *      非IE: attachment;filename*=1.jpg
			 */
			String userAgent = request.getHeader("user-agent");
			String content = "";
			if(userAgent.contains("Trident")){
				//IE
				content = "attachment;filename="+fileName;
			}else{
				//非IE
				content = "attachment;filename*="+fileName;
			}
			response.setHeader("content-disposition", content);

			//2)把文件内容发送给浏览器
			OutputStream out = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while(  (len=in.read(buf))!=-1   ){
				out.write(buf, 0, len);
			}
			out.close();
			in.close();	
			
		}
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
