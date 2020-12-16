package com.my.DAO;



import java.util.List;

import com.my.DTO.EvaluationDTO;
import com.my.DTO.LikeyDTO;
import com.my.DTO.PageDTO;
import com.my.DTO.ProfessorDTO;

public interface EvaluationDAO {
	public int insert(EvaluationDTO edto) throws Exception;
	public int selectTotContent(PageDTO pdto) throws Exception;
	public List<EvaluationDTO> selectList(PageDTO pdto) throws Exception;
	public int likeycheck(LikeyDTO ldto) throws Exception;
	public int likeyinsert(LikeyDTO ldto) throws Exception;
	public int likeydelete(LikeyDTO ldto) throws Exception;
	public int likecntup(int evaluationid) throws Exception;
	public int likecntdown(int evaluationid) throws Exception;
	public int delete(int evaluationid) throws Exception;
	public EvaluationDTO selectone(int evaluationid) throws Exception;
	public List<ProfessorDTO> selectlectureName() throws Exception;
	public List<ProfessorDTO> selectprofessor(String lectureName) throws Exception;
	public int professorevaluationup(EvaluationDTO edto) throws Exception;
	public int professorevaluationdown(EvaluationDTO edto) throws Exception;
	public List<ProfessorDTO> selectprofessordetail(String lectureName) throws Exception;
	public int addprofessor(ProfessorDTO pfdto) throws Exception; 
	public int deleteprofessor(ProfessorDTO pfdto) throws Exception;
	public int deletEvaluationProfessor(String professorName) throws Exception;
	public int deleteLikeyProfessor(String professorName) throws Exception;
	public int deletelikeylecture(String lecturename) throws Exception;
	public int deleteevaluationlecture(String lecturename) throws Exception;
	public int deleteprofessorlecture(String lecturename) throws Exception;
}
