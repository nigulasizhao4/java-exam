package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Exam;
import edu.prj.bean.Exam;
import edu.prj.dao.ExamDao;
import edu.prj.dao.impl.ExamDaoImpl;
import edu.prj.service.ExamService;

public class ExamServiceImpl implements ExamService{

	 ExamDao examDao = new ExamDaoImpl();
		@Override
		public Long insert(Exam bean) throws SQLException {
			// TODO Auto-generated method stub
			return examDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return examDao.delete(id);
		}

		@Override
		public Long update(Exam bean) {
			// TODO Auto-generated method stub
			return examDao.update(bean);
		}

		@Override
		public List<Exam> list() {
			// TODO Auto-generated method stub
			return examDao.list();
		}

		@Override
		public Exam load(Long id) {
			// TODO Auto-generated method stub
			return examDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return examDao.count();
		}

		@Override
		public Exam loadName(String name) {
			// TODO Auto-generated method stub
			return examDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return examDao.countByName(name);
		}

		@Override
		public List<Exam> listByName(String name) {
			// TODO Auto-generated method stub
			return examDao.listByName(name);
		}

		@Override
		public Integer idByName(String name) {
			// TODO Auto-generated method stub
			return examDao.idByName(name);
		}

		@Override
		public Exam idByName1(String name, Integer id) {
			// TODO Auto-generated method stub
			return examDao.idByName1(name, id);
		}


}
