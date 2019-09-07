package edu.prj.ui.teacher.paper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.PaperItem;
import edu.prj.bean.Question;
import edu.prj.service.PaperItemService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

import java.util.*;
import java.util.List;


public class Paper extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblTitle;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu=null;
	PaperFrm paperFrm= null;
	private Integer paperId;
	public Paper(Integer id){
		paperId=id;
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
		PaperItemService paperItemService = new PaperItemServiceImpl();
		    List<PaperItem> list1= paperItemService.listByPId(paperId);
	        List<Question> list = new ArrayList<Question>();
	        Question bean=new Question();
	        QuestionService questionService = new QuestionServiceImpl();
	     for(PaperItem item :list1) {
	        	bean=questionService.load(item.getQuestionId().longValue());
	        	list.add(bean);
	        }		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<Question>(list,7) {

			@Override
			public Object getPropValue(Question bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getQuestionId();
				}
				if(columnIndex==1) {
					return bean.getQuestion();
				}
				if(columnIndex==2) {
					return bean.getItemA();
				}
				if(columnIndex==3) {
					return bean.getItemB();
				}
				if(columnIndex==4) {
					return bean.getItemC();
				}
				if(columnIndex==5) {
					return bean.getItemD();
				}
				if(columnIndex==6) {
					return bean.getAnswer();
				}
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "题目ID";
				}
				if(columnIndex==1) {
					return "题目";
				}
				if(columnIndex==2) {
					return "选项A";
				}
				if(columnIndex==3) {
					return "选项B";
				}
				if(columnIndex==4) {
					return "选项C";
				}
				if(columnIndex==5) {
					return "选项D";
				}
				if(columnIndex==6) {
					return "答案";
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
          lblTitle.setText("试卷信息列表");
          lblTitle.setBounds(285, 5, 300, 80);
          lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
          con.add(lblTitle);
//          
//          lblSearch=new JLabel();
//          lblSearch.setText("题目查询");
//          lblSearch.setBounds(20,460,40,30);
//          con.add(lblSearch);
//          
//          txtSearch = new JTextField();
//          txtSearch.setBounds(70,460,180,30);
//          con.add(txtSearch);
//          
//          btnSearch=new JButton();
//          btnSearch.setText("查询");
//          btnSearch.setBounds(260, 460, 70, 30);
//          con.add(btnSearch);
//          
//          btnReset=new JButton();
//          btnReset.setText("重置");
//          btnReset.setBounds(340, 460, 70, 30);
//          con.add(btnReset);
//          
//     
//          btnSearch.addActionListener(this);
//          btnReset.addActionListener(this);
          
          intiTableUI();    
          
    	  super.setBackground(Color.BLUE);
    	  setSize(800, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		
//		 if (e.getSource() == btnSearch) {	
//			 
//			 showListDate();
//	       }
//		 if (e.getSource() == btnReset) {
//			 txtSearch.setText("");
//			 showListDate();		 
//		 }
	}

}
