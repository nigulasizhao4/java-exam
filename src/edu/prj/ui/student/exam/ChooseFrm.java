package edu.prj.ui.student.exam;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Subject;
import edu.prj.bean.Paper;
import edu.prj.bean.Qtype;
import edu.prj.bean.Question;
import edu.prj.bean.Student;
import edu.prj.service.SubjectService;
import edu.prj.service.PaperService;
import edu.prj.service.QtypeService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.SubjectServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QtypeServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;

public class ChooseFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblPaper;	
	private JLabel lblMsg;	
	private JComboBox<IdText> cboPaper;	
	private JButton btnSubmit;
	private JButton btnReset;

	private String name;    
	    public String getName() {
			return name;
			}
		public void setName(String name) {
				this.name = name;
			}
	public ChooseFrm(String name){
		setName(name);
		initUI();
		bindEvent();
	}
	private void bindEvent() {		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
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
           
           
           lblPaper = new JLabel();
           lblPaper.setText("试卷名称");
           lblPaper.setBounds(50, 150, 80, 30);
           con.add(lblPaper);
           
           cboPaper = new JComboBox<IdText>();
           cboPaper.setBounds(140,150,120,33);
           con.add(cboPaper);
 
          
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
      PaperService paperService =  new PaperServiceImpl();
      QtypeService qtypeService = new QtypeServiceImpl();
	private void bindComboBoxData() {
		List<Paper> list1 = paperService.list();
		List<IdText> list=new ArrayList<>();
		
		for(Paper item:list1) {
			list.add(new IdText(item.getPaperId().longValue(),item.getPaperName()));
			
		}
		IdTextModel modelA = new IdTextModel(list);
		cboPaper.setModel(modelA);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnSubmit) {
			 IdText paperId= (IdText) cboPaper.getSelectedItem();
			 Long paperid=paperId.getId();
			 Paper bean=paperService.load(paperid);
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = new Date();
		     String formatDate = df.format(date);
			 if(formatDate.compareTo(bean.getStartOn())>0&&formatDate.compareTo(bean.getEndOn())<0&&bean.getHasGenerate()==1) {
				 ExamFrm frm = new ExamFrm(name,paperid);
			 }else {
				 JOptionPane.showMessageDialog(null,"对不起，不能考此张试卷。");
			 }						 
			 this.setVisible(false);		 	
	     }
		 if (e.getSource() == btnReset) {
			 cboPaper.setSelectedIndex(-1);
    }
  }
}
