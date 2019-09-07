package edu.prj.dao;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Teacher;


public interface TeacherDao {
	Long insert(Teacher bean) throws SQLException;
	Long delete (Long id);
	Long update(Teacher bean);
	List<Teacher> list();
	Teacher load(Long id);
	Long count();
	Teacher loadName(String name);
	Long countByName(String name);
	List<Teacher> listByName(String name);
	

}
