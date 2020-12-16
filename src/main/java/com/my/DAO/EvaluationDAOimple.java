package com.my.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.my.DTO.EvaluationDTO;
import com.my.DTO.LikeyDTO;
import com.my.DTO.PageDTO;
import com.my.DTO.ProfessorDTO;


@Repository
public class EvaluationDAOimple implements EvaluationDAO{

	@Resource
	SqlSession session;
	
	@Resource
	SqlSessionFactory ssf;

	@Override
	public int insert(EvaluationDTO edto) throws Exception {
		return session.insert("EvaluationMapper.insert", edto);
	}

	@Override
	public int selectTotContent(PageDTO pdto) throws Exception {
		return session.selectOne("EvaluationMapper.selectTotContent", pdto);
	}

	@Override
	public List<EvaluationDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("EvaluationMapper.selectList", pdto);
	}

	@Override
	public int likeycheck(LikeyDTO ldto) throws Exception {
		return session.selectOne("EvaluationMapper.likeycheck", ldto);
	}

	@Override
	public int likeyinsert(LikeyDTO ldto) throws Exception {
		return session.insert("EvaluationMapper.likeyinsert", ldto);
	}

	@Override
	public int likeydelete(LikeyDTO ldto) throws Exception {
		return session.delete("EvaluationMapper.likeydelete", ldto);
	}

	@Override
	public int likecntup(int evaluationid) throws Exception {
		return session.update("EvaluationMapper.likecntup", evaluationid);
	}

	@Override
	public int likecntdown(int evaluationid) throws Exception {
		return session.update("EvaluationMapper.likecntdown", evaluationid);
	}

	@Override
	public int delete(int evaluationid) throws Exception {
		return session.delete("EvaluationMapper.evaluationdelete",evaluationid);
	}

	@Override
	public EvaluationDTO selectone(int evaluationid) throws Exception {
		return session.selectOne("EvaluationMapper.selectone", evaluationid);
	}

	@Override
	public List<ProfessorDTO> selectlectureName() throws Exception {
		return session.selectList("EvaluationMapper.selectlectureName");
	}

	@Override
	public List<ProfessorDTO> selectprofessor(String lectureName) throws Exception {
		return session.selectList("EvaluationMapper.selectleprofessorname", lectureName);
	}

	@Override
	public int professorevaluationup(EvaluationDTO edto) throws Exception {
		return session.update("EvaluationMapper.professorevaluationup", edto);
	}

	@Override
	public int professorevaluationdown(EvaluationDTO edto) throws Exception {
		return session.update("EvaluationMapper.professorevaluationdown", edto);
	}

	@Override
	public List<ProfessorDTO> selectprofessordetail(String lectureName) throws Exception {
		return session.selectList("EvaluationMapper.professorlist", lectureName);
	}

	@Override
	public int addprofessor(ProfessorDTO pfdto) throws Exception { 
		return session.insert("EvaluationMapper.addprofessor", pfdto);
	}

	@Override
	public int deleteprofessor(ProfessorDTO pfdto) throws Exception { 
		return session.delete("EvaluationMapper.deleteprofessor", pfdto);
	}

	@Override
	public int deletEvaluationProfessor(String professorName) throws Exception {
		return session.delete("EvaluationMapper.deletEvaluationProfessor", professorName);
	}

	@Override
	public int deleteLikeyProfessor(String professorName) throws Exception {
		return session.delete("EvaluationMapper.deleteLikeyProfessor", professorName);
	}

	@Override
	public int deletelikeylecture(String lecturename) throws Exception {
		return session.delete("EvaluationMapper.deletelikeylecture", lecturename);
	}

	@Override
	public int deleteevaluationlecture(String lecturename) throws Exception {
		return session.delete("EvaluationMapper.deleteevaluationlecture", lecturename);
	}

	@Override
	public int deleteprofessorlecture(String lecturename) throws Exception {
		return session.delete("EvaluationMapper.deleteprofessorlecture", lecturename);
	}

	
	
}
