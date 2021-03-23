package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionProvider;
import connection.jdbcUtil;
import entity.CommentDTO;
import entity.PBoard;

public class ImgService {

//	인스턴스 생성
	private static ImgService instance = new ImgService();

	public static ImgService getInstance() {
		return instance;
	}
	
	//검색이 포함된 리스트
	public List<PBoard> getPBoardList(){
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<PBoard> list = new ArrayList<PBoard>();
		
		String sql="select * from pcontent";
		
		try {
			
			con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			 while (rs.next()) {
		            String pid = rs.getString("pid");
		            String pname = rs.getString("pname");
		            String content = rs.getString("content");
		            String category = rs.getString("category");
		            int price = rs.getInt("price");
		            Date regdate = rs.getDate("regdate");
		            String delflag = rs.getString("delflag");
		            String path = rs.getString("path");
		            
		           PBoard pb = new PBoard(pid,pname,content,category,price,regdate,delflag,path);
		           list.add(pb);
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

	public PBoard getPhotoDetail(String id) {
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		PBoard pb = null;
		
		String sql="select * from pcontent where pid=?";
		
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			
			 if(rs.next()) {
		            String pid = rs.getString("pid");
		            String pname = rs.getString("pname");
		            String content = rs.getString("content");
		            String category = rs.getString("category");
		            int price = rs.getInt("price");
		            Date regdate = rs.getDate("regdate");
		            String delflag = rs.getString("delflag");
		            String path = rs.getString("path");
		            
		            pb = new PBoard(pid,pname,content,category,price,regdate,delflag,path);
		          
			 }	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		
		return pb;
	}

	public List<CommentDTO> getCommentList(String id) {
	
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		
		String sql="select * from comment where pid=?";
		
		try {
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1, id);
			 rs=psmt.executeQuery();
			
			 while (rs.next()) {
		            int wid = rs.getInt("wid");
		            String pid = rs.getString("pid");
		            String comment = rs.getString("comment");
		            int score = rs.getInt("score");
		            Date regdate = rs.getDate("regdate");
		            String userid = rs.getString("userid");
		            String delflag = rs.getString("delflag");
		           
		            
		           CommentDTO cd = new CommentDTO(wid,pid,comment,score,regdate,userid,delflag);
		           list.add(cd);
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

	public int getCommentCount(String pid) {  //
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(wid)as count from comment where pid=?";
		
		try {
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1, pid);
			 rs=psmt.executeQuery();
			 
			 if(rs.next()) {
				  count = rs.getInt("count");
			 }
			 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return count;
	}

	public int insertComment(CommentDTO cd) {
		Connection con = null;
		PreparedStatement psmt = null;
		int result = 0;
		
		String sql = "insert into comment(pid, comment,score,userid) values(?,?,?,?)";
		
		try {
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1,cd.getPid());
			 psmt.setString(2,cd.getComment());
			 psmt.setInt(3,cd.getScore());
			 psmt.setString(4,cd.getUserid());
			 
			 result = psmt.executeUpdate();
			 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		return result;
	}

	
	
	public float getaverage(String pid) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		float aver = 0;
		
		String sql = "select avg(score) as avg from comment where pid = ?";
		
		try {
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1,pid);
			
			 
			 rs=psmt.executeQuery();
			 if(rs.next()) {
			 aver = rs.getFloat("avg");
			 }
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(con);
		}
		
		return aver;
	}
	
	
	
	
	
	
	
	
}
