package com.my.Service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.DAO.EvaluationDAO;
import com.my.DTO.EvaluationDTO;
import com.my.DTO.LikeyDTO;
import com.my.DTO.PageDTO;
import com.my.DTO.ProfessorDTO;

@Service
public class EvaluationServiceimple implements EvaluationService{
	
	@Autowired 
	private JavaMailSenderImpl mailSender;
	
	@Resource
	private EvaluationDAO dao;

	@Override
	public String insert(EvaluationDTO edto) throws Exception {
		String msg = null;
		if(dao.insert(edto) == 1) {
			dao.professorevaluationup(edto);
			msg = "등록성공";
		}
		return msg;
	}

	@Override
	public PageDTO setPage(PageDTO pdto) throws Exception {
		pdto.pageset(dao.selectTotContent(pdto));
		return pdto;
	}


	@Override
	public void sendreport(String userid, Map<String, Object> map) throws Exception {
		String reporttitle = (String)map.get("reporttitle");
		String reportcontent = (String)map.get("reportcontent");
		map.get("reportcontent");
		String text = 
				"<h2>강의평가 웹사이트 신고</h2>"+
				"신고자 : "+userid+"님이 신고하신 내용입니다."+
				"<ol><h3>신고 제목 : "+reporttitle+"</h3></ol>"+
				"<li>신고 내용 : "+reportcontent+"</li>";
		final MimeMessagePreparator preparetor = new MimeMessagePreparator() {
					
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom("malslapq92@naver.com", "강의평가 웹사이트");
				helper.setTo("malslapq92@naver.com");
				helper.setSubject("강의평가 웹사이트 신고문건");
				helper.setText(text, true);
				}
			};
		mailSender.send(preparetor);
	}

	@Override
	public List<EvaluationDTO> selectList(PageDTO pdto) throws Exception {
		if(pdto.getSearchcurPage() != 0) {
			pdto.setCurPage(pdto.getSearchcurPage());
		}
		if(pdto.getKey() == "") {
			pdto.setKey(null);
		}
		List<EvaluationDTO> list = dao.selectList(setPage(pdto));
		return list;
	}
	
	@Transactional
	@Override
	public String likeyupdate(LikeyDTO ldto) throws Exception {
		int evaluationid = ldto.getEvaluationid();
		if(dao.likeycheck(ldto) == 0) {
			dao.likeyinsert(ldto);
			dao.likecntup(evaluationid);
		}
		else {
			dao.likeydelete(ldto);
			dao.likecntdown(evaluationid);
		}
		EvaluationDTO edto = dao.selectone(evaluationid);
		String likecnt = Integer.toString(edto.getLikecount()); 
		return likecnt;
	}
	
	@Transactional
	@Override
	public String delete(int evaluationid, String userid) throws Exception {
		EvaluationDTO edto = dao.selectone(evaluationid);
		String msg;
		if(edto != null && (edto.getUserid().equals(userid) || userid.equals("root"))) {
			LikeyDTO ldto = new LikeyDTO();
			ldto.setUserid(userid);
			ldto.setEvaluationid(evaluationid);
			dao.likeydelete(ldto);
			dao.delete(evaluationid);
			dao.professorevaluationdown(edto);
			msg = "삭제 완료";
		}
		else {
			msg = "작성자가 아닙니다.";
		}
		return msg;
	}

	@Override
	public List<ProfessorDTO> selectlectureName() throws Exception {
		return dao.selectlectureName();
	}

	@Override
	public List<ProfessorDTO> selectprofessor(String lectureName) throws Exception {
		return dao.selectprofessor(lectureName);
	}

	@Override
	public List<ProfessorDTO> selectprofessordetail(String lectureName) throws Exception {
		return dao.selectprofessordetail(lectureName);
	}

	@Override
	public String addprofessor(ProfessorDTO pfdto) throws Exception {
		String msg;
		if(dao.addprofessor(pfdto) == 1) {
			msg = "등록 성공";
		}
		else msg = "등록 실패";
		return msg;
	}

	@Transactional
	@Override
	public String deleteprofessor(ProfessorDTO pfdto) throws Exception {
		String msg;
		dao.deleteLikeyProfessor(pfdto.getProfessorname());
		dao.deletEvaluationProfessor(pfdto.getProfessorname());
		if(dao.deleteprofessor(pfdto) == 1) {
			msg = "삭제 성공";
		}
		else msg = "삭제 실패";
		return msg;
	}

	@Transactional
	@Override
	public String deletelecture(String lecturename) throws Exception {
		String msg;
		dao.deletelikeylecture(lecturename);
		dao.deleteevaluationlecture(lecturename);
		if (dao.deleteprofessorlecture(lecturename) >= 1) {
			msg = "삭제 성공";
		}
		else msg = "삭제 실패";
		return msg;
	}
	

	
}
