package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Qtype;


public interface QtypeService {
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
