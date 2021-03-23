package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceAll;

@WebServlet("/join")
public class JoinController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
//		PrintWriter out = resp.getWriter();
		String id = req.getParameter("nid");
		String pass = req.getParameter("npass");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		
//		if(id==null||id.equals("")) {
//			out.println("<sript>");
//		}
		ServiceAll sa = ServiceAll.getInstance();
		sa.setuserinfo(id,pass,name,email,gender);
		
		resp.sendRedirect("login");
	}
}
