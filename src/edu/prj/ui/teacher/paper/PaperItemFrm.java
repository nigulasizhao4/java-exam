package edu.prj.ui.teacher.paper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.PaperItem;
import edu.prj.service.PaperItemService;
import edu.prj.service.impl.PaperItemServiceImpl;

import java.util.*;


public class PaperItemFrm extends JFrame implements ActionListener{
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
	PaperFrm paperFrm= null;
	public PaperItemFrm(){
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
		java.util.List<PaperItem> list=null;
		 String name=txtSearch.getText();
		 if(name!=null&&!name.isEmpty()) {
			 list=paperItemService.listByPId(Integer.valueOf(name).intValue());
		 }else {
			list=paperItemService.list();
		}
		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<PaperItem>(list,5) {

			@Override
			public Object getPropValue(PaperItem bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getItemId();
				}
				if(columnIndex==1) {
					return bean.getPaperId();
				}
				if(columnIndex==2) {
					return bean.getQuestionId();
				}
				if(columnIndex==3) {
					return bean.getAnswer();
				}
				if(columnIndex==4) {
					return bean.getScore();
				}
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "ID";
				}
				if(columnIndex==1) {
					return "试卷ID";
				}
				if(columnIndex==2) {
					return "题目ID";
				}
				if(columnIndex==3) {
					return "答案";
				}
				if(columnIndex==4) {
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
          lblTitle.setText("试卷信息列表");
          lblTitle.setBounds(285, 5, 300, 80);
          lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
          con.add(lblTitle);
          
          lblSearch=new JLabel();
          lblSearch.setText("试卷ID");
          lblSearch.setBounds(20,460,40,30);
          con.add(lblSearch);
          
          txtSearch = new JTextField();
          txtSearch.setBounds(70,460,180,30);
          con.add(txtSearch);
          
          btnSearch=new JButton();
          btnSearch.setText("查询");
          btnSearch.setBounds(260, 460, 70, 30);
          con.add(btnSearch);
          
          btnReset=new JButton();
          btnReset.setText("重置");
          btnReset.setBounds(340, 460, 70, 30);
          con.add(btnReset);
          
     
          btnSearch.addActionListener(this);
          btnReset.addActionListener(this);
          
          intiTableUI();    
          
    	  super.setBackground(Color.BLUE);
    	  setSize(800, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == btnSearch) {	
			 showListDate();
	       }
		 if (e.getSource() == btnReset) {
			 txtSearch.setText("");
			 showListDate();		 
		 }
	}

}
