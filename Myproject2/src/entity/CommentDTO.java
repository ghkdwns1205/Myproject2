package entity;

import java.util.Date;

public class CommentDTO {

	private int wid;
	private String pid;
	private String comment;
	private int score;
	private Date regdate;
	private String userid;
	private String delflag;
	
	
	
	public CommentDTO() {
	}
	public CommentDTO(int wid, String pid, String comment, int score, Date regdate, String userid, String delflag) {
		super();
		this.wid = wid;
		this.pid = pid;
		this.comment = comment;
		this.score = score;
		this.regdate = regdate;
		this.userid = userid;
		this.delflag = delflag;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	
	
	
}
