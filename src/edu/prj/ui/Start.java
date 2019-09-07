package edu.prj.ui;

import edu.prj.bean.Manager;
import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.ui.manager.grade.GradeFrm;
import edu.prj.ui.manager.manager.ManagerFrm;
import edu.prj.ui.manager.student.StudentFrm;
import edu.prj.ui.manager.subject.SubjectFrm;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginFrm frm = new LoginFrm();
//		StudentFrm frm = new StudentFrm();
//		Manager manager = new Manager();
//		ManagerMainFrm frm = new ManagerMainFrm("admin",manager);
//		ManagerFrm frm = new ManagerFrm();
//		GradeFrm frm = new GradeFrm("admin");
//		SubjectFrm frm = new SubjectFrm();
//		Teacher teacher = new Teacher();
//		TeacherMainFrm frm = new TeacherMainFrm("123", teacher);
//		Student student = new Student();
//		StudentMainFrm frm = new StudentMainFrm("123", student);
		frm.setVisible(true);

	}

}
