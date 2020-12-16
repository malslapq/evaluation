package com.my.DTO;

public class LikeyDTO {
	
	private String userid;
	private int evaluationid;
	private String lecturename;
	private String professorname;
	private String userip;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getEvaluationid() {
		return evaluationid;
	}
	public void setEvaluationid(int evaluationid) {
		this.evaluationid = evaluationid;
	}
	public String getProfessorname() {
		return professorname;
	}
	public void setProfessorname(String professorname) {
		this.professorname = professorname;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	@Override
	public String toString() {
		return "LikeyDTO [userid=" + userid + ", evaluationid=" + evaluationid + ", professorname=" + professorname
				+ ", userip=" + userip +", lecturename="+lecturename+"]";
	}
	public LikeyDTO(String userid, int evaluationid, String professorname, String userip, String lecturename) {
		super();
		this.userid = userid;
		this.evaluationid = evaluationid;
		this.professorname = professorname;
		this.userip = userip;
		this.lecturename = lecturename;
	}
	public LikeyDTO() {
		super();
	}
	public String getLecturename() {
		return lecturename;
	}
	public void setLecturename(String lecturename) {
		this.lecturename = lecturename;
	}
	
	
	
}
