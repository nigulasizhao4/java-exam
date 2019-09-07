package edu.prj.ui.manager.grade;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Grade;
import edu.prj.service.GradeService;
import edu.prj.service.impl.GradeServiceImpl;
;

public class GradeInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblGradeName;
	
	private JLabel lblMsg;

	private JTextField txtGradeName;
	
	private JButton btnSubmit;
	private JButton btnReset;

	GradeFrm gradeFrm= null;
	private String username;
	public GradeInsertFrm(String string){
		setUsername(string);
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
		if(gradeFrm != null) {
			gradeFrm.setVisible(true);
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
          
          setTitle("添加年级");
          
           lblGradeName=new JLabel();
           lblGradeName.setText("年级名称");
           lblGradeName.setBounds(50, 150, 80, 30);
           con.add(lblGradeName);
           
           txtGradeName=new JTextField();
           txtGradeName.setText("");
           txtGradeName.setBounds(140, 150, 120, 30);
           con.add(txtGradeName);
           
          
           
          
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
	GradeService gradeService =  new GradeServiceImpl();
	public Long insert() {
		 GradeService gradeService =new GradeServiceImpl(); 
		 String gradeName = txtGradeName.getText().trim();
		 Long result=0l;
		 if(gradeName==null||gradeName.isEmpty()) {
			 lblMsg.setText("提示：班级名称不能为空");
			 return 0l;
		 }
		 Grade bean = gradeService.loadName(gradeName);
		 if(bean==null) {
		 Grade bean1 = new Grade();
	     bean1.setGradeName(gradeName);
	     bean1.setCreateBy(username);
		  String errMsg=null;	  
		try {
			result = gradeService.insert(bean1);
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
	 
		   if(gradeFrm!=null) {
			   txtGradeName.setText("");
				 gradeFrm.showListDate();
				 gradeFrm.setVisible(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {
			 txtGradeName.setText("");

    }
  }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
