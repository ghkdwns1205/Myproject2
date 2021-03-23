package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



//import com.mysql.cj.Session;

import entity.Board;
import service.ServiceAll;

@WebServlet("/write")
public class WriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		
//		HttpSession session = req.getSession(true);
//		if(session.getAttribute("id").equals("")||session.getAttribute("id")==null) {
//			out.println("<script>alert('로그인을 해주세요'); location.href='login';</script>");
//		}
      
		
		req.getRequestDispatcher("/WEB-INF/view/write.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		HttpSession session = req.getSession(true);
		
		String table = req.getParameter("table");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String nickname = req.getParameter("nickname");
		System.out.println(nickname);
		System.out.println("write 컨트롤러 값 받아오기 table = " +table);
		System.out.println("write 컨트롤러 값 받아오기 title = " +title);
		System.out.println("write 컨트롤러 값 받아오기 content = " +content);
		ServiceAll sa = new ServiceAll();
		
		
		
		int result = sa.setwrite(table,title,content,nickname);
		System.out.println("result 1되면 정상 : " + result);
		
		resp.sendRedirect("freeboard");
//		List<Board> list = sa.setwrite(); 
	}
}
