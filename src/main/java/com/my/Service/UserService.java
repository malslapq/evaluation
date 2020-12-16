package com.my.Service;

import java.util.Map;

import com.my.DTO.UserDTO;

public interface UserService {
	public Map<String, String> join(UserDTO udto) throws Exception;
	public Map<String, Object> login(UserDTO udto) throws Exception;
	public String idcheck(String userID) throws Exception;
	public void sendemail(final UserDTO udto) throws Exception;
	public String emailcheckedupdate(String userID) throws Exception;
	public String resendemail(UserDTO udto) throws Exception;
	public UserDTO selectone(String userID) throws Exception;
	public Map<String, String> defection(UserDTO udto) throws Exception;
	public String passwdupdate(String oldPasswd, String newPasswd, String userID) throws Exception;
	public Map<String,String> emailupdate(String userEmail, String userID) throws Exception;

}