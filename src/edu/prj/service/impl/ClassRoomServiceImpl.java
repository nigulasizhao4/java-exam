package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.ClassRoom;
import edu.prj.dao.ClassRoomDao;
import edu.prj.dao.impl.ClassRoomDaoImpl;
import edu.prj.service.ClassRoomService;

public class ClassRoomServiceImpl implements ClassRoomService{

	 ClassRoomDao classRoomDao = new ClassRoomDaoImpl();
	
		@Override
		public Long insert(ClassRoom bean) throws SQLException {
			// TODO Auto-generated method stub
			return classRoomDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return classRoomDao.delete(id);
		}

		@Override
		public Long update(ClassRoom bean) {
			// TODO Auto-generated method stub
			return classRoomDao.update(bean);
		}

		@Override
		public List<ClassRoom> list() {
			// TODO Auto-generated method stub
			return classRoomDao.list();
		}

		@Override
		public ClassRoom load(Long id) {
			// TODO Auto-generated method stub
			return classRoomDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return classRoomDao.count();
		}

		@Override
		public ClassRoom loadName(String name) {
			// TODO Auto-generated method stub
			return classRoomDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return classRoomDao.countByName(name);
		}

		@Override
		public List<ClassRoom> listByName(String name) {
			// TODO Auto-generated method stub
			return classRoomDao.listByName(name);
		}


}
