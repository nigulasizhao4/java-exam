package edu.prj.dao;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.PaperItem;


public interface PaperItemDao {
	Long insert(PaperItem bean) throws SQLException;
	Long delete (Long id);
	List<PaperItem> list();
	PaperItem load(Long id);
	Long count();
	PaperItem loadName(String name);
	Long countByName(String name);
	List<PaperItem> listByPId(Integer id);
	

}
