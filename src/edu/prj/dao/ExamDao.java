package edu.prj.dao;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Exam;


public interface ExamDao {
	Long insert(Exam bean) throws SQLException;
	Long delete (Long id);
	Long update(Exam bean);
	List<Exam> list();
	Exam load(Long id);
	Long count();
	Exam loadName(String name);
	Long countByName(String name);
	List<Exam> listByName(String name);
	Integer idByName(String name);
	Exam idByName1(String name,Integer id);
	

}
