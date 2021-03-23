package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceAll;

@WebServlet("/idCheck")
public class IDCheckController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("idchedsck");
		String id = req.getParameter("id");
		int check = ServiceAll.getInstance().IDCheck(id);

		req.setAttribute("check", check);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/WEB-INF/view/idcheck.jsp").forward(req, res);
	}
}
