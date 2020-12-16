package com.my.DAO;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.my.DTO.UserDTO;

@Repository
public class UserDAOimple implements UserDAO{

	@Resource
	SqlSession session;
	
	@Resource
	SqlSessionFactory ssf;
	
	@Override
	public int join(UserDTO udto) throws Exception {
		return session.insert("UserMapper.insert", udto);
	}

	@Override
	public String selectuserPasswd(UserDTO udto) throws Exception {
		return session.selectOne("UserMapper.selectuserpasswd", udto);
	}

	@Override
	public int useremailchecked(UserDTO udto) throws Exception {
		int result = session.selectOne("UserMapper.selectUserEmailChecked",udto);
		return result;
	}

	@Override
	public int setuseremailchecked(UserDTO udto) throws Exception {
		return session.update("UserMapper.useremailcheckedupdate",udto);
	}

	@Override
	public String getuseremail(UserDTO udto) throws Exception {
		return session.selectOne("UserMapper.getuseremail", udto);
	}

	@Override
	public int emailcheckedupdate(String userID) throws Exception {
		return session.update("UserMapper.mailcheckedupdate", userID);
	}

	@Override
	public UserDTO selectone(String userID) throws Exception {
		return session.selectOne("UserMapper.selectone", userID);
	}

	@Override
	public int defection(UserDTO udto) throws Exception {
		session.delete("UserMapper.deletelikey", udto);
		session.delete("UserMapper.deleteevaluation", udto);
		return session.delete("UserMapper.defection", udto);
	}

	@Override
	public int passwdupdate(UserDTO udto) throws Exception {
		return session.update("UserMapper.passwdupdate", udto);
	}

	@Override
	public int emailupdate(UserDTO udto) throws Exception {
		return session.update("UserMapper.emailupdate", udto);
	}

}
