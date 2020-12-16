package com.my.DTO;

public class UserDTO {

	private String userID;
	private String userPasswd;
	private String userEmail;
	private String userEmailHash;
	private boolean userEmailChecked;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmailHash() {
		return userEmailHash;
	}
	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}
	public boolean isUserEmailChecked() {
		return userEmailChecked;
	}
	public void setUserEmailChecked(boolean userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}
	@Override
	public String toString() {
		return "UserDTO [userID=" + userID + ", userPasswd=" + userPasswd + ", userEmail=" + userEmail
				+ ", userEmailHash=" + userEmailHash + ", userEmailChecked=" + userEmailChecked + "]";
	}
	public UserDTO(String userID, String userPasswd, String userEmail, String userEmailHash, boolean userEmailChecked) {
		super();
		this.userID = userID;
		this.userPasswd = userPasswd;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailChecked = userEmailChecked;
	}
	public UserDTO() {
		super();
	}
	

	
	
}
