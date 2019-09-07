package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Subject;


public interface SubjectService {
	Long insert(Subject bean) throws SQLException;
	Long delete (Long id);
	Long update(Subject bean);
	List<Subject> list();
	Subject load(Long id);
	Long count();
	Subject loadName(String name);
	Long countByName(String name);
	List<Subject> listByName(String name);
	

}
