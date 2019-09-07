package edu.prj.ui;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.WatchEvent;

import javax.swing.*;

import edu.prj.bean.*;

import edu.prj.ui.manager.*;
import edu.prj.ui.manager.classroom.ClassRoomFrm;
import edu.prj.ui.manager.grade.GradeFrm;
import edu.prj.ui.manager.manager.ManagerFrm;
import edu.prj.ui.manager.student.StudentFrm;
import edu.prj.ui.manager.subject.SubjectFrm;
import edu.prj.ui.manager.teacher.TeacherFrm;





public class ManagerMainFrm extends JFrame implements ActionListener{
	/**
	 * 
	 */
	LoginFrm longinFrm=new LoginFrm();
    JMenu person = new JMenu("人员管理(S)");
    JMenu school = new JMenu("配置管理(B)");
    JMenu help = new JMenu("帮助(H)");
    JMenuItem student = new JMenuItem("学生管理S");
    JMenuItem classRoom = new JMenuItem("教室管理R");
    JMenuItem teacher = new JMenuItem("教师管理T");
    JMenuItem manager = new JMenuItem("管理员管理M");
    JMenuItem change = new JMenuItem("修改密码C");
    JMenuItem grade = new JMenuItem("年级管理G");
    JMenuItem subject = new JMenuItem("科目管理U");
    JMenuItem qute= new JMenuItem("注销Q");
    JMenuItem exit = new JMenuItem("退出E");
    JMenuItem about = new JMenuItem("关于");
    JMenuItem help1 = new JMenuItem("帮助");
    JPanel container = new JPanel();;
	JMenuBar menuBar = new JMenuBar();	
	JTextArea text = new JTextArea();

	private static final long serialVersionUID = 1L;
    private String name;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Person person2;
	public ManagerMainFrm(String name,Person person) {
		setName(name);
		setPerson2(person);
		initUI();
		bindEvent();
	}
	  @Override
	    public void actionPerformed(ActionEvent e) {
		  if (e.getSource() == about) {
	            new About();
	        }
		  if (e.getSource() == exit) {
	            System.exit(0);
	        }
		  if (e.getSource() == qute) {
			  setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				int option= JOptionPane.showConfirmDialog(this,"确定注销系统？","提示",JOptionPane.YES_NO_OPTION);		
				if(option==JOptionPane.YES_OPTION) {
						if(longinFrm!=null) {
							this.dispose();
							longinFrm.setVisible(true);
							
						}
				}
	        }
		  if (e.getSource() == change) {
	            ChangeFrm frm = new ChangeFrm(name,person2);
	        }
		  if (e.getSource() == student) {
			  StudentFrm studentFrm = new StudentFrm();
		  }
	  
		  if (e.getSource() == classRoom) {
			  ClassRoomFrm  classRoomFrm = new ClassRoomFrm();
		  }
		  if (e.getSource() == teacher) {
			  TeacherFrm  teacherFrm = new TeacherFrm();
		  }
		  if (e.getSource() == manager) {
			  ManagerFrm  managerFrm = new  ManagerFrm();
		  }
		  if (e.getSource() == grade) {
			  GradeFrm  gradeFrm = new  GradeFrm(getName());
		  }
		  if (e.getSource() == subject) {
			  SubjectFrm  subjectFrm = new  SubjectFrm();
		  }
		  
}
//
//		  
//	  }
//
	private void bindEvent() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}

		});
	}
	public void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		int option= JOptionPane.showConfirmDialog(this,"确定注销系统？","提示",JOptionPane.YES_NO_OPTION);		
		if(option==JOptionPane.YES_OPTION) {
			if(e.getWindow()==this) {
				if(longinFrm!=null) {
					this.dispose();
					longinFrm.setVisible(true);
					
				}
				
			}
		}
		
	}

	private void initUI() {

		JPanel container = new JPanel();;
		JMenuBar menuBar = new JMenuBar();	
		JTextArea text = new JTextArea();

    	  setTitle("管理员管理系统,你好"+getName());
          int width=1000;
          int height=800;
          setSize(width,height);
          
          int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
          int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
          setLocation(x, y);
          container.setLayout(null);
  		  this.add(container);
  		  this.setJMenuBar(menuBar);

          
          person.setMnemonic(KeyEvent.VK_S);
          school.setMnemonic(KeyEvent.VK_B);
          help.setMnemonic(KeyEvent.VK_H);
          
          student.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
          classRoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
          teacher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
          manager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
          change.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
          qute.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
          exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
          grade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

          person.add(student);
          person.add(teacher);
          person.add(manager);
          person.add(change);
          person.add(qute);
          person.add(exit);
     
          school.add(classRoom);
          school.add(grade);
          
          help.add(about);
          help.add(help1);
          menuBar.add(person);
          menuBar.add(school);
          menuBar.add(help);
          setJMenuBar(menuBar);
          
          about.addActionListener(this);
          exit.addActionListener(this);
          student.addActionListener(this);
          classRoom.addActionListener(this);
          teacher.addActionListener(this);
          manager.addActionListener(this);
          change.addActionListener(this);
          grade.addActionListener(this);
          subject.addActionListener(this);
          qute.addActionListener(this);

		
	}
	public Person getPerson2() {
		return person2;
	}
	public void setPerson2(Person person2) {
		this.person2 = person2;
	}
	class About extends JDialog {// 关于窗口
	    About() {
	        JOptionPane.showMessageDialog(null, "            作者:嘿嘿         版本：v1.5\n\n            联系：1231231231", "关于",
	                JOptionPane.PLAIN_MESSAGE);
	    }
	}

}
