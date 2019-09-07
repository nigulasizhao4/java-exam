package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.ExamItem;
import edu.prj.bean.ExamItem;
import edu.prj.dao.ExamItemDao;
import edu.prj.dao.impl.ExamItemDaoImpl;
import edu.prj.service.ExamItemService;

public class ExamItemServiceImpl implements ExamItemService{

	 ExamItemDao examItemDao = new ExamItemDaoImpl();
		@Override
		public Long insert(ExamItem bean) throws SQLException {
			// TODO Auto-generated method stub
			return examItemDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return examItemDao.delete(id);
		}

	

		@Override
		public List<ExamItem> list() {
			// TODO Auto-generated method stub
			return examItemDao.list();
		}

		@Override
		public ExamItem load(Long id) {
			// TODO Auto-generated method stub
			return examItemDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return examItemDao.count();
		}

		@Override
		public ExamItem loadName(String name) {
			// TODO Auto-generated method stub
			return examItemDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return examItemDao.countByName(name);
		}

		@Override
		public List<ExamItem> listById(Integer id) {
			// TODO Auto-generated method stub
			return examItemDao.listById(id);
		}


}
