package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ServiceAll;

@WebServlet("/interest")
public class InterestController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		PrintWriter out = resp.getWriter();
//		ServiceAll sa = new ServiceAll();
//		
//		HttpSession session = req.getSession(true);
//		String id = (String) session.getAttribute("id");
//		if(id==null||id.equals("")) {
//			out.println("<script>alert('로그인 먼저 해주세요~'); history.back();</script>");
//		}
//		String number_ = req.getParameter("number");
//		int number= Integer.parseInt(number_);
//		
//		String like_ = req.getParameter("like");
//		int like = Integer.parseInt(like_);
//		
//		
//		
//		System.out.println("인터레스트컨트롤러 id : " + id);
//		System.out.println("인터레스트컨트롤러 number : " + number);
//		System.out.println("인터레스트컨트롤러 like : " + like);
//		
//		int result = sa.insertInterest(number,id);
//		List<Interest> list = sa.intercheck(number); //비교할 닉네임 담아오기
//		
//		
////		boolean check = true;
//		
////		for(int i = 0; i<list.size();i++) {
////		if(list.get(i).equals(id))
////		}
//		
//		for(Interest it : list) {
//			if(it.equals(id)) {
//				out.println("<script>alert('이미 좋아요한 글입니다'); history.back();</script>");
//				
//			}else {
//				int result1 = sa.likeupdate(number);
//			}
//		}
//		

		HttpSession session = req.getSession(true);
		String id = (String) session.getAttribute("id");
		int number = Integer.parseInt(req.getParameter("number"));
		List<String> list = ServiceAll.getInstance().intercheck(number);

		boolean check = false;
		for (int i = 0; i < list.size(); i++) {

			String userid = list.get(i);
			if (id.equals(userid)) {
				check = true;
			}
		}

		if (check == false) {
			int result = ServiceAll.getInstance().updatescore(number);
			int result1 = ServiceAll.getInstance().insertscore(number, id);
		}

		resp.sendRedirect("freeboarddetail?number=" + number);
	}
}
