package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.dao.TeacherDao;
import edu.prj.dao.impl.TeacherDaoImpl;
import edu.prj.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	 TeacherDao teacherDao = new TeacherDaoImpl();
	 @Override
	 public boolean login(String username, String password) {
			Student student =new Student();
			student.setLoginname(username);
			student.setLoginpass(password);
			if(loadName(username)!=null&&loadName(username).getLoginpass().equals(password)&&loadName(username).getIsdisabled()!=1) {
				return true;
			}
			return false;
		}
		@Override
		public Long insert(Teacher bean) throws SQLException {
			// TODO Auto-generated method stub
			return teacherDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return teacherDao.delete(id);
		}

		@Override
		public Long update(Teacher bean) {
			// TODO Auto-generated method stub
			return teacherDao.update(bean);
		}

		@Override
		public List<Teacher> list() {
			// TODO Auto-generated method stub
			return teacherDao.list();
		}

		@Override
		public Teacher load(Long id) {
			// TODO Auto-generated method stub
			return teacherDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return teacherDao.count();
		}

		@Override
		public Teacher loadName(String name) {
			// TODO Auto-generated method stub
			return teacherDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return teacherDao.countByName(name);
		}

		@Override
		public List<Teacher> listByName(String name) {
			// TODO Auto-generated method stub
			return teacherDao.listByName(name);
		}


}

