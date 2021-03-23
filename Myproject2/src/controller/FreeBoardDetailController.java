package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Board;
import entity.DetailComment;
import service.ServiceAll;

@WebServlet("/freeboarddetail")
public class FreeBoardDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		ServiceAll sa = new ServiceAll();
		
		String number_ = req.getParameter("number");
		System.out.println("프리보드디테일 넘버 = "+number_);
		int number = Integer.parseInt(number_);
		Board bo = sa.getfbdetaillist(number);
		
		sa.gethits(number);
		List<DetailComment> dcl = sa.getfbcomment(number);
		
		req.setAttribute("dcl", dcl);
		req.setAttribute("num", number);
		req.setAttribute("list", bo);
		req.getRequestDispatcher("/WEB-INF/view/freeboarddetail.jsp").forward(req, resp);
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		String interest = req.getParameter("interest");
//		System.out.println("두포스트 : "+interest);
//		
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
		
		HttpSession session = req.getSession(true);
		String nickname = (String) session.getAttribute("id");
		
		String comment = req.getParameter("comment");
		
		String number_ = req.getParameter("number");
		int number = Integer.parseInt(number_);
		System.out.println("닉네임:"+nickname);
		System.out.println(number);
		System.out.println(comment);
		String color = req.getParameter("color");
		ServiceAll sa = new ServiceAll();
		
		int result = sa.setcomment(nickname,comment,number);
		System.out.println("댓글업로드 성공 =1 실패=-1 결과값 : "+ result );
		
		req.setAttribute("color", color);
		resp.sendRedirect("freeboarddetail?number="+number);
	}
}
