package edu.prj.ui.manager.grade;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Grade;
import edu.prj.service.GradeService;
import edu.prj.service.impl.GradeServiceImpl;

import java.util.*;


public class GradeFrm extends JFrame implements ActionListener{
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
	private JButton btnInsert;
	private JMenuItem dMenuItem;
	private JMenuItem uMenuItem;
	private String username;
	

	public GradeFrm(String name){
       setUsername(name);
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
	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		dMenuItem = new JMenuItem("删除");
		tblObjMenu.add(dMenuItem);
		dMenuItem.addActionListener(this);
		uMenuItem=new JMenuItem("修改");
		tblObjMenu.add(uMenuItem);
		uMenuItem.addActionListener(this);
	}
	
	void showListDate() {
		GradeService gradeService = new GradeServiceImpl();
		java.util.List<Grade> list=null;
		 String name=txtSearch.getText();
		 if(name!=null&&!name.isEmpty()) {
			 list=gradeService.listByName(name);
		 }else {
			list=gradeService.list();
		}
		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<Grade>(list,6) {

			@Override
			public Object getPropValue(Grade bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getGradeId();
				}
				if(columnIndex==1) {
					return bean.getGradeName();
				}
				if(columnIndex==2) {
					return bean.getCreateOn();
				}
				if(columnIndex==3) {
					return bean.getCreateBy();
				}
				if(columnIndex==4) {
					return bean.getUpdateOn();
				}
				if(columnIndex==5) {
					return bean.getUpdateBy();
				}
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "年级id";
				}
				if(columnIndex==1) {
					return "年级名称";
				}
				if(columnIndex==2) {
					return "创建时间";
				}
				if(columnIndex==3) {
					return "创建人";
				}
				if(columnIndex==4) {
					return "修改时间";
				}
				if(columnIndex==5) {
					return "修改人";
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
          lblTitle.setText("年级信息列表");
          lblTitle.setBounds(285, 5, 300, 80);
          lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
          con.add(lblTitle);
          
          lblSearch=new JLabel();
          lblSearch.setText("名称");
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
          
          btnInsert = new JButton();
          btnInsert.setText("添加");
          btnInsert.setBounds(420, 460, 70, 30);
          con.add(btnInsert);
          
          btnSearch.addActionListener(this);
          btnReset.addActionListener(this);
          btnInsert.addActionListener(this);
          
          intiTableUI();
          createTblObjMenu();        
    
    	  super.setBackground(Color.BLUE);
    	  setSize(800, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dMenuItem) {
			int index=tblObj.getSelectedRow();
			if(index!=-1) {
				TableModel model = tblObj.getModel();
				Object object=model.getValueAt(index, 0);
				Long pk=Long.valueOf(""+object);
				
				String title="系统提示";
				String message="请确认是否删除选中的数据";
				int option=JOptionPane.YES_NO_OPTION;
				int buttonValue=JOptionPane.showConfirmDialog(null, message,title,option);
				
				if(buttonValue==JOptionPane.YES_OPTION) {
					GradeService gradeService = new GradeServiceImpl();
					Long result=gradeService.delete(pk);
					if(result>0) {
						JOptionPane.showMessageDialog(null,"删除成功");
						showListDate();
					}else {
						JOptionPane.showMessageDialog(null,"删除失败");
					}
				}
				
			}
		}
        if (e.getSource() == uMenuItem) {
        	int index=tblObj.getSelectedRow();
			if(index!=-1) {
				TableModel model = tblObj.getModel();
				Object object=model.getValueAt(index, 0);
				Long pk=Long.valueOf(object.toString());
				if(pk>0) {
        	    GradeUpdateFrm gradeUpdateFrm = new GradeUpdateFrm(getUsername());
        	    
        	   gradeUpdateFrm.pk=pk;
        	   gradeUpdateFrm.loadData();
        	   
        	   gradeUpdateFrm.gradeFrm=this;	
        	   gradeUpdateFrm.setVisible(true);
        	   this.setVisible(false);
				}
			}
			
		}
		 if (e.getSource() == btnSearch) {	
			 showListDate();
	       }
		 if (e.getSource() == btnReset) {
			 txtSearch.setText("");
			 showListDate();		 
	        }
		 if (e.getSource() == btnInsert) {
			 GradeInsertFrm gradeInsertFrm = new GradeInsertFrm(getUsername());
			 gradeInsertFrm.gradeFrm=this;
			 
			 gradeInsertFrm.setVisible(true);
			 this.setVisible(false);		 
	        }
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
