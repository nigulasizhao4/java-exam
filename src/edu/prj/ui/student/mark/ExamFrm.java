package edu.prj.ui.student.mark;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Exam;
import edu.prj.service.ExamService;
import edu.prj.service.impl.ExamServiceImpl;
import edu.prj.ui.StudentMainFrm;

import java.util.*;


public class ExamFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblTitle;
	private JLabel  lblSearch;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnReset;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu=null;
	StudentMainFrm paperFrm= null;
	private String studentName;
	public ExamFrm(String name){
		studentName=name;
		initUI();
		bindEvent();
	}
	private void intiTableUI() {
		tblObj = new JTable();
		pnlTablePane = new JScrollPane(tblObj);
		pnlTablePane.setBounds(10, 100, 780, 300);
		con.add(pnlTablePane);
		
		showListDate();
	}	
	void showListDate() {
		ExamService examService = new ExamServiceImpl();
		java.util.List<Exam> list=null;
		// String name=txtSearch.getText();
		 if(studentName!=null&&!studentName.isEmpty()) {
			 list=examService.listByName(studentName);
		 }else {
			list=examService.list();
		}
		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<Exam>(list,6) {

			@Override
			public Object getPropValue(Exam bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getExamId();
				}
				if(columnIndex==1) {
					return bean.getStudentId();
				}
				if(columnIndex==2) {
					return bean.getStudentName();
				}
				if(columnIndex==3) {
					return bean.getPaperId();
				}
				if(columnIndex==4) {
					return bean.getIsmark();
				}
				if(columnIndex==5) {
					return bean.getTotalScore();
				}
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "ID";
				}
				if(columnIndex==1) {
					return "学生ID";
				}
				if(columnIndex==2) {
					return "学生姓名";
				}
				if(columnIndex==3) {
					return "试卷ID";
				}
				if(columnIndex==4) {
					return "是否阅卷";
				}
				if(columnIndex==5) {
					return "分数";
				}
				return "无";
			}
			
		};
		
		
		tblObj.setModel(tableModel);
		
		
	}
	private void bindEvent() {
		tblObj.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					int focuseRowIndex=tblObj.rowAtPoint(e.getPoint());
					if(focuseRowIndex== -1) {
						return;
					}
					tblObj.setRowSelectionInterval(focuseRowIndex, focuseRowIndex);
					tblObjMenu.show(tblObj,e.getX(),e.getY());
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}

		});	
		}
public void window_closing(WindowEvent e) {
			// TODO Auto-generated method stub
			if(paperFrm != null) {
				paperFrm.setVisible(true);
			}
			this.dispose();
	}

	private void initUI() {
		  con=super.getContentPane();//顶级容器
    	  con.setLayout(null);//空布局
          int width=600;
          int height=450;
          setSize(width,height);
          
          int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
          int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
          setLocation(x, y);
          
          lblTitle=new JLabel();
          lblTitle.setText("成绩结果");
          lblTitle.setBounds(285, 5, 300, 80);
          lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
          con.add(lblTitle);

          
          intiTableUI();    
          
    	  super.setBackground(Color.BLUE);
    	  setSize(800, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//		
//		 if (e.getSource() == btnSearch) {	
//			 showListDate();
//	       }
//		 if (e.getSource() == btnReset) {
//			 txtSearch.setText("");
//			 showListDate();		 
//		 }
	}

}
