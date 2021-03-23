package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.CommentDTO;
import entity.PBoard;
import service.ImgService;

@WebServlet("/view")
public class ImgViewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pid = req.getParameter("pid");
		
		
	
		
		PBoard pb = ImgService.getInstance().getPhotoDetail(pid);
		req.setAttribute("p", pb);

		int count = ImgService.getInstance().getCommentCount(pid);
		req.setAttribute("count", count);

		if (count != 0) {
			List<CommentDTO> clist = ImgService.getInstance().getCommentList(pid);
			req.setAttribute("c", clist);
		}
		
		
		Float aver = ImgService.getInstance().getaverage(pid);	
		req.setAttribute("aver", aver);
		
		req.getRequestDispatcher("/WEB-INF/view/view.jsp").forward(req, resp);
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8"); //한글써도 안깨지게
	
		String userid = "jun";
		String comment = req.getParameter("comment");
		String score_ = req.getParameter("s");
		int score = Integer.parseInt(score_);
		String pid = req.getParameter("pid");
				
		CommentDTO cd = new CommentDTO();
		cd.setUserid(userid);
		cd.setPid(pid);
		cd.setScore(score);
		cd.setComment(comment);
	
		int result = ImgService.getInstance().insertComment(cd);
		
	resp.sendRedirect("view?pid="+pid);
	
}


}
