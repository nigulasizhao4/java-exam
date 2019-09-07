package edu.prj.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

import com.liuvei.common.SysFun;

import edu.prj.bean.Manager;
import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

import edu.prj.service.impl.*;


public class LoginFrm extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginFrm(){
		initUI();
		//bindEvent();
	}
	private JButton jbLogin;
    private JButton jbExit;//登录，退出按钮
	private JLabel aLabel,bLabel;//标签
    private JTextField btext;//文本框
    private JPasswordField pw;//密码框
    private JLabel IbUnderText ;
    private JLabel lblStatus;
    private JRadioButton rdoS,rdoM,rdoT;
    private ButtonGroup btngrp;
    private JLabel lblMsg;

  
	private void bindEvent() {
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭按钮 
		
	}

	private void initUI() {
		  Container con=super.getContentPane();//顶级容器
    	  con.setLayout(null);//空布局
          int width=600;
          int height=450;
          setSize(width,height);
          
          int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
          int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
          setLocation(x, y);
    	  
    	  setTitle("登录系统");
    	  
    	  aLabel=new JLabel("账  号:");
    	  aLabel.setBounds(100,85,100,60);//设置组件的位置，大小（x,y,width, height）
    	  btext=new JTextField(20);
    	  btext.setBounds(180,100,200,30);
    	  btext.setBackground(Color.white);//设置颜色
 
    	  bLabel=new JLabel("密    码：");
    	  bLabel.setBounds(100,145,100,60);
    	  pw=new JPasswordField(20);
    	  pw.setBounds(180,160,200,30);
    	  pw.setBackground(Color.white);
    	
    	  jbLogin=new JButton("登录");
    	  jbLogin.setBounds(200,300,60,40);
    	  jbExit=new JButton("重置");
    	  jbExit.setBounds(300,300,60,40);
    	  
    	  IbUnderText = new JLabel();
    	  IbUnderText.setText("提示：ascdefghijklmnopqrstuvwxyz1234567890");
    	  IbUnderText.setBounds(120,255,300,300);
    	  IbUnderText.setForeground(Color.red);
    	  
    	   lblStatus = new JLabel();
    	   lblStatus .setText("身  份：");
    	   lblStatus .setBounds(100, 220, 80, 30);
           con.add(lblStatus);

           
           rdoS = new JRadioButton("学生");
           rdoS.setBounds(170,220, 80, 30);
           con.add(rdoS);
           rdoM=new JRadioButton("管理员");
           rdoM.setBounds(250,220, 80, 30);
           con.add(rdoM);
           rdoT=new JRadioButton("老师");
           rdoT.setBounds(330,220, 80, 30);
           con.add(rdoT);
           
           btngrp = new ButtonGroup();
           btngrp.add(rdoS);
           btngrp.add(rdoM);
           btngrp.add(rdoT);
           rdoS.setSelected(true);
    	  
    	  
    	  con.add(aLabel);//把组件添加到界面上
    	  con.add(btext);
    	  con.add(bLabel);
    	  con.add(pw);
    	  con.add(jbLogin);
    	  con.add(jbExit);
    	  con.add(IbUnderText);
    	  
    	  super.setVisible(true);//设置窗口为可见的
    	  
    	  jbLogin.addActionListener(this);
    	  jbExit.addActionListener(this);
    	  super.setBackground(Color.BLUE);
    	  setSize(600, 500);//设置窗口大小
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		 if (e.getSource() == jbLogin) {
			 String userName =null;
			 String userPass=null;
			 userName = btext.getText();
			 userPass = pw.getText();
			 
			 if(userName==null||userName.isEmpty()) {
				 this.IbUnderText.setText("        用户名不能为空。");
				 return ;
			 }
			 if(userPass==null||userPass.isEmpty()) {
				 this.IbUnderText.setText("        密码不能为空。");
				 return ;
			 }
			 if(rdoS.isSelected()==false&&rdoM.isSelected()==false&&rdoT.isSelected()==false) {
				 lblMsg.setText("提示：请选择身份。");
				 return ;
			 }
			 if(rdoS.isSelected()==true) {
				 StudentService studentService= new  StudentServiceImpl();
				 if(studentService.login(userName,userPass)) {
					 Student student = new Student();
					 StudentMainFrm frm = new StudentMainFrm(userName,student);
					 this.setVisible(false);
					 frm.setVisible(true);
				 }else {
					 this.IbUnderText.setText("        账户密码不正确或该用户已被禁用。");
					 
				}
					 
			}if (rdoT.isSelected()==true) {
				TeacherService teacherService = new TeacherServiceImpl();
				 if(teacherService.login(userName, userPass)) {
					 Teacher teacher = new Teacher();
					 TeacherMainFrm frm = new TeacherMainFrm(userName,teacher);
					 this.setVisible(false);
					 frm.setVisible(true);
				 }else {
					 this.IbUnderText.setText("        账户密码不正确或该用户已被禁用。");
				}
			}if (rdoM.isSelected()==true) {
				ManagerService managerService = new ManagerServiceImpl();
				 if(managerService.login(userName, userPass)) {
					 Manager manager = new Manager();
					 ManagerMainFrm frm = new ManagerMainFrm(userName,manager);	
					 frm.setVisible(true);
					 setVisible(false);
				 }else {
					 this.IbUnderText.setText("        账户密码不正确或该用户已被禁用。");
				}
			}
			 
      }
		 if (e.getSource() == jbExit) {
				this.btext.setText("");
		    	this.pw.setText("");
		 }
		
	}

}
