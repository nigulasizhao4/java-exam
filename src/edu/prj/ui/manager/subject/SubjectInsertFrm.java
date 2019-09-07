package edu.prj.ui.manager.subject;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Subject;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.SubjectServiceImpl;
;

public class SubjectInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblRoomName;
	private JLabel lblMsg;
	private JLabel lblDisabled;

	private JTextField txtSubjectName;

	private ButtonGroup btngrp;
	private JRadioButton rdoY;
	private JRadioButton rdoN;
	
	
	private JButton btnSubmit;
	private JButton btnReset;

	SubjectFrm subjectFrm= null;
	public SubjectInsertFrm(){
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
		if(subjectFrm != null) {
			subjectFrm.setVisible(true);
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
          
          setTitle("添加科目");
          
           lblRoomName=new JLabel();
           lblRoomName.setText("科目名称");
           lblRoomName.setBounds(50, 100, 80, 30);
           con.add(lblRoomName);
           
           txtSubjectName=new JTextField();
           txtSubjectName.setText("");
           txtSubjectName.setBounds(140, 100, 120, 30);
           con.add(txtSubjectName);
           
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
    	  setSize(400, 450);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}
	SubjectService subjectService =  new SubjectServiceImpl();
	public Long insert() {
		 SubjectService subjectService =new SubjectServiceImpl(); 
		 String subjectName = txtSubjectName.getText().trim();
		 String status=null;
		 Long result=0l;
		 if(subjectName==null||subjectName.isEmpty()) {
			 lblMsg.setText("提示：班级名称不能为空");
			 return 0l;
		 }
		 if(rdoY.isSelected()==false&&rdoN.isSelected()==false) {
			 lblMsg.setText("提示：请选择是否启用。");
			 return 0l;
		 }
		 if(rdoY.isSelected()==true) {
			 status="启用";
		 }else if (rdoN.isSelected()==true) {
			 status="禁用";
		}
		 Subject bean = subjectService.loadName(subjectName);
		 if(bean==null) {
		 Subject bean1 = new Subject();
	     bean1.setSubjectName(subjectName);
	     bean1.setStatus(status);
		  String errMsg=null;	  
		try {
			result = subjectService.insert(bean1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			errMsg=e1.getMessage();
		}
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
	 
		   if(subjectFrm!=null) {

				 txtSubjectName.setText("");
				 subjectFrm.showListDate();
				 subjectFrm.setVisible(true);
				 rdoY.setSelected(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {
			 txtSubjectName.setText("");
			 rdoY.setSelected(true);

    }
  }
}
