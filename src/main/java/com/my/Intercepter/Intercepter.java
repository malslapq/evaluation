package com.my.Intercepter;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.DTO.PageDTO;

public class Intercepter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userID");
		PageDTO pdto = (PageDTO) session.getAttribute("pdto");
		if(userid == null || pdto == null) {
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('다시 로그인 해 주시길 바랍니다.'); location.href='/assessment/user/loginform';</script>");
			out.flush();
			out.close();
			return false;
		}
		return true;
	}

	
}
