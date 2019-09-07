package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Paper;
import edu.prj.dao.PaperDao;
import edu.prj.dao.impl.PaperDaoImpl;
import edu.prj.service.PaperService;

public class PaperServiceImpl implements PaperService{

	 PaperDao PaperDao = new PaperDaoImpl();
	
		@Override
		public Long insert(Paper bean) throws SQLException {
			// TODO Auto-generated method stub
			return PaperDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return PaperDao.delete(id);
		}

		@Override
		public Long update(Paper bean) {
			// TODO Auto-generated method stub
			return PaperDao.update(bean);
		}

		@Override
		public List<Paper> list() {
			// TODO Auto-generated method stub
			return PaperDao.list();
		}

		@Override
		public Paper load(Long id) {
			// TODO Auto-generated method stub
			return PaperDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return PaperDao.count();
		}

		@Override
		public Paper loadName(String name) {
			// TODO Auto-generated method stub
			return PaperDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return PaperDao.countByName(name);
		}

		@Override
		public List<Paper> listByName(String name) {
			// TODO Auto-generated method stub
			return PaperDao.listByName(name);
		}

		@Override
		public Long update1(Integer id) {
			// TODO Auto-generated method stub
			return PaperDao.update1(id);
		}


}
