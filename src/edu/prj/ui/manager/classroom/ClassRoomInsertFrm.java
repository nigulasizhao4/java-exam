package edu.prj.ui.manager.classroom;

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
import edu.prj.service.ClassRoomService;
import edu.prj.service.impl.ClassRoomServiceImpl;
;

public class ClassRoomInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblRoomName;
	private JLabel lblGradeId;

	
	private JLabel lblMsg;

	private JTextField txtRoomName;
	private JTextField txtGradeId;
	
	
	private JButton btnSubmit;
	private JButton btnReset;

	ClassRoomFrm classRoomFrm= null;
	public ClassRoomInsertFrm(){
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
		if(classRoomFrm != null) {
			classRoomFrm.setVisible(true);
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
          
          setTitle("添加教室");
          
           lblRoomName=new JLabel();
           lblRoomName.setText("班级名称");
           lblRoomName.setBounds(50, 100, 80, 30);
           con.add(lblRoomName);
           
           txtRoomName=new JTextField();
           txtRoomName.setText("");
           txtRoomName.setBounds(140, 100, 120, 30);
           con.add(txtRoomName);
           
           lblGradeId = new JLabel();
           lblGradeId.setText("年级");
           lblGradeId.setBounds(50, 180, 80, 30);
            con.add(lblGradeId);
            
            txtGradeId=new JTextField();
            txtGradeId.setText("");
            txtGradeId.setBounds(140,180,120,30);
            con.add(txtGradeId);
         
           
          
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
	ClassRoomService classRoomService =  new ClassRoomServiceImpl();
	public Long insert() {
		 ClassRoomService classRoomService =new ClassRoomServiceImpl(); 
		 String gradeId = txtGradeId.getText().trim();
		 String roomName = txtRoomName.getText().trim();
		 Long result=0l;
		 if(roomName==null||roomName.isEmpty()) {
			 lblMsg.setText("提示：班级名称不能为空");
			 return 0l;
		 }
		 if(SysFun.isNullOrEmpty(gradeId)) {
			 lblMsg.setText("提示：年级不能为空");
			 return 0l;
		 }
		 ClassRoom bean = classRoomService.loadName(roomName);
		 if(bean==null) {
		 ClassRoom bean1 = new ClassRoom();
	     bean1.setRoomname(roomName);
	     Integer gradeId1= Integer.valueOf(gradeId);
	     bean1.setGradeId(gradeId1);
		  String errMsg=null;	  
		try {
			result = classRoomService.insert(bean1);
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
	 
		   if(classRoomFrm!=null) {
				 txtGradeId.setText("");
				 txtRoomName.setText("");
				 classRoomFrm.showListDate();
				 classRoomFrm.setVisible(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {
			 txtGradeId.setText("");
			 txtRoomName.setText("");

    }
  }
}
