package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Qtype;
import edu.prj.dao.QtypeDao;
import edu.prj.dao.impl.QtypeDaoImpl;
import edu.prj.service.QtypeService;

public class QtypeServiceImpl implements QtypeService{

	 QtypeDao qtypeDao = new QtypeDaoImpl();
	
		@Override
		public Long insert(Qtype bean) throws SQLException {
			// TODO Auto-generated method stub
			return qtypeDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return qtypeDao.delete(id);
		}

		@Override
		public Long update(Qtype bean) {
			// TODO Auto-generated method stub
			return qtypeDao.update(bean);
		}

		@Override
		public List<Qtype> list() {
			// TODO Auto-generated method stub
			return qtypeDao.list();
		}

		@Override
		public Qtype load(Long id) {
			// TODO Auto-generated method stub
			return qtypeDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return qtypeDao.count();
		}

		@Override
		public Qtype loadName(String name) {
			// TODO Auto-generated method stub
			return qtypeDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return qtypeDao.countByName(name);
		}

		@Override
		public List<Qtype> listByName(String name) {
			// TODO Auto-generated method stub
			return qtypeDao.listByName(name);
		}


}
