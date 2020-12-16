package com.my.Controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.DTO.PageDTO;
import com.my.DTO.UserDTO;
import com.my.Service.UserService;

@RequestMapping(value = "/user")
@Controller()
public class UserController {
	
	@Resource
	private UserService Service;
	
	@RequestMapping(value = "/index")
	public void index() {
	}
	
	@RequestMapping(value = "/loginform")
	public String loginform(UserDTO udto) {
		return "/user/login";
	}
	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "/user/join";
	}
	@RequestMapping(value = "/defectionform")
	public String defectionform(HttpSession session, Model model) throws Exception {
		String userID = (String)session.getAttribute("userID");
		model.addAttribute("udto",Service.selectone(userID));
		return "/user/defection";
	}
	
	@RequestMapping(value = "/updateform")
	public String updateform(HttpSession session, Model model) throws Exception {
		String userID = (String)session.getAttribute("userID");
		model.addAttribute("udto",Service.selectone(userID));
		return "/user/update";
	}
	
	@RequestMapping(value = "/passwdupdate", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> passwdupdate(HttpSession session, String olduserPasswd, String newuserPasswd) throws Exception {
		String msg = Service.passwdupdate(olduserPasswd, newuserPasswd, (String) session.getAttribute("userID"));
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/emailupdate", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> emailupdate(HttpSession session, String userEmail) throws Exception {
		Map<String,String> map = Service.emailupdate(userEmail, (String) session.getAttribute("userID"));
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/defection")
	public String defection(UserDTO udto, Model model) throws Exception {
		Map<String, String> map = Service.defection(udto);
		String msg = map.get("msg");
		String url = map.get("url");
		model.addAttribute("msg",msg);
		return url;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO udto, Model model, HttpSession session) throws Exception {
		Map<String, Object> map = Service.login(udto);
		int result = (Integer) map.get("result");
		if (result == 1) {
			PageDTO pdto = new PageDTO();
			session.removeAttribute("temporarilyudto");
			session.setAttribute("userID", udto.getUserID());
			session.setAttribute("pdto", pdto);
		}
		if (result == 3) {
			udto.setUserEmail((String) map.get("useremail"));
			session.setAttribute("temporarilyudto", udto);
		}
		model.addAttribute("msg", map.get("msg"));
		return (String) map.get("url");
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		session.removeAttribute("userID");
		session.removeAttribute("pdto");
		session.invalidate();
		String msg = "로그아웃되었습니다.";
		model.addAttribute("msg", msg);
		return "/user/login";
	}
	
	@RequestMapping(value = "/idcheck/{userid}", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> idcheck(@PathVariable("userid") String userID) throws Exception {
		String msg = Service.idcheck(userID);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	public String join(UserDTO udto, Model model, HttpSession session) throws Exception {
		Map<String,String> map = Service.join(udto);
		session.setAttribute("temporarilyudto", udto);
		session.setMaxInactiveInterval(60*3);
		model.addAttribute("msg",map.get("msg"));
		return map.get("url");
	}
	
	@RequestMapping(value = "/emailconfirm")
	public String mailconfirm(String userID, Model model) throws Exception {
		String msg = Service.emailcheckedupdate(userID);
		model.addAttribute("msg", msg);
		return "/user/login";
	}
	
	@RequestMapping(value = "/Resendemail", produces="application/text; charset=utf-8")
	public ResponseEntity<String> resendemail(HttpSession session) throws Exception{
		UserDTO udto = (UserDTO) session.getAttribute("temporarilyudto");
		String msg = Service.resendemail(udto);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sessioncheck", produces="application/text; charset=utf-8")
	public ResponseEntity<String> sessioncheck(HttpSession session) {
		return new ResponseEntity<String>((String)session.getAttribute("userID"), HttpStatus.OK);
	}
	
}
