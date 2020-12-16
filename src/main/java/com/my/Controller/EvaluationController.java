package com.my.Controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.DTO.EvaluationDTO;
import com.my.DTO.LikeyDTO;
import com.my.DTO.PageDTO;
import com.my.DTO.ProfessorDTO;
import com.my.Service.EvaluationService;

@SessionAttributes("pdto")
@RequestMapping(value = "/evaluation")
@Controller
public class EvaluationController {
	
	@Resource
	private EvaluationService Service;
	
	@RequestMapping(value = "/{userid}", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> insert(@RequestBody EvaluationDTO edto) throws Exception {
		String msg = Service.insert(edto);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> sendreport(HttpSession session,@RequestBody Map<String, Object> map) throws Exception{
		String userid = (String)session.getAttribute("userID");
		Service.sendreport(userid, map);
		String msg = "신고 완료"; 
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/index")
	public void selectList(@ModelAttribute("pdto") PageDTO pdto, Model model) throws Exception{
		List<EvaluationDTO> list = Service.selectList(pdto);
		List<ProfessorDTO> lnlist = Service.selectlectureName();
		model.addAttribute("list", list);
		model.addAttribute("lnlist",lnlist);
	}
	
	@RequestMapping(value = "/likeyupdate", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	public ResponseEntity<Map<String, Object>> likeyupdate(HttpSession session, HttpServletRequest request,@RequestBody LikeyDTO ldto, Model model) throws Exception{
		ldto.setUserid((String)session.getAttribute("userID"));
		ldto.setUserip(request.getRemoteAddr());
		String likecnt = Service.likeyupdate(ldto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("likecnt", likecnt);
		map.put("evaluationid", ldto.getEvaluationid());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, HttpSession session, int evaluationid) throws Exception {
		String msg = Service.delete(evaluationid, (String)session.getAttribute("userID"));
		model.addAttribute("list", Service.selectList((PageDTO)session.getAttribute("pdto")));
		model.addAttribute("msg",msg);
		return "/evaluation/index";
	}
	
	@RequestMapping(value = "/selectprofessorname", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	public ResponseEntity<List<ProfessorDTO>> selectprofessor(String lectureName) throws Exception{
		List<ProfessorDTO> pnlist = Service.selectprofessor(lectureName);
		return new ResponseEntity<List<ProfessorDTO>>(pnlist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lectureaddform")
	public String lectureaddform(Model model) throws Exception {
		List<ProfessorDTO> lnlist = Service.selectlectureName();
		model.addAttribute("lnlist", lnlist);
		return "/evaluation/lectureadd";
	}
	
	@RequestMapping(value = "/professordetail", method = RequestMethod.POST)
	public ResponseEntity<List<ProfessorDTO>> professordetail(String lectureName) throws Exception{
		List<ProfessorDTO> plist = Service.selectprofessordetail(lectureName);
		return new ResponseEntity<List<ProfessorDTO>>(plist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addprofessor")
	public String addprofessor(ProfessorDTO pfdto, Model model) throws Exception {
		String msg = Service.addprofessor(pfdto);
		List<ProfessorDTO> lnlist = Service.selectlectureName();
		model.addAttribute("lnlist", lnlist);
		model.addAttribute("msg",msg);
		return "/evaluation/lectureadd";
	}
	
	@RequestMapping(value = "/deleteprofessor")
	public String deleteprofessor (ProfessorDTO pfdto, Model model) throws Exception {
		String msg = Service.deleteprofessor(pfdto);
		List<ProfessorDTO> lnlist = Service.selectlectureName();
		model.addAttribute("lnlist", lnlist);
		model.addAttribute("msg", msg);
		return "/evaluation/lectureadd";
	}
	
	@RequestMapping(value = "/deletelecture")
	public String deletelecture(Model model, String lecturename) throws Exception {
		String msg = Service.deletelecture(lecturename);
		List<ProfessorDTO> lnlist = Service.selectlectureName();
		model.addAttribute("lnlist", lnlist);
		model.addAttribute("msg", msg);
		return "/evaluation/lectureadd";
	}
}
