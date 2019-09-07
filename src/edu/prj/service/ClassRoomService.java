package edu.prj.service;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.ClassRoom;


public interface ClassRoomService {
	Long insert(ClassRoom bean) throws SQLException;
	Long delete (Long id);
	Long update(ClassRoom bean);
	List<ClassRoom> list();
	ClassRoom load(Long id);
	Long count();
	ClassRoom loadName(String name);
	Long countByName(String name);
	List<ClassRoom> listByName(String name);
	

}
