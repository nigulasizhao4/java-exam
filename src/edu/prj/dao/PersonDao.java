package edu.prj.dao;

import edu.prj.bean.Person;
import edu.prj.bean.Student;

public interface PersonDao {
	 Long changePass(Person person);
	 Person loadName(String name,Person person);

}
