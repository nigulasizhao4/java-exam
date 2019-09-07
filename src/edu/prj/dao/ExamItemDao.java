package edu.prj.dao;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.ExamItem;


public interface ExamItemDao {
	Long insert(ExamItem bean) throws SQLException;
	Long delete (Long id);
	List<ExamItem> list();
	ExamItem load(Long id);
	Long count();
	ExamItem loadName(String name);
	Long countByName(String name);
	List<ExamItem> listById(Integer id);
	

}
