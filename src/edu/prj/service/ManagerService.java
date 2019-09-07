package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Manager;


public interface ManagerService {
	Long insert(Manager bean) throws SQLException;
	Long delete (Long id);
	Long update(Manager bean);
	List<Manager> list();
	Manager load(Long id);
	Long count();
	Manager loadName(String name);
	Long countByName(String name);
	List<Manager> listByName(String name);
	boolean login(String username, String password);
	

}
