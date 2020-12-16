package com.my.Service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage; 
import org.springframework.mail.javamail.JavaMailSenderImpl; 
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.DAO.UserDAOimple;
import com.my.DTO.UserDTO;


@Service
public class UserServiceimple implements UserService{

	@Autowired 
	private JavaMailSenderImpl mailSender;
	
	@Resource
	private BCryptPasswordEncoder encode;
	
	@Resource
	private UserDAOimple dao;

	@Transactional
	@Override
	public Map<String, String> join(UserDTO udto) throws Exception {
		Map<String, String> map = new HashMap<String,String>();
		String msg = idcheck(udto.getUserID());
		String url = null;
		if (msg.equals("이미 존재하는 아이디 입니다.")) {
			url = "/user/join";
			map.put("msg", msg);
			map.put("url", url);
		}
		else {
			udto.setUserPasswd(encode.encode(udto.getUserPasswd()));
			udto.setUserEmailHash(encode.encode(udto.getUserEmail()));
			dao.join(udto);
			msg = "회원가입 성공";
			url = "/user/emailsendaction";
			map.put("msg", msg);
			map.put("url", url);
			sendemail(udto);
		}
		return map;
	}

	@Override
	public Map<String, Object> login(UserDTO udto) throws Exception { 
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = null;
		String url = null;
		int result = 0;
		if (encode.matches(udto.getUserPasswd(), dao.selectuserPasswd(udto))) {
			if (dao.useremailchecked(udto) == 1) {
				msg = "로그인 성공";
				url = "/user/index";
				result = 1;				
			}
			else {
				String useremail = dao.getuseremail(udto);
				msg = "이메일 인증 필요";
				url = "/user/emailsendfirm";
				result = 3;
				map.put("useremail", useremail);
			}
		}
		else {
			msg = "아이디나 비밀번호가 다릅니다.";
			url = "/user/login";
			result = 0;
		}
		map.put("msg",msg);
		map.put("url",url);
		map.put("result", result);
		return map;
	}

	@Override
	public String idcheck(String userID) throws Exception {
		String msg = "사용 가능한 아이디 입니다.";
		UserDTO udto = new UserDTO();
		udto.setUserID(userID);
		if (dao.selectuserPasswd(udto) != null) {
			msg = "이미 존재하는 아이디 입니다.";
		}
		return msg;
	}

	@Override
	public void sendemail(final UserDTO udto) throws Exception {
		String text = "<h1>강의평가 웹사이트 메일 인증</h1>"+ 
				"이메일 인증을 하시려면 아래 이메일 인증 확인을 누르세요."+"<br>"+ 
				"<h3><a href='http://101.101.219.205:8080/assessment/user/emailconfirm?userID="+udto.getUserID()+"'"+
				">이메일 인증 확인</a></h3>";
		
		final MimeMessagePreparator preparetor = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom("malslapq92@naver.com", "강의평가 웹사이트");
				helper.setTo(udto.getUserEmail());
				helper.setSubject("강의평가 웹사이트 인증 메일입니다.");
				helper.setText(text, true);
			}
		};
		mailSender.send(preparetor);
	}
	

	@Override
	public String emailcheckedupdate(String userID) throws Exception {
		String msg = null;
		if (dao.emailcheckedupdate(userID) == 1) {
			msg = "메일 인증이 완료 됐습니다.";
		}
		else msg = "메일 인증에 실패 했습니다.";
		return msg;
	}

	@Override
	public String resendemail(UserDTO udto) throws Exception {
		String msg = null;
		if (udto == null) {
			msg = "임시 로그인 시간이 만료됬습니다. 다시 로그인해 주세요.";
		}
		else {
			sendemail(udto);
			msg = "다시 인증메일을 보냈습니다.";
		}
		return msg;
	}

	@Override
	public UserDTO selectone(String userID) throws Exception {
		return dao.selectone(userID);
	}

	@Transactional
	@Override
	public Map<String, String> defection(UserDTO udto) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String msg;
		String url;
		if(encode.matches(udto.getUserPasswd(), dao.selectuserPasswd(udto))) {			
			dao.defection(udto);
			msg = "회원 탈퇴 되셨습니다.";
			url = "/user/login";
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";
			url = "/user/defection";
		}
		map.put("msg", msg);
		map.put("url", url);
		return map;
	}

	@Transactional
	@Override
	public String passwdupdate(String oldPasswd, String newPasswd, String userID) throws Exception {
		UserDTO udto = dao.selectone(userID);
		String msg = null;
		if(encode.matches(oldPasswd, udto.getUserPasswd())) {
			udto.setUserPasswd(encode.encode(newPasswd));
			dao.passwdupdate(udto);
			msg = "수정완료";
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		return msg;
	}


	@Transactional
	@Override
	public Map<String,String> emailupdate(String userEmail, String userID) throws Exception {
		UserDTO udto = dao.selectone(userID);
		Map<String, String> map = new HashMap<String, String>();
		String msg;
		udto.setUserEmail(userEmail);
		if(dao.emailupdate(udto) == 1) {
			msg = "수정 완료";
			map.put("email", userEmail);
		}
		else {
			msg= "이메일 수정 실패";
		}
		map.put("msg", msg);
		return map;
	}



	
	
	
}
