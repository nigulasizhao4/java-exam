package edu.prj.service.impl;

import java.sql.SQLException;
import java.util.List;

import edu.prj.bean.Manager;
import edu.prj.bean.Manager;
import edu.prj.dao.ManagerDao;
import edu.prj.dao.impl.ManagerDaoImpl;
import edu.prj.service.ManagerService;

public class ManagerServiceImpl implements ManagerService{

	 ManagerDao managerDao = new ManagerDaoImpl();
	 @Override
		public boolean login(String username, String password) {
			Manager manager =new Manager();
			manager.setLoginname(username);
			manager.setLoginpass(password);
			if(loadName(username)!=null&&loadName(username).getLoginpass().equals(password)&&loadName(username).getIsdisabled()!=1) {
				return true;
			}
			return false;
		}
		@Override
		public Long insert(Manager bean) throws SQLException {
			// TODO Auto-generated method stub
			return managerDao.insert(bean);
		}

		@Override
		public Long delete(Long id) {
			// TODO Auto-generated method stub
			return managerDao.delete(id);
		}

		@Override
		public Long update(Manager bean) {
			// TODO Auto-generated method stub
			return managerDao.update(bean);
		}

		@Override
		public List<Manager> list() {
			// TODO Auto-generated method stub
			return managerDao.list();
		}

		@Override
		public Manager load(Long id) {
			// TODO Auto-generated method stub
			return managerDao.load(id);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			return managerDao.count();
		}

		@Override
		public Manager loadName(String name) {
			// TODO Auto-generated method stub
			return managerDao.loadName(name);
		}

		@Override
		public Long countByName(String name) {
			// TODO Auto-generated method stub
			return managerDao.countByName(name);
		}

		@Override
		public List<Manager> listByName(String name) {
			// TODO Auto-generated method stub
			return managerDao.listByName(name);
		}


}
