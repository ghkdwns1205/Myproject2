package service;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionProvider;
import connection.jdbcUtil;
import entity.Board;
import entity.DetailComment;


public class ServiceAll {
	private static ServiceAll instance = new ServiceAll();

	public static ServiceAll getInstance() {
		return instance;
	}

//	public ServiceAll() {
//		try {
//			String dbURL = "jdbc:mysql://localhost:3306/myproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//			String dbID = "root";
//			String dbPass = "soaksdkady1!";
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection(dbURL,dbID,dbPass);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	// ID 중복 체크
	public int IDCheck(String userID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int value = 0;

		try {
			String sql = "select userid from user where userid = ?";

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next())
				value = 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return value;
	}

	public void setuserinfo(String id, String pass, String name, String email, String gender) {// 회원가입하면 DB에 값 저장
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "insert into user (userid,userpass,name,email,gender) values(?,?,?,?,?)";

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			psmt.setString(3, name);
			psmt.setString(4, email);
			psmt.setString(5, gender);
			int result = psmt.executeUpdate();
			if (result == 1) {
				System.out.println("정상등록");
			} else {
				System.out.println("등록실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}

	}

	public int loginidcheck(String id, String pass) {// 로그인 하면 DB값과 비교
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select userpass from user where userid=? ";
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pass)) {
					return 1; // 값 불러오기 성공(로그인 성공)
				} else {
					return 0; // 비밀번호가 다름
				}

			}
			return -1; // 아이디가 없음

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return -2; // 데이터베이스 오류
	}

	public Board getContents(int contentID) {// 글보기 일단 보류
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String SQL = "select * from contents where contentID < ? and Available =1";
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, contentID);
			rs = psmt.executeQuery();
			if (rs.next()) {
				Board bo = new Board();
//				bo.setContentid(rs.getString(1));
//				bo.setTitle(rs.getString(2));
//				bo.setUserid(rs.getString(3));
//				bo.setDate(rs.getDate(4));
//				bo.setContent(rs.getString(5));
//				bo.setAvailable(rs.getInt(6));
				return bo;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return null; // 오류
	}

	public List<Board> getList() { // 게시판 목록 가져오기
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String SQL = "select * from freeboard";
		List<Board> list = new ArrayList<Board>();
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);

			rs = psmt.executeQuery();
			while (rs.next()) {

				Board bo = new Board();
				bo.setNumber(rs.getInt(1));
				bo.setNickname(rs.getString(2));
				bo.setTitle(rs.getString(3));
				bo.setContent(rs.getString(4));
				bo.setRegdate(rs.getDate(5));
				bo.setHits(rs.getInt(6));
				bo.setLikes(rs.getInt(7));
				bo.setFlag(rs.getString(8));

				list.add(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return list;
	}

	public int setwrite(String table, String title, String content, String nickname) { // 자유게시판 글쓰기
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		System.out.println("table : " + table);

		String SQL = "insert into " + table + " (nickname,title,content) values(?,?,?)";
		try {

			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setString(1, nickname);
			psmt.setString(2, title);
			psmt.setString(3, content);

			System.out.println(SQL);
			psmt.executeUpdate();

			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return result;
	}

	public List<Board> getsearchlist(int page, String choice, String search) {

		int start = 1 + (page - 1) * 9; // 1 , 11 ,21 ,31 ,41 ,51
		int end = page * 9; // 10 20 30 40 50
		System.out.println(page);
		System.out.println(choice);
		System.out.println(search);
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
//		String SQL = "select * from (select * from freeboard where flag='Y' and " + choice
//				   + " like ?) as a  order by regdate desc ";
		String SQL = "Select num.* " + " from (Select @rownum:=@rownum+1 as rownum, n.* "
				+ " from(select @rownum:=0)tmp, " + " (select * " + " from freeboard " + " where flag = 'Y'  " + " and "
				+ choice + " like ? " + " order by regdate desc)n)num " + " Where num.rownum between ? and ? ";
		System.out.println(SQL);

		List<Board> list = new ArrayList<Board>();

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setString(1, "%" + search + "%");
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			rs = psmt.executeQuery();
			while (rs.next()) {

				int number = rs.getInt("number");
				String nickname = rs.getString("nickname");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hits = rs.getInt("hits");
				int like = rs.getInt("likes");
				String flag = rs.getString("flag");

				Board bo = new Board(number, nickname, title, content, regdate, hits, like, flag);
				list.add(bo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}

		return list;
	}

	public int getlistcount(String choice, String search) { // 게시물 역순으로 삭제된거 번호 제외한 갯수불러오기

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String SQL = "Select count(num.number) as count " // 가상의 숫자를 만들어 역순으로 숫자정렬
				+ " from (Select @rownum:=@rownum+1 as num , n.*" + "        from(select *"
				+ "               From freeboard" + "                where " + choice + " like ?" // 검색어가 들어감
				+ "            order by regdate desc)n" + "        Where (@rownum:=0)=0) num ";
		System.out.println(SQL);
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setString(1, "%" + search + "%");
			rs = psmt.executeQuery();

			if (rs.next())
				count = rs.getInt("count");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return count;
	}

	public Board getfbdetaillist(int number1) { // 디테일 리스트 불러오기
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Board bo = new Board();

		String SQL = "select * from freeboard where number = ? ";
		System.out.println(SQL);

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, number1);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int number = rs.getInt("number");
				String nickname = rs.getString("nickname");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hits = rs.getInt("hits");
				int like = rs.getInt("likes");
				String flag = rs.getString("flag");

				System.out.println(number);
				bo = new Board(number, nickname, title, content, regdate, hits, like, flag);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return bo;
	}

	public void gethits(int number) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String SQL = "update freeboard set hits=hits+1 where number = ? ";

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, number);
			int result = psmt.executeUpdate();
			System.out.println("업데이트 잘되면 1이찍힘 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}

	}

	public List<String> intercheck(int number) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		System.out.println("11kjhk");
		String SQL = "select * from Interest where number = ?";

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, number);
			rs = psmt.executeQuery();

			while (rs.next()) {

				String nickname = rs.getString("nickname");
				list.add(nickname);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return list;
	}

	public int likeupdate(int number) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		String SQL = "update freeboard set likes=likes+1 where number = ?";
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, number);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}

		return result;
	}

	public int insertInterest(int number, String id) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		String SQL = "insert into Interest(number,nickname) values(?,?)";

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(SQL);
			psmt.setInt(1, number);
			psmt.setString(2, id);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}

		return result;
	}

	public int updatescore(int number) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "update freeboard set likes=likes+1 where number = ?";
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			psmt.executeUpdate();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return -1;
	}

	public int insertscore(int number, String nickname) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "insert into Interest(number,nickname) values(?,?)";
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			psmt.setString(2, nickname);
			psmt.executeUpdate();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return -1;
	}

	public int setcomment(String nickname, String comment, int number) {  //댓글 입력

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "insert into fdcomment(cnumber,comment,nickname) values(?,?,?)";
		
		try {
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			psmt.setString(2, comment);
			psmt.setString(3, nickname);
			psmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return -1;
	}

	public List<DetailComment> getfbcomment(int number) { //댓글 불러오기
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<DetailComment> list = new ArrayList<DetailComment>();
		String sql = "select * from fdcomment where cnumber = ? ";
		
		try {
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int seq = rs.getInt("seq");
				int cnumber = rs.getInt("cnumber");
				String comment = rs.getString("comment");
				Date regdate = rs.getDate("regdate");
				String nickname = rs.getString("nickname");
				String delflag = rs.getString("delflag");
				
				DetailComment dc = new DetailComment(seq,cnumber,comment,regdate,nickname,delflag);
				list.add(dc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return list;
	}
}
