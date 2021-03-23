package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("로그아웃 컨트롤 하위");
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(true);
		String id = (String) session.getAttribute("id");
		if(id==null||id.equals("")) {
			out.println("<script>alert('로그인 되어있지 않습니다'); location.href='main'</script>");
		}else {
		session.invalidate();
		out.println("<script>alert('로그아웃 되셨습니다.'); location.href='main'</script>");
		}
		
//		req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
	}
}
