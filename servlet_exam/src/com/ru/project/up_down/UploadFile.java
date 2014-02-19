package com.ru.project.up_down;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * 
 * 项目名称：servlet_exam
 * 类描述：使用common-fileupload实现文件上传
 * 创建人：Administrator
 * 创建时间：2014-2-18 下午4:36:48
 * 修改人：Administrator
 * 修改时间：2014-2-18 下午4:36:48
 * 修改备注：
 * @since  jdk1.7
 * @version 1.0
 */
public class UploadFile extends HttpServlet{
	private Logger log = Logger.getLogger(UploadFile.class);
    // 文件的上传路径 
    private String uploadPath = "G:/test/file/"; 
    // 限制文件的上传大小 
    private int maxPostSize = 100 * 1024 * 1024;  //最大上传文件大小100M
	
    /**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("上传开始!"); 
        response.setContentType("text/html;charset=UTF-8"); 
        //DiskFileItemFactory创建fileitem实例，如果文件很小保存在内存中，超过阈值保存在磁盘中。
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        factory.setSizeThreshold(4096); //设置阈值
        //ServletFileUpload用来管理一个jsp提交的FileItems
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setHeaderEncoding("utf-8");
        upload.setSizeMax(maxPostSize);
        try { 
           List fileItems = upload.parseRequest(request);
           System.out.println("文件数量：" + fileItems.size());
           Iterator iter = fileItems.iterator();
           File file = new File(uploadPath);
           if(!file.exists()){
        	   System.out.println("创建目录" + uploadPath);
        	   file.mkdirs();
           }
           System.out.println("上传目录" + file.getAbsolutePath());
           while (iter.hasNext()) { 
               FileItem item = (FileItem) iter.next(); 
               String name = item.getName();
               System.out.println("文件名称 = " + name);
             //检查当前项目是普通表单项目还是上传文件。
               if (item.isFormField()){
            	 //如果是普通表单项目，显示表单内容。
            	   String fieldName = item.getFieldName();
            	   System.out.println("表单内容：" + fieldName);
               } 
                   
               if (!item.isFormField()) { 
            	   String filePath = uploadPath + name;
                   item.write(new File(filePath)); 
                   response.getWriter().write("上传成功");
               } 
           } 
       } catch (Exception e) {
    	   log.error("文件上传失败", e);
       } 
	}
	
	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
