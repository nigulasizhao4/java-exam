package edu.prj.ui.manager.student;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoom;
import edu.prj.bean.Student;
import edu.prj.service.ClassRoomService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ClassRoomServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
;

public class StudentInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblUserName;
	private JLabel lblUserPass;
	private JLabel lblNickName;
	private JLabel lblDisabled;
	private JLabel lblRoomId;
	
	private JLabel lblMsg;
	private JTextField txtUserName;
	private JTextField txtNickName;
	private JTextField txtDisabled;
	private JTextField txtUserPass;
	
	private JComboBox<IdText> cboRoomId;
	
	private ButtonGroup btngrp;
	private JRadioButton rdoY;
	private JRadioButton rdoN;
	
	private JButton btnSubmit;
	private JButton btnReset;

	StudentFrm studentFrm= null;
	public StudentInsertFrm(){
		initUI();
		bindEvent();
	}
	
	private void bindEvent() {
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			window_closing(e);
		}

	});
		
		
	}
	public void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(studentFrm != null) {
			studentFrm.setVisible(true);
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
          
          setTitle("添加学生");
          
           lblNickName = new JLabel();
           lblNickName.setText("昵称");
           lblNickName.setBounds(50, 40, 80, 30);
           con.add(lblNickName);
           
           txtNickName=new JTextField();
           txtNickName.setText("");
           txtNickName.setBounds(140,40,120,30);
           con.add(txtNickName);
           
           lblUserName=new JLabel();
           lblUserName.setText("用户名");
           lblUserName.setBounds(50, 80, 80, 30);
           con.add(lblUserName);
           
           txtUserName=new JTextField();
           txtUserName.setText("");
           txtUserName.setBounds(140, 80, 120, 30);
           con.add(txtUserName);
          
           lblUserPass = new JLabel();
           lblUserPass.setText("用户密码");
           lblUserPass.setBounds(50, 120, 80, 30);
           con.add(lblUserPass);
           
           txtUserPass= new JTextField();
           txtUserPass.setText("");
           txtUserPass.setBounds(140, 120, 120, 30);
           con.add(txtUserPass);
           
           lblDisabled=new JLabel();
           lblDisabled.setText("是否启用");
           lblDisabled.setBounds(50, 160, 80, 30);
           con.add(lblDisabled);
           
           rdoY=new JRadioButton("是");
           rdoY.setBounds(150,160, 50, 30);
           con.add(rdoY);
           rdoN=new JRadioButton("否");
           rdoN.setBounds(220,160, 50, 30);
           con.add(rdoN);
           
           btngrp = new ButtonGroup();
           btngrp.add(rdoY);
           btngrp.add(rdoN);
           rdoY.setSelected(true);
           
           
           lblRoomId = new JLabel();
           lblRoomId.setText("班级");
           lblRoomId.setBounds(50, 200, 80, 30);
           con.add(lblRoomId);
           
           cboRoomId = new JComboBox<IdText>();
           cboRoomId.setBounds(140,200,120,33);
           con.add(cboRoomId);
          
           btnSubmit = new JButton();
           btnSubmit.setText("提交");
           btnSubmit.setBounds(80, 260, 60, 30);
           con.add(btnSubmit);
           
           btnReset=new JButton();
           btnReset.setText("重置");
           btnReset.setBounds(160, 260, 60, 30);
           con.add(btnReset);
           
           lblMsg=new JLabel();
           lblMsg.setBounds(50,300 , 180, 30);
           con.add(lblMsg);
           
           btnSubmit.addActionListener(this);       
           btnReset.addActionListener(this);
           
           
               
           bindComboBoxData();
    	  super.setBackground(Color.BLUE);
    	  setSize(400, 450);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}
      ClassRoomService classRoomService =  new ClassRoomServiceImpl();
	private void bindComboBoxData() {
		List<ClassRoom> list1 = classRoomService.list();
		List<IdText> list=new ArrayList<>();
		
		for(ClassRoom item:list1) {
			list.add(new IdText(item.getRoomId().longValue(),item.getRoomname()));
			
		}
		IdTextModel modelA = new IdTextModel(list);
		cboRoomId.setModel(modelA);
	
	}
	public Long insert() {
		 StudentService studentService =new StudentServiceImpl(); 
		 String studentName=null;
		 String userName =null;
		 String userPass=null;
		 Integer isdisabled=0;
		 studentName=txtNickName.getText().trim();
		 userName = txtUserName.getText().trim();
		 userPass = txtUserPass.getText().trim();		 
		 if(SysFun.isNullOrEmpty(studentName)) {
			 lblMsg.setText("提示：姓名不能为空");
			 return 0l;
		 }
		 if(userName==null||userName.isEmpty()) {
			 lblMsg.setText("提示：用户名不能为空");
			 return 0l;
		 }
		 if(userPass==null||userPass.isEmpty()) {
			 lblMsg.setText("提示：密码不能为空");
			 return 0l;
		 }
		 if(rdoY.isSelected()==false&&rdoN.isSelected()==false) {
				 lblMsg.setText("提示：请选择是否启用。");
				 return 0l;
			 }
			 if(rdoY.isSelected()==true) {
				 isdisabled=0;
			 }else if (rdoN.isSelected()==true) {
				 isdisabled=1;
			}
		if(cboRoomId.getSelectedIndex()==-1) {
					lblMsg.setText("提示：请选择班级");
					return 0l;
			}
		 Student bean = studentService.loadName(studentName);
		 if(bean!=null) {
			 lblMsg.setText("提示：此人已存在。");
			 return 0l;
		 }
		 IdText roomId1= (IdText) cboRoomId.getSelectedItem();
		 Long roomId=roomId1.getId();
		 studentName=txtNickName.getText().trim();
		 userName = txtUserName.getText().trim();
		 userPass = txtUserPass.getText().trim();
		 Student bean1 = new Student();
		 bean1.setNickname(studentName);
		 bean1.setLoginname(userName);
		 bean1.setLoginpass(userPass);
		 bean1.setIsdisabled(isdisabled);
		 bean1.setRoomid(roomId.intValue());
		 System.out.println(roomId.intValue());
		  Long result=0l;
		  String errMsg=null;	  
		try {
			result = studentService.insert(bean1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			errMsg=e1.getMessage();
		}
		return result;
		
}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnSubmit) {
			
			 Long result=0l;
			 result=insert();		 
	 if(result>0) {
		 JOptionPane.showMessageDialog(null,"操作成功");
	 
			 if(studentFrm!=null) {
				 txtNickName.setText("");
				 txtUserName.setText("");
				 txtUserPass.setText("");
				 rdoY.setSelected(true);
				 studentFrm.showListDate();
				 studentFrm.setVisible(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {
			 txtNickName.setText("");
			 txtUserName.setText("");
			 txtUserPass.setText("");
			 rdoY.setSelected(true);

    }
  }
}
