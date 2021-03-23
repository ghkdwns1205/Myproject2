package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/naverlogin")
public class NaverLoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("여기1번");
		String clientId = "2Wq7aK1tqidstWiEOk3e";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/Myproject2/callback", "UTF-8"); //http:localhost:3306/Myproject2/callback
		// http://localhost:8080/CinemaProject/user/login
		
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		HttpSession session=req.getSession();
		System.out.println("여까지 옴");
		session.setAttribute("state", state);
		req.setAttribute("apiURL", apiURL);
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}
}