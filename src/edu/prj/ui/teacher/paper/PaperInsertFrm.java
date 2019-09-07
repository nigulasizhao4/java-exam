package edu.prj.ui.teacher.paper;

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
import edu.prj.bean.Paper;
import edu.prj.bean.Subject;
import edu.prj.service.PaperService;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.SubjectServiceImpl;


public class PaperInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblPaperName;
	private JLabel lblTotalScore;
	private JLabel lblPerScore;
	private JLabel lblQuestionNum;
	private JLabel lblExamMinute;
	private JLabel lblStartOn;
	private JLabel lblEndOn;
	private JLabel lblHasGenerate;
	private JLabel lblMsg;
	private JLabel lblSubjectId;

	private JTextField txtPaperName;
	private JTextField txtTotalScore;
	private JTextField txtPerScore;
	private JTextField txtQuestionNum;
	private JTextField txtExamMinute;
	private JTextField txtStartOn;
	private JTextField txtEndOn;
	
	private JComboBox<IdText> cboSubjectId;
	
	private ButtonGroup btngrp;
	private JRadioButton rdoY;
	private JRadioButton rdoN;
	
	private JButton btnSubmit;
	private JButton btnReset;

	PaperFrm paperFrm= null;
	public PaperInsertFrm(){
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
          
          setTitle("添加试卷");
          
           lblPaperName=new JLabel();
           lblPaperName.setText("试卷名称");
           lblPaperName.setBounds(50, 50, 80, 30);
           con.add(lblPaperName);
           
           txtPaperName=new JTextField();
           txtPaperName.setText("");
           txtPaperName.setBounds(140, 50, 120, 30);
           con.add(txtPaperName);
           
           lblTotalScore=new JLabel();
           lblTotalScore.setText("总分");
           lblTotalScore.setBounds(50, 90, 80, 30);
           con.add(lblTotalScore);
           
           txtTotalScore=new JTextField();
           txtTotalScore.setText("");
           txtTotalScore.setBounds(140, 90, 120, 30);
           con.add(txtTotalScore);
           
           lblPerScore=new JLabel();
           lblPerScore.setText("每题分数");
           lblPerScore.setBounds(50, 130, 80, 30);
           con.add(lblPerScore);
           
           txtPerScore=new JTextField();
           txtPerScore.setText("");
           txtPerScore.setBounds(140, 130, 120, 30);
           con.add(txtPerScore);
           
           lblQuestionNum=new JLabel();
           lblQuestionNum.setText("题目数");
           lblQuestionNum.setBounds(50, 170, 80, 30);
           con.add(lblQuestionNum);
           
           txtQuestionNum=new JTextField();
           txtQuestionNum.setText("");
           txtQuestionNum.setBounds(140, 170, 120, 30);
           con.add(txtQuestionNum);
           
           lblExamMinute=new JLabel();
           lblExamMinute.setText("考试分钟");
           lblExamMinute.setBounds(50, 210, 80, 30);
           con.add(lblExamMinute);
           
           txtExamMinute=new JTextField();
           txtExamMinute.setText("");
           txtExamMinute.setBounds(140, 210, 120, 30);
           con.add(txtExamMinute);
           
           lblStartOn=new JLabel();
           lblStartOn.setText("有效开始时间");
           lblStartOn.setBounds(50, 250, 80, 30);
           con.add(lblStartOn);
           
           txtStartOn=new JTextField();
           txtStartOn.setText("");
           txtStartOn.setBounds(140, 250, 120, 30);
           con.add(txtStartOn);
           
           lblEndOn=new JLabel();
           lblEndOn.setText("有效结束时间");
           lblEndOn.setBounds(50, 290, 80, 30);
           con.add(lblEndOn);
           
           txtEndOn=new JTextField();
           txtEndOn.setText("");
           txtEndOn.setBounds(140, 290, 120, 30);
           con.add(txtEndOn);
           
//           lblHasGenerate=new JLabel();
//           lblHasGenerate.setText("是否生成");
//           lblHasGenerate.setBounds(50, 330, 80, 30);
//           con.add(lblHasGenerate);
//           
//           rdoY=new JRadioButton("是");
//           rdoY.setBounds(150,330, 50, 30);
//           con.add(rdoY);
//           rdoN=new JRadioButton("否");
//           rdoN.setBounds(220,330, 50, 30);
//           con.add(rdoN);
//           
//           btngrp = new ButtonGroup();
//           btngrp.add(rdoY);
//           btngrp.add(rdoN);
//           rdoY.setSelected(true);
           
           JLabel lblSubjectId = new JLabel();
           lblSubjectId.setText("科目");
           lblSubjectId.setBounds(50, 330, 80, 30);
           con.add(lblSubjectId);
           
           cboSubjectId = new JComboBox<IdText>();
           cboSubjectId.setBounds(140,330,120,33);
           con.add(cboSubjectId);

                          
           btnSubmit = new JButton();
           btnSubmit.setText("提交");
           btnSubmit.setBounds(80, 400, 60, 30);
           con.add(btnSubmit);
           
           btnReset=new JButton();
           btnReset.setText("重置");
           btnReset.setBounds(160, 400, 60, 30);
           con.add(btnReset);
           
           lblMsg=new JLabel();
           lblMsg.setBounds(50,450 , 180, 30);
           con.add(lblMsg);
           
           btnSubmit.addActionListener(this);       
           btnReset.addActionListener(this);
           
           bindComboBoxData();
    	  super.setBackground(Color.BLUE);
    	  setSize(600, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}
	private void bindComboBoxData() {
		SubjectService subjectService = new SubjectServiceImpl();
		List<Subject> list1 = subjectService.list();
		List<IdText> list=new ArrayList<>();
		
		for(Subject item:list1) {
			list.add(new IdText(item.getSubjectId().longValue(),item.getSubjectName()));	
		}
		IdTextModel modelA = new IdTextModel(list);
		cboSubjectId.setModel(modelA);
	
	}
	public Long insert() {
		 PaperService paperService =new PaperServiceImpl(); 
		 String paperName = txtPaperName.getText().trim();
		 String totalScore=txtTotalScore.getText().trim();
		 String perScore=txtPerScore.getText().trim();
		 String questionNum = txtQuestionNum.getText().trim();
		 String examMinute = txtExamMinute.getText().trim();
		 String startOn= txtStartOn.getText().trim();
		 String endOn = txtEndOn.getText().trim();
		 Integer hasGenerate=0;
		 Long result=0l;
		 if(paperName==null||paperName.isEmpty()) {
			 lblMsg.setText("提示：试卷名称不能为空");
			 return 0l;
		 }
		 if(cboSubjectId.getSelectedIndex()==-1) {
				lblMsg.setText("提示：请选择班级");
				return 0l;
		}
//		 if(rdoY.isSelected()==false&&rdoN.isSelected()==false) {
//			 lblMsg.setText("提示：请选择是否启用。");
//			 return 0l;
//		 }
//		 if(rdoY.isSelected()==true) {
//			 hasGenerate=0;
//		 }else if (rdoN.isSelected()==true) {
//			 hasGenerate=1;
//		}
		 IdText subjectId1= (IdText) cboSubjectId.getSelectedItem();
		 Long subjectId=subjectId1.getId();
		 Paper bean = paperService.loadName(paperName);
		 if(bean==null) {
		 Paper bean1 = new Paper();
	     bean1.setPaperName(paperName);
	     bean1.setTotalScore(Double.valueOf(totalScore).doubleValue());	 
	     bean1.setPerScore(Double.valueOf(perScore).doubleValue());
	     bean1.setQuestionNum(Integer.valueOf(questionNum).intValue());
	     bean1.setSubjectId(subjectId.intValue());
	     bean1.setExamMinute(Integer.valueOf(examMinute).intValue());
	     bean1.setStartOn(startOn);
	     bean1.setEndOn(endOn);
	//     bean1.setHasGenerate(hasGenerate);     
		  String errMsg=null;	  
		try {
			result = paperService.insert(bean1);
		} catch (SQLException e1) {
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
	 
		   if(paperFrm!=null) {
				txtPaperName.setText("");
				txtTotalScore.setText("");
				txtPerScore.setText("");
				txtQuestionNum.setText("");
				txtExamMinute.setText("");
				txtStartOn.setText("");
				txtEndOn.setText("");
				 paperFrm.showListDate();
				 paperFrm.setVisible(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {
			 txtPaperName.setText("");
				txtTotalScore.setText("");
				txtPerScore.setText("");
				txtQuestionNum.setText("");
				txtExamMinute.setText("");
				txtStartOn.setText("");
				txtEndOn.setText("");
				//rdoN.setSelected(true);

    }
  }
}
