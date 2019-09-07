package edu.prj.dao;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Qtype;


public interface QtypeDao {
	Long insert(Qtype bean) throws SQLException;
	Long delete (Long id);
	Long update(Qtype bean);
	List<Qtype> list();
	Qtype load(Long id);
	Long count();
	Qtype loadName(String name);
	Long countByName(String name);
	List<Qtype> listByName(String name);
	

}
