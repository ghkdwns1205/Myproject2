package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entity.Board;
import service.ServiceAll;

@WebServlet("/freeboard")
public class FreeBoardController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		ServiceAll sa = new ServiceAll();
//		ServiceAll sa = ServiceAll.getInstance();
//		List<Board> list = sa.getList();
		
//		for ( Board  a : list ) { //리스트 잘 가져오는지 찍어보기
//			System.out.println("Content 가져온거 한번 찍어본다잉"+a.getNumber());
//		}
//		System.out.println("zzzzz");
		
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
        
        String page1 = req.getParameter("p");
        String choice1 = req.getParameter("choice");
        String search1 = req.getParameter("search");
        
        System.out.println("choice1 = "+choice1);
        System.out.println("search1 = "+search1);
        
        int page= 1;
        String choice = "title";
		 String search = "";

		if(choice1 != null && !choice1.equals("")) {
			choice = choice1;
		}
		
		if(search1!= null && !search1.equals("")) {
			search = search1;
		}
		
		if(page1 != null && !page1.equals("") ) {
			page = Integer.parseInt(page1);
		}
		
		List<Board> list = sa.getsearchlist(page,choice,search);
		
		int count = sa.getlistcount(choice,search);

		System.out.println(count);
		
		req.setAttribute("count", count);
		req.setAttribute("list", list);
//		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/freeboard.jsp").forward(req, resp);
		
	}
	
}
