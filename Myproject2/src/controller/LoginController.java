package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceAll;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		HttpSession session = req.getSession(true);
		PrintWriter out = resp.getWriter();
		
		String id = (String) session.getAttribute("id");
		
		if(id != null) {
			out.println("<script>alert('이미 로그인 되어있습니다'); location.href='main'</script>");
		}else {
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		}
		
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
	
		PrintWriter out = resp.getWriter(); //java에서 스크립트로 알럴트창 띄울수 있게
		HttpSession session = req.getSession(true); //세션 생성하기
		
		String id=req.getParameter("id");
		String pass = req.getParameter("pass");
		
		System.out.println("로그인.jsp에서 컨트롤러 post로 제대로 들어오는지 확인");
		System.out.println("id : "+ id);
		System.out.println("pass : "+pass);

		
		
		ServiceAll sa = new ServiceAll();
		
		int result = sa.loginidcheck(id,pass);
		System.out.println("로그인 아이디 비밀번호 비교\n 1. 로그인 성공 0. 비밀번호가 다름 -1. 아이디가 없음 -2데이터 베이스 접속오류");
		System.out.println(result);
		
		if(result==1) {
			out.println("<script>alert('로그인 완료');location.href='main' </script>");
			session.setAttribute("id", id);
//			resp.sendRedirect("main");
		}else if(result==0) {
			out.println("<script>alert('비밀번호가 틀립니다'); history.back()</script>");
		}else if(result==-1) {
			out.println("<script>alert('아이디가 틀립니다'); history.back()</script>");
		}
		
}
}
