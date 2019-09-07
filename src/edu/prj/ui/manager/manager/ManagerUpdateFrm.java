package edu.prj.ui.manager.manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Manager;
import edu.prj.service.ManagerService;
import edu.prj.service.impl.ManagerServiceImpl;

public class ManagerUpdateFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblUserName;
	private JLabel lblUserPass;
	private JLabel lblNickName;
	private JLabel lblDisabled;

	
	private JLabel lblMsg;
	private JTextField txtUserName;
	private JTextField txtNickName;
	private JTextField txtDisabled;
	private JTextField txtUserPass;
	
	
	private ButtonGroup btngrp;
	private JRadioButton rdoY;
	private JRadioButton rdoN;
	
	private JButton btnSubmit;
	private JButton btnReset;


	ManagerFrm managerFrm= null;
	public Long pk;
	public ManagerUpdateFrm(){
		initUI();
		bindEvent();
	}
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null, "主键数据为空，加载数据失败");
			return;
		}
		ManagerService managerService = new ManagerServiceImpl();
		Manager bean = managerService.load(pk);
		
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "主键对应数据为空，加载数据失败");
			return;
		}
		this.setTitle(this.getTitle()+"正在修改ID为"+pk+"的学生信息");
		if(bean!=null) {
			txtNickName.setText(bean.getNickname());
		}
		
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
		if(managerFrm != null) {
			managerFrm.setVisible(true);
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
          rdoY.setBounds(170,160, 50, 30);
          con.add(rdoY);
          rdoN=new JRadioButton("否");
          rdoN.setBounds(220,160, 50, 30);
          con.add(rdoN);
          
          btngrp = new ButtonGroup();
          btngrp.add(rdoY);
          btngrp.add(rdoN);
          rdoY.setSelected(true);
          
     
         
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
          
          
              

   	  super.setBackground(Color.BLUE);
   	  setSize(450, 500);//设置窗口大小
   	  super.setVisible(true);//设置窗口为可见的
	}
	ManagerService managerService =  new ManagerServiceImpl();
	public Long update() throws SQLException {
		 ManagerService managerService =new ManagerServiceImpl(); 
		 String managerName=null;
		 String userName =null;
		 String userPass=null;
		 Integer isdisabled=0;
		 managerName=txtNickName.getText().trim();
		 userName = txtUserName.getText().trim();
		 userPass = txtUserPass.getText().trim();		 
		 if(SysFun.isNullOrEmpty(managerName)) {
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
	
		 Manager bean = managerService.load(pk);
		 Long result=0l;
		 if(bean!=null) {		 
		 managerName=txtNickName.getText().trim();
		 userName = txtUserName.getText().trim();
		 userPass = txtUserPass.getText().trim();
		 Manager bean1 = new Manager();
		 System.out.println(pk);
		 bean1.setManagerid(pk.intValue());
		 bean1.setNickname(managerName);
		 bean1.setLoginname(userName);
		 bean1.setLoginpass(userPass);
		 bean1.setIsdisabled(isdisabled); 
		 result = managerService.update(bean1);
	}
		return result;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnSubmit) {
			
			 Long result=0l;
		
				try {
					result=update();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
	 if(result>0) {
		 JOptionPane.showMessageDialog(null,"操作成功");
	 
			 if(managerFrm!=null) {
				 txtNickName.setText("");
				 txtUserName.setText("");
				 txtUserPass.setText("");
				 rdoY.setSelected(true);
				 managerFrm.showListDate();
				 managerFrm.setVisible(true);
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
