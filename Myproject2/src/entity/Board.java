package entity;

import java.util.Date;

public class Board {

		private int number;
		private String nickname;
		private String title;
	    private String content;
	    private Date regdate; 
	    private int hits;
	    private int likes;
	    private String flag;
			
	    public Board(int number, String nickname, String title, String content, Date regdate, int hits, int likes,
				String flag) {
			super();
			this.number = number;
			this.nickname = nickname;
			this.title = title;
			this.content = content;
			this.regdate = regdate;
			this.hits = hits;
			this.likes = likes;
			this.flag = flag;
		}

		public Board() {
	    
	    }

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getRegdate() {
			return regdate;
		}

		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}

		public int getHits() {
			return hits;
		}

		public void setHits(int hits) {
			this.hits = hits;
		}

		public int getLikes() {
			return likes;
		}

		public void setLikes(int likes) {
			this.likes = likes;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}
	
		
	    
}
