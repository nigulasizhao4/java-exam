package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Student;
import edu.prj.bean.Student;
import edu.prj.dao.StudentDao;
import edu.prj.dao.impl.StudentDaoImpl;
import edu.prj.service.StudentService;

public class StudentServiceImpl implements StudentService{
    StudentDao studentDao = new StudentDaoImpl();
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
	public Long insert(Student bean) throws SQLException {
		// TODO Auto-generated method stub
		return studentDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return studentDao.delete(id);
	}

	@Override
	public Long update(Student bean) {
		// TODO Auto-generated method stub
		return studentDao.update(bean);
	}

	@Override
	public List<Student> list() {
		// TODO Auto-generated method stub
		return studentDao.list();
	}

	@Override
	public Student load(Long id) {
		// TODO Auto-generated method stub
		return studentDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return studentDao.count();
	}

	@Override
	public Student loadName(String name) {
		// TODO Auto-generated method stub
		return studentDao.loadName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.countByName(name);
	}

	@Override
	public List<Student> listByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.listByName(name);
	}

}
