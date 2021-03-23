package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.PBoard;
import service.ImgService;

@WebServlet("/imgboard")
public class ImgBoardController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<PBoard> list = ImgService.getInstance().getPBoardList();
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/ImgBoard.jsp").forward(req, resp);
	}
}
