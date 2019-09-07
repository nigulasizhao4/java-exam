package edu.prj.ui.manager.classroom;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.ClassRoom;
import edu.prj.service.ClassRoomService;
import edu.prj.service.impl.ClassRoomServiceImpl;

import java.util.*;


public class ClassRoomFrm extends JFrame implements ActionListener{
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
	JMenuItem dMenuItem;
	JMenuItem uMenuItem;
	public ClassRoomFrm(){
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
		ClassRoomService classRoomService = new ClassRoomServiceImpl();
		java.util.List<ClassRoom> list=null;
		 String name=txtSearch.getText();
		 if(name!=null&&!name.isEmpty()) {
			 list=classRoomService.listByName(name);
		 }else {
			list=classRoomService.list();
		}
		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<ClassRoom>(list,3) {

			@Override
			public Object getPropValue(ClassRoom bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getRoomId();
				}
				if(columnIndex==1) {
					return bean.getRoomname();
				}
				if(columnIndex==2) {
					return bean.getGradeId();
				}
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "班级id";
				}
				if(columnIndex==1) {
					return "班级名称";
				}
				if(columnIndex==2) {
					return "年级";
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
          lblTitle.setText("教室信息列表");
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
					ClassRoomService classRoomService = new ClassRoomServiceImpl();
					Long result=classRoomService.delete(pk);
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
        	    ClassRoomUpdateFrm classRoomUpdateFrm = new ClassRoomUpdateFrm();
        	    
        	   classRoomUpdateFrm.pk=pk;
        	   classRoomUpdateFrm.loadData();
        	   
        	   classRoomUpdateFrm.classRoomFrm=this;	
        	   classRoomUpdateFrm.setVisible(true);
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
			 ClassRoomInsertFrm classRoomInsertFrm = new ClassRoomInsertFrm();
			 classRoomInsertFrm.classRoomFrm=this;
			 
			 classRoomInsertFrm.setVisible(true);
			 this.setVisible(false);		 
	        }
		
	}

}
