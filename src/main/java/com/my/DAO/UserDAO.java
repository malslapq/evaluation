package com.my.DAO;

import com.my.DTO.UserDTO;

public interface UserDAO {
	public int join(UserDTO udto) throws Exception; 
	public String selectuserPasswd(UserDTO udto) throws Exception;
	public int useremailchecked(UserDTO udto) throws Exception;
	public int setuseremailchecked(UserDTO udto) throws Exception;
	public String getuseremail(UserDTO udto) throws Exception;
	public int emailcheckedupdate(String userID) throws Exception;
	public UserDTO selectone(String userID) throws Exception;
	public int defection(UserDTO udto) throws Exception;
	public int passwdupdate(UserDTO udto) throws Exception;
	public int emailupdate(UserDTO udto) throws Exception;
}
