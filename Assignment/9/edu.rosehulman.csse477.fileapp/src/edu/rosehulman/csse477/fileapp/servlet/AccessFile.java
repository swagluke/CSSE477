package edu.rosehulman.csse477.fileapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.rosehulman.csse477.fileapp.dao.FileDao;

/**
 * Servlet implementation class FileCounter
 */
@WebServlet("/AccessFile")
public class AccessFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileDao filedao;

	@Override
	public void init() throws ServletException {
		this.filedao = new FileDao(this.getServletContext().getRealPath("/data"));
		try {
			filedao.getFile();
		} catch (Exception e) {
			getServletContext().log("An exception occurred in FileCounter", e);
			throw new ServletException("An exception occurred in FileCounter" + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter output = response.getWriter();
		filedao.getFile();
		output.println(filedao.getFile());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = this.getContent(request);
		response.setContentType("text/plain");
		PrintWriter output = response.getWriter();
		output.println(filedao.postFile(content));
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		filedao.removeFile();
	}
//	Aleady done for us. No need to implement more.	
//	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, java.io.IOException {
//	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		filedao.removeFile();
		String content = this.getContent(request);
		response.setContentType("text/plain");
		PrintWriter output = response.getWriter();
		output.println(filedao.postFile(content));
	}
	
	public String getContent(HttpServletRequest request){
	    StringBuilder buffer = new StringBuilder();
	    BufferedReader reader;
	    String data = "";
		try {
			reader = request.getReader();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        buffer.append(line);
		    }
		    data = buffer.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
