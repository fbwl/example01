package model.dto.memo;

import java.util.Date;

public class MemoDTO {
	private int no;
	private String writer;
	private String content;
	private Date regi_date;
	public MemoDTO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	@Override
	public String toString() {
		return "MemoDTO [no=" + no + ", writer=" + writer + ", content=" + content + ", regi_date=" + regi_date + "]";
	}
}
