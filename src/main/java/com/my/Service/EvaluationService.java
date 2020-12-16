package com.my.Service;


import java.util.List;
import java.util.Map;

import com.my.DTO.EvaluationDTO;
import com.my.DTO.LikeyDTO;
import com.my.DTO.PageDTO;
import com.my.DTO.ProfessorDTO;

public interface EvaluationService {
	public String insert(EvaluationDTO edto) throws Exception;
	public PageDTO setPage(PageDTO pdto) throws Exception;
	public void sendreport(String userid, Map<String, Object> map) throws Exception;
	public List<EvaluationDTO> selectList(PageDTO pdto) throws Exception;
	public String likeyupdate(LikeyDTO ldto) throws Exception;
	public String delete(int evaluationid, String userid)throws Exception;
	public List<ProfessorDTO> selectlectureName() throws Exception;
	public List<ProfessorDTO> selectprofessor(String lectureName) throws Exception;
	public List<ProfessorDTO> selectprofessordetail(String lectureName) throws Exception;
	public String addprofessor(ProfessorDTO pfdto) throws Exception;
	public String deleteprofessor(ProfessorDTO pfdto) throws Exception;
	public String deletelecture(String lecturename) throws Exception;
}
