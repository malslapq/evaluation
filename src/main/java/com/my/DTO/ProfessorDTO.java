package com.my.DTO;

public class ProfessorDTO {
	private String lecturename;
	private String professorname;
	private int evaluation;
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
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "ProfessorDTO [lecturename=" + lecturename + ", professorname=" + professorname + ", evaluation="
				+ evaluation + "]";
	}
	public ProfessorDTO(String lecturename, String professorname, int evaluation) {
		super();
		this.lecturename = lecturename;
		this.professorname = professorname;
		this.evaluation = evaluation;
	}
	public ProfessorDTO() {
		super();
	}
	
	
}
