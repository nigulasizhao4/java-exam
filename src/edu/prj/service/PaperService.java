package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Paper;


public interface PaperService {
	Long insert(Paper bean) throws SQLException;
	Long delete (Long id);
	Long update(Paper bean);
	List<Paper> list();
	Paper load(Long id);
	Long count();
	Paper loadName(String name);
	Long countByName(String name);
	List<Paper> listByName(String name);
	Long update1(Integer id);
	

}
