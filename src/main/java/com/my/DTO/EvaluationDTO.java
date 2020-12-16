package com.my.DTO;

import java.util.Date;

public class EvaluationDTO {
	
	private int evaluationid;
	private String userid;
	private String lecturename;
	private String professorname;
	private int lectureyear;
	private String semesterdivide;
	private String lecturedivide;
	private String evaluationtitle;
	private String evaluationcontent;
	private String totalscore;
	private String creditscore;
	private String comfortablescore;
	private String lecturescore;
	private int likecount;
	private Date regdate;
	
	public int getEvaluationid() {
		return evaluationid;
	}
	public void setEvaluationid(int evaluationid) {
		this.evaluationid = evaluationid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLecturename() {
		return lecturename;
	}
	public void setLecturename(String lecturename) {
		this.lecturename = lecturename;
	}
	public String getProfessorname() {
		return professorname;
	}
	public void setProfessorname(String professorname) {
		this.professorname = professorname;
	}
	public int getLectureyear() {
		return lectureyear;
	}
	public void setLectureyear(int lectureyear) {
		this.lectureyear = lectureyear;
	}
	public String getSemesterdivide() {
		return semesterdivide;
	}
	public void setSemesterdivide(String semesterdivide) {
		this.semesterdivide = semesterdivide;
	}
	public String getLecturedivide() {
		return lecturedivide;
	}
	public void setLecturedivide(String lecturedivide) {
		this.lecturedivide = lecturedivide;
	}
	public String getEvaluationtitle() {
		return evaluationtitle;
	}
	public void setEvaluationtitle(String evaluationtitle) {
		this.evaluationtitle = evaluationtitle;
	}
	public String getEvaluationcontent() {
		return evaluationcontent;
	}
	public void setEvaluationcontent(String evaluationcontent) {
		this.evaluationcontent = evaluationcontent;
	}
	public String getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(String totalscore) {
		this.totalscore = totalscore;
	}
	public String getCreditscore() {
		return creditscore;
	}
	public void setCreditscore(String creditscore) {
		this.creditscore = creditscore;
	}
	public String getComfortablescore() {
		return comfortablescore;
	}
	public void setComfortablescore(String comfortablescore) {
		this.comfortablescore = comfortablescore;
	}
	public String getLecturescore() {
		return lecturescore;
	}
	public void setLecturescore(String lecturescore) {
		this.lecturescore = lecturescore;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "EvaluationDTO [evaluationid=" + evaluationid + ", userid=" + userid + ", lecturename=" + lecturename
				+ ", professorname=" + professorname + ", lectureyear=" + lectureyear + ", semesterdivide="
				+ semesterdivide + ", lecturedivide=" + lecturedivide + ", evaluationtitle=" + evaluationtitle
				+ ", evaluationcontent=" + evaluationcontent + ", totalscore=" + totalscore + ", creditscore="
				+ creditscore + ", comfortablescore=" + comfortablescore + ", lecturescore=" + lecturescore
				+ ", likecount=" + likecount + ", regdate=" + regdate + "]";
	}
	public EvaluationDTO(int evaluationid, String userid, String lecturename, String professorname, int lectureyear,
			String semesterdivide, String lecturedivide, String evaluationtitle, String evaluationcontent,
			String totalscore, String creditscore, String comfortablescore, String lecturescore, int likecount,
			Date regdate) {
		super();
		this.evaluationid = evaluationid;
		this.userid = userid;
		this.lecturename = lecturename;
		this.professorname = professorname;
		this.lectureyear = lectureyear;
		this.semesterdivide = semesterdivide;
		this.lecturedivide = lecturedivide;
		this.evaluationtitle = evaluationtitle;
		this.evaluationcontent = evaluationcontent;
		this.totalscore = totalscore;
		this.creditscore = creditscore;
		this.comfortablescore = comfortablescore;
		this.lecturescore = lecturescore;
		this.likecount = likecount;
		this.regdate = regdate;
	}
	public EvaluationDTO() {
		super();
	}
	
	
	
}
