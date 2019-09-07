package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Grade;


public interface GradeService {
	Long insert(Grade bean) throws SQLException;
	Long delete (Long id);
	Long update(Grade bean);
	List<Grade> list();
	Grade load(Long id);
	Long count();
	Grade loadName(String name);
	Long countByName(String name);
	List<Grade> listByName(String name);
	

}
