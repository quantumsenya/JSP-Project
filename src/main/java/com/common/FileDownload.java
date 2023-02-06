package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileRepo = "c:/file_proj1";
		String no = request.getParameter("no");
		String path = request.getParameter("path");
		String fileName = request.getParameter("imageFileName");
		File downloadFile = new File(fileRepo+"/"+path+"/"+no, fileName); // 다운로드 대상 파일
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
		
		try (
			OutputStream out = response.getOutputStream(); // 출력스트림
			InputStream in = new FileInputStream(downloadFile);
		){
			byte[] buffer = new byte[1024*8];
			int count=0;
			while ( (count = in.read(buffer))!=-1 ) { // 읽어 올 값이 있다면
				out.write(buffer,0,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
