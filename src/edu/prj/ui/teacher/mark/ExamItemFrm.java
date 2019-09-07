package edu.prj.ui.teacher.mark;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Exam;
import edu.prj.bean.ExamItem;
import edu.prj.service.ExamItemService;
import edu.prj.service.impl.ExamItemServiceImpl;
import edu.prj.ui.StudentMainFrm;

import java.util.*;


public class ExamItemFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblTitle;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu=null;
	StudentMainFrm paperFrm= null;
	private String studentName;
	ExamFrm examFrm=null;
	private Long pk;
	public ExamItemFrm(Long pk){
		this.pk=pk;
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
		ExamItemService examItemService = new ExamItemServiceImpl();
		java.util.List<ExamItem> list=null;
		list=examItemService.listById(pk.intValue());	 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<ExamItem>(list,8) {

			@Override
			public Object getPropValue(ExamItem bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getItemId();
				}
				if(columnIndex==1) {
					return bean.getExamId();
				}
				if(columnIndex==2) {
					return bean.getQuestionId();
				}
				if(columnIndex==3) {
					return bean.getStuAnswer();
				}
				if(columnIndex==4) {
					return bean.getStdAnswer();
				}
				if(columnIndex==5) {
					return bean.getStdScore();
				}
				if(columnIndex==6) {
					return bean.getMarkResult();
				}
				if(columnIndex==7) {
					return bean.getGainScore();
				}
				
				return null;
				
			}
			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "ID";
				}
				if(columnIndex==1) {
					return "考试ID";
				}
				if(columnIndex==2) {
					return "题目ID";
				}
				if(columnIndex==3) {
					return "学生答案";
				}
				if(columnIndex==4) {
					return "标准答案";
				}
				if(columnIndex==5) {
					return "标准分数";
				}
				if(columnIndex==6) {
					return "阅卷结果";
				}
				if(columnIndex==7) {
					return "该题得分";
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
			if(examFrm != null) {
				examFrm.setVisible(true);
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

	}

}
