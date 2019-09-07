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
import edu.prj.bean.Qtype;
import edu.prj.bean.Question;
import edu.prj.bean.Student;
import edu.prj.service.SubjectService;
import edu.prj.service.QtypeService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.SubjectServiceImpl;
import edu.prj.service.impl.QtypeServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;

public class ChooseFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblSubject;
	private JLabel lblQtype;
	
	private JLabel lblMsg;
	
	private JComboBox<IdText> cboSubject;
	private JComboBox<IdText> cboQtype;

	
	private JButton btnSubmit;
	private JButton btnReset;

	QuestionFrm questionFrm= null;
	public ChooseFrm(){
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
          
          setTitle("请选择：");
           
           
           lblSubject = new JLabel();
           lblSubject.setText("科目");
           lblSubject.setBounds(50, 100, 80, 30);
           con.add(lblSubject);
           
           cboSubject = new JComboBox<IdText>();
           cboSubject.setBounds(140,100,120,33);
           con.add(cboSubject);
           
           lblQtype = new JLabel();
           lblQtype.setText("类型");
           lblQtype.setBounds(50, 180, 80, 30);
           con.add(lblQtype);
           
           cboQtype = new JComboBox<IdText>();
           cboQtype.setBounds(140,180,120,33);
           con.add(cboQtype);
          
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
      SubjectService subjectRoomService =  new SubjectServiceImpl();
      QtypeService qtypeService = new QtypeServiceImpl();
	private void bindComboBoxData() {
		List<Subject> list1 = subjectRoomService.list();
		List<IdText> list=new ArrayList<>();
		
		for(Subject item:list1) {
			list.add(new IdText(item.getSubjectId().longValue(),item.getSubjectName()));
			
		}
		IdTextModel modelA = new IdTextModel(list);
		cboSubject.setModel(modelA);
		
		List<Qtype> list3 = qtypeService.list();
		List<IdText> list4=new ArrayList<>();
		
		for(Qtype item:list3) {
			list4.add(new IdText(item.getQtypeId().longValue(),item.getQtypeName()));		
		}
		IdTextModel modelB = new IdTextModel(list4);
		cboQtype.setModel(modelB);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnSubmit) {
			 IdText subjectId= (IdText) cboSubject.getSelectedItem();
			 Long subid=subjectId.getId();
			 IdText qtypeId= (IdText) cboQtype.getSelectedItem();
			 Long qid=qtypeId.getId();
			 
			 QuestionInsertFrm frm = new QuestionInsertFrm(subid,qid);
			 this.setVisible(false);		 	
	     }
		 if (e.getSource() == btnReset) {
			 cboSubject.setSelectedIndex(-1);
			 cboQtype.setSelectedIndex(-1);

    }
  }
}
