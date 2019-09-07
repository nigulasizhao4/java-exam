package edu.prj.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import edu.prj.bean.Person;
import edu.prj.ui.teacher.mark.ExamFrm;
//import edu.prj.ui.teacher.score.ScoreFrm;
import edu.prj.ui.teacher.paper.PaperFrm;
import edu.prj.ui.teacher.qtype.QtypeFrm;
import edu.prj.ui.teacher.question.QuestionFrm;




public class TeacherMainFrm extends JFrame implements ActionListener{
	/**
	 * 
	 */
	LoginFrm longinFrm=new LoginFrm();
	JMenu system = new JMenu("系统管理(S)");
    JMenu  exam= new JMenu("考试管理(E)");
    JMenu help = new JMenu("帮助(H)");
    JMenuItem change = new JMenuItem("修改密码");
    JMenuItem question = new JMenuItem("题库管理(Q)");
    JMenuItem paper= new JMenuItem("试卷管理(P)");
    JMenuItem classify= new JMenuItem("分类管理(C)");
    JMenuItem query= new JMenuItem("成绩查询");
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
		private Person person;
	public TeacherMainFrm(String name, Person person) {
		setName(name);
		setPerson(person);
		initUI();
		//bindEvent();
	}
	  @Override
	    public void actionPerformed(ActionEvent e) {
		  if (e.getSource() == about) {
	            new About();
	        }
		  if (e.getSource() == exit) {
	            System.exit(0);
	        }
		  if (e.getSource() == change) {
	            ChangeFrm frm = new ChangeFrm(name,person);
	        }
		  if (e.getSource() == question) {
	            QuestionFrm frm = new QuestionFrm();
	        }
		  if (e.getSource() == classify) {
	            QtypeFrm frm = new QtypeFrm();
	        }
		  if (e.getSource() == paper) {
	            PaperFrm frm = new PaperFrm();
	        }
		  if (e.getSource() == query) {
	            ExamFrm frm = new ExamFrm();
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
		  
	  }

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

    	  setTitle("教室管理系统,你好"+name);
          int width=1000;
          int height=800;
          setSize(width,height);
          
          int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
          int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
          setLocation(x, y);
          container.setLayout(null);
  		  this.add(container);
  		  this.setJMenuBar(menuBar);

          
          system.setMnemonic(KeyEvent.VK_S);
          exam.setMnemonic(KeyEvent.VK_E);
          help.setMnemonic(KeyEvent.VK_H);
          
          change.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
          classify.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
          qute.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
          exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

          system.add(change);
          system.add(qute);
          system.add(exit);
     
        
          exam.add(question);
          exam.add(paper);
          exam.add(classify);
          exam.add(query);
          
          help.add(about);
          help.add(help1);
          menuBar.add(system);
          menuBar.add(exam);
          menuBar.add(help);
          setJMenuBar(menuBar);
          
          about.addActionListener(this);
          exit.addActionListener(this);
          classify.addActionListener(this);
          question.addActionListener(this);
          change.addActionListener(this);
          paper.addActionListener(this);
          query.addActionListener(this);
          qute.addActionListener(this);

		
	}
	class About extends JDialog {// 关于窗口

	    About() {
	        JOptionPane.showMessageDialog(null, "            作者:嘿嘿         版本：v1.5\n\n            联系：1231231231", "关于",
	                JOptionPane.PLAIN_MESSAGE);
	    }
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}
