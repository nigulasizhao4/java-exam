package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Question;


public interface QuestionService {
	Long insert(Question bean) throws SQLException;
	Long delete (Long id);
	Long update(Question bean);
	List<Question> list();
	Question load(Long id);
	Long count();
	Question loadName(String name);
	Long countByName(String name);
	List<Question> listByName(String name);
	List<Question> listByQtype(Integer type,Integer subjectid);
	

}
