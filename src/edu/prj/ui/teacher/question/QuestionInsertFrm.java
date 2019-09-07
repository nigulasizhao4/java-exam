package edu.prj.ui.teacher.question;

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
import edu.prj.bean.Question;
import edu.prj.service.SubjectService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.SubjectServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

public class QuestionInsertFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblQuestion;
	private JLabel lblItemA;
	private JLabel lblItemB;
	private JLabel lblItemC;
	private JLabel lblItemD;
	private JLabel lblAnswer;
	private JLabel lblTag ;
	
	private JLabel lblMsg;
	private JTextField txtQuestion;
	private JTextField txtItemA;
	private JTextField txtItemB;
	private JTextField txtItemC;
	private JTextField txtItemD;
	private JTextField txtAnswer;
	private JTextField txtAnswer1;
	
	
	private JButton btnSubmit;
	private JButton btnReset;

	QuestionFrm questionFrm= null;
	private Integer subjectId;
	private Integer qtypeId;
	
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getQtypeId() {
		return qtypeId;
	}
	public void setQtypeId(Integer qtypeId) {
		this.qtypeId = qtypeId;
	}
	public QuestionInsertFrm(Long subid, Long qid){
		setSubjectId(subid.intValue());
		setQtypeId(qid.intValue());
		if(qtypeId==1) {
		  initUI1();
		  bindEvent();
		}
		if(qtypeId==2) {
			initUI();
			bindEvent();
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
		if(questionFrm != null) {
			questionFrm.setVisible(true);
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
          
          setTitle("添加试题");
          
           lblQuestion = new JLabel();
           lblQuestion.setText("题目");
           lblQuestion.setBounds(50, 40, 80, 30);
           con.add(lblQuestion);
           
           txtQuestion=new JTextField();
           txtQuestion.setText("");
           txtQuestion.setBounds(140,40,300,30);
           con.add(txtQuestion);
           
           lblItemA= new JLabel();
           lblItemA.setText("选项A");
           lblItemA.setBounds(50, 80, 80, 30);
           con.add(lblItemA);
           
           txtItemA=new JTextField();
           txtItemA.setText("");
           txtItemA.setBounds(140,80,300,30);
           con.add(txtItemA);
           
           lblItemB= new JLabel();
           lblItemB.setText("选项B");
           lblItemB.setBounds(50, 120, 80, 30);
           con.add(lblItemB);
           
           txtItemB=new JTextField();
           txtItemB.setText("");
           txtItemB.setBounds(140,120,300,30);
           con.add(txtItemB);
           
           lblItemC= new JLabel();
           lblItemC.setText("选项C");
           lblItemC.setBounds(50, 160, 80, 30);
           con.add(lblItemC);
           
           txtItemC=new JTextField();
           txtItemC.setText("");
           txtItemC.setBounds(140,160,300,30);
           con.add(txtItemC);
           
           lblItemD= new JLabel();        
           lblItemD.setText("选项D");
           lblItemD.setBounds(50, 200, 80, 30);
           con.add(lblItemD);
           
           txtItemD=new JTextField();
           txtItemD.setText("");
           txtItemD.setBounds(140,200,300,30);
           con.add(txtItemD);  
           
           lblAnswer= new JLabel();        
           lblAnswer.setText("答案");
           lblAnswer.setBounds(50, 240, 80, 30);
           con.add(lblAnswer);
           
           txtAnswer=new JTextField();
           txtAnswer.setText("");
           txtAnswer.setBounds(140,280,300,30);
           con.add(txtAnswer);            
            
           lblTag = new JLabel();        
           lblTag.setText("标签");
           lblTag.setBounds(50, 280, 80, 30);
           con.add(lblTag);
           
           txtAnswer1=new JTextField();
           txtAnswer1.setText("");
           txtAnswer1.setBounds(140,240,300,30);
           con.add(txtAnswer1);            
          
           btnSubmit = new JButton();
           btnSubmit.setText("提交");
           btnSubmit.setBounds(80, 350, 60, 30);
           con.add(btnSubmit);
           
           btnReset=new JButton();
           btnReset.setText("重置");
           btnReset.setBounds(160, 350, 60, 30);
           con.add(btnReset);
           
           lblMsg=new JLabel();
           lblMsg.setBounds(50,420, 180, 30);
           con.add(lblMsg);
           
           btnSubmit.addActionListener(this);       
           btnReset.addActionListener(this);
    	  super.setBackground(Color.BLUE);
    	  setSize(600,500);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}
	private void initUI1() {
		  con=super.getContentPane();//顶级容器
  	  con.setLayout(null);//空布局
        int width=600;
        int height=450;
        setSize(width,height);
        
        int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
        int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
        setLocation(x, y);
        
        setTitle("添加试题");
        
         lblQuestion = new JLabel();
         lblQuestion.setText("题目");
         lblQuestion.setBounds(50, 50, 80, 30);
         con.add(lblQuestion);
         
         txtQuestion=new JTextField();
         txtQuestion.setText("");
         txtQuestion.setBounds(140,50,300,30);
         con.add(txtQuestion);
         
         lblItemA= new JLabel();
         lblItemA.setText("A.正确");
         lblItemA.setBounds(50, 100, 80, 30);
         con.add(lblItemA);
         
         txtItemA=new JTextField();
         txtItemA.setText("");
         txtItemA.setBounds(140,100,300,30);
         con.add(txtItemA);
         
         lblItemB= new JLabel();
         lblItemB.setText("B.错误");
         lblItemB.setBounds(50, 150, 80, 30);
         con.add(lblItemB);
         
         txtItemB=new JTextField();
         txtItemB.setText("");
         txtItemB.setBounds(140,150,300,30);
         con.add(txtItemB);
         
         
         lblAnswer= new JLabel();        
         lblAnswer.setText("答案");
         lblAnswer.setBounds(50, 200, 80, 30);
         con.add(lblAnswer);
         
         txtAnswer=new JTextField();
         txtAnswer.setText("");
         txtAnswer.setBounds(140,200,300,30);
         con.add(txtAnswer);            
          
         lblTag = new JLabel();        
         lblTag.setText("标签");
         lblTag.setBounds(50, 250, 80, 30);
         con.add(lblTag);
         
         txtAnswer1=new JTextField();
         txtAnswer1.setText("");
         txtAnswer1.setBounds(140,250,300,30);
         con.add(txtAnswer1);            
        
         btnSubmit = new JButton();
         btnSubmit.setText("提交");
         btnSubmit.setBounds(80, 330, 60, 30);
         con.add(btnSubmit);
         
         btnReset=new JButton();
         btnReset.setText("重置");
         btnReset.setBounds(160, 330, 60, 30);
         con.add(btnReset);
         
         lblMsg=new JLabel();
         lblMsg.setBounds(50,400, 180, 30);
         con.add(lblMsg);
         
         btnSubmit.addActionListener(this);       
         btnReset.addActionListener(this);
  	  super.setBackground(Color.BLUE);
  	  setSize(450,500);//设置窗口大小
  	  super.setVisible(true);//设置窗口为可见的
		
	}
	public Long insert() {
		 QuestionService questionService =new QuestionServiceImpl(); 
		 Question bean1 = new Question();
		 if(getQtypeId()==2) {
		 String question = txtQuestion.getText().trim();
		 String itemA = txtItemA.getText().trim();	 
		 String itemB = txtItemB.getText().trim();	
		 String itemC = txtItemC.getText().trim();	
		 String itemD = txtItemD.getText().trim();	
		 String answer = txtAnswer1.getText().trim();
		 String tag = txtAnswer.getText().trim();	
		 if(SysFun.isNullOrEmpty(question)) {
			 lblMsg.setText("提示：题目不能为空");
			 return 0l;
		 }
		 if(SysFun.isNullOrEmpty(itemA)) {
			 lblMsg.setText("提示：选项A不能为空");
			 return 0l;
		 }
		 if(SysFun.isNullOrEmpty(itemB)) {
			 lblMsg.setText("提示：选项B不能为空");
			 return 0l;
		 }
		 if(SysFun.isNullOrEmpty(itemC)) {
			 lblMsg.setText("提示：选项C不能为空");
			 return 0l;
		 }
		 if(SysFun.isNullOrEmpty(itemD)) {
			 lblMsg.setText("提示：选项D不能为空");
			 return 0l;
		 } 
		 if(SysFun.isNullOrEmpty(answer)) {
			 lblMsg.setText("提示：答案不能为空");
			 return 0l;
		 }
		
		 Question bean = questionService.loadName(question);
		 if(bean!=null) {
			 lblMsg.setText("提示：此题已存在。");
			 return 0l;
		 }
		 bean1 = new Question();
		 bean1.setqTypeId(getQtypeId());
		 bean1.setQuestion(question);
		 bean1.setItemA(itemA);
		 bean1.setItemB(itemB);
		 bean1.setItemC(itemC);
		 bean1.setItemD(itemD);
		 bean1.setAnswer(answer);
		 bean1.setSubjectId(getSubjectId());
		 bean1.setTag(tag);
		}
		 if(qtypeId==1) {
			 String question = txtQuestion.getText().trim();
			 String itemA = txtItemA.getText().trim();	 
			 String itemB = txtItemB.getText().trim();	
			 String answer = txtAnswer1.getText().trim();
			 String tag = txtAnswer.getText().trim();	
			 if(SysFun.isNullOrEmpty(question)) {
				 lblMsg.setText("提示：题目不能为空");
				 return 0l;
			 }
			 if(SysFun.isNullOrEmpty(itemA)&&SysFun.isNullOrEmpty(itemB)) {
				 lblMsg.setText("提示：判断选项不能为空");
				 return 0l;
			 }
			
			 if(SysFun.isNullOrEmpty(answer)) {
				 lblMsg.setText("提示：答案不能为空");
				 return 0l;
			 }
			
			 Question bean = questionService.loadName(question);
			 if(bean!=null) {
				 lblMsg.setText("提示：此题已存在。");
				 return 0l;
			 }
			 bean1 = new Question();
			 bean1.setqTypeId(getQtypeId());
			 bean1.setQuestion(question);
			 bean1.setItemA(itemA);
			 bean1.setItemB(itemB);
			 bean1.setItemC("null");
			 bean1.setItemD("null");
			 bean1.setAnswer(answer);
			 bean1.setSubjectId(getSubjectId());
			 bean1.setTag(tag);			 
		 }
		  Long result=0l;
		  String errMsg=null;	  
		try {
			result = questionService.insert(bean1);
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
	 
			 if(questionFrm!=null) {
		             txtQuestion.setText("");
				     txtItemA.setText("");
					 txtItemB.setText("");
					 txtItemC.setText("");
					 txtItemD.setText("");
					 txtAnswer.setText("");
					 txtAnswer1.setText("");
			
				 questionFrm.showListDate();
				 questionFrm.setVisible(true);
				 setVisible(false);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	   this.setVisible(false);
	   QuestionFrm questionFrm = new QuestionFrm();
	   return;
	}
		 if (e.getSource() == btnReset) {
             txtQuestion.setText("");
		     txtItemA.setText("");
			 txtItemB.setText("");
			 txtItemC.setText("");
			 txtItemD.setText("");
			 txtAnswer.setText("");
			 txtAnswer1.setText("");

    }
  }
}
