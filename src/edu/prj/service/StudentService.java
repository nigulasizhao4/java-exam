package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Student;


public interface StudentService {
	Long insert(Student bean) throws SQLException;
	Long delete (Long id);
	Long update(Student bean);
	List<Student> list();
	Student load(Long id);
	Long count();
	Student loadName(String name);
	Long countByName(String name);
	List<Student> listByName(String name);
	boolean login(String userName, String userPass);

}
