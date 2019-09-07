package edu.prj.ui.student.exam;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.*;
import com.liuvei.common.win.ATableModel;

import edu.prj.bean.*;
import edu.prj.dao.impl.ExamDaoImpl;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.StudentMainFrm;

import java.util.*;


public class ExamFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton start,commit,back,next;
	private JRadioButton aButton,bButton,cButton,dButton;
	private ButtonGroup buttonGroup;
	private JLabel label,clock;
	private  JTextArea jTextArea;
	private JPanel panel,panel2,panel3;
	private String name;  
	private Long paperId;
	int p=0;//设置题目数指针           
	int topicnum=0;
	int right,error;                                                     //答对和答错
	ClockDispaly mt;                                            //倒计时模块
	static ExamItem[] examItems= new ExamItem[10];
	List<String> qList=new ArrayList<String>();
	List<String> tList=new ArrayList<String>();
	PaperItemService paperItemService = new PaperItemServiceImpl();
	QuestionService questionService= new QuestionServiceImpl();
	PaperService paperService = new PaperServiceImpl();
	StudentMainFrm studentMainFrm = null;
	Integer startButtn=0;
    public String getName() {
		return name;
		}
	public void setName(String name) {
			this.name = name;
		}
	static {
		for(int i=0;i<10;i++) {
		examItems[i]=new ExamItem();
		examItems[i].setStuAnswer("null");
		}
		
	}
	public ExamFrm(String name,Long id){
		setName(name);
		paperId=id;
		initUI();
	}

	private void initUI() {
		PaperService paperService = new PaperServiceImpl();
		Paper bean=paperService.load(paperId);
		this.setTitle("在线考试系统");                                   //设置标题
		this.setSize(600,500);                                           //设置窗口大小
		this.setLocationRelativeTo(null);                                //设置显示位置居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //设置关闭时关闭
		
		panel = new JPanel();                                            //初始化面板
		panel2 = new JPanel();
		panel3 = new JPanel();
		label = new JLabel("总考试时间: "+bean.getExamMinute()+"分钟");                             //初始化并命名标签
		clock = new JLabel();
		jTextArea = new JTextArea(20,40);                                //初始化文本区域
		jTextArea.setEditable(false);                                    //设置文本不可修改
		
		aButton =  new JRadioButton("A");                                //初始化单选按钮
		bButton =  new JRadioButton("B");
		cButton =  new JRadioButton("C");
		dButton =  new JRadioButton("D");
		buttonGroup = new ButtonGroup();                                 //初始化选项组
		
		start = new JButton("开始考试");                                   //初始化按键
		back = new JButton("上一题");
		next  = new JButton("下一题");
		commit = new JButton("提交考试");
		
		aButton.addActionListener(this);                              //单选按钮添加监听事件
		bButton.addActionListener(this);
		cButton.addActionListener(this);
		dButton.addActionListener(this);
		
		start.addActionListener(this);                                //按钮添加监听事件
		back.addActionListener(this);
		next.addActionListener(this);
		commit.addActionListener(this);
		
		
		buttonGroup.add(aButton);                                     //把单选按钮放到选项组
		buttonGroup.add(bButton);
		buttonGroup.add(cButton);
		buttonGroup.add(dButton);
		
		panel.add(label);                                             //把标签放入面板panel
		panel.add(clock);
		panel.add(start);                                             //把按键放入面板panel
		panel2.add(jTextArea);                                        //把文本区域放入面板panel2
		panel3.add(aButton);                                          //把单选按钮放入面板panel3
		panel3.add(bButton);
		panel3.add(cButton);
		panel3.add(dButton);
		panel3.add(back);                                             //把按键放入面板panel3
		panel3.add(next);
		panel3.add(commit);	
		
		this.add(panel,BorderLayout.NORTH);                           //设置面板panel放在上面
		this.add(panel2,BorderLayout.CENTER);                         //设置面板panel2放在中间
		this.add(panel3, BorderLayout.SOUTH);                         //设置面板panel放在下面
		
		this.setVisible(true);                            //设置窗口可见		
		topicnum=bean.getQuestionNum();
		mt = new ClockDispaly(clock, bean.getExamMinute());                            //调用并设置倒计时的时间		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==start) {//开始开始按键实现
			createExam();          //调用createExam模块
			p=0;                   //题目序号
			showQuestion();        //调用showQuestion模块
			start.setEnabled(false);//设置按钮不可点击
			startButtn=1;
			mt.start();             //考试时间倒计时启动
		}
		if (e.getSource()==back) {//上一题的按键实现
			if(startButtn==0) {
				JOptionPane.showMessageDialog(null, "请先开始答题");
				return;
			}
			p--;
			if(p<3) {
				cButton.setVisible(true);
				dButton.setVisible(true);
			}
			if(p>=3) {
				cButton.setVisible(false);
				dButton.setVisible(false);
			}
			if (p==-1) {
				JOptionPane.showMessageDialog(null, "已经是第一题");
				p++;
			}
			showQuestion();
		}
		if (e.getSource()==next) {//下一题的按键实现
			if(startButtn==0){
				JOptionPane.showMessageDialog(null, "请先开始答题");
				return;
			}
			p++;
			if(p<3) {
				cButton.setVisible(true);
				dButton.setVisible(true);
			}
			if(p>=3) {
				cButton.setVisible(false);
				dButton.setVisible(false);
			}
			buttonGroup.clearSelection();
			if (p==topicnum) {
				JOptionPane.showMessageDialog(null, "已经是最后一题");
				p--;
			}
			showQuestion();
		}
		if (e.getSource()==commit) {//提交试卷的按键实现
			if(p==0) {
				JOptionPane.showMessageDialog(null, "请先开始答题");
				return;
			}
			for (int i = 0; i < topicnum; i++) {
				if(!examItems[i].getStuAnswer().equals("null")) {
					continue;
				 }else {
					  JOptionPane.showMessageDialog(null, (i+1)+"题答案为空。请重新做此题");
					  return;
				  }				
			}
			showExam();
			commit.setEnabled(false);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if(studentMainFrm != null) {
						studentMainFrm.setVisible(true);
					}
					setVisible(false);
				}

			});
		}		
		if(e.getSource()==aButton) {
			examItems[p].setStuAnswer("A");
		}
		if(e.getSource()==bButton) {
			examItems[p].setStuAnswer("B");
		}
		if(e.getSource()==cButton) {
			examItems[p].setStuAnswer("C");
		}
		if(e.getSource()==dButton) {
			examItems[p].setStuAnswer("D");
		}		
	}
	public void showExam() {//设置成绩模块
		right=0;error=0;
		for (int i = 0; i < topicnum; i++) {
			if (examItems[i].getStuAnswer().equals(examItems[i].getStdAnswer())) {//判断答案的正确与错误
				Paper paper = paperService.load(paperId);
				examItems[i].setMarkResult(1);
				examItems[i].setGainScore(paper.getPerScore());
				right++;
			}else {
				examItems[i].setMarkResult(0);
				examItems[i].setGainScore(0.0);
				error++;
			}
		 
		}
		Double exam = (double) (right*100/topicnum);            //设置分数
		StudentService studentService = new  StudentServiceImpl();
		ExamService examService = new ExamServiceImpl();
		Student bean=studentService.loadName(getName());
		ExamItemService examItemService = new ExamItemServiceImpl();
		Exam exam2 = new Exam();
		 long result = 0l;
		 Long count=1l;
		 if(examService.idByName1(getName(),paperId.intValue())==null) {
			// System.out.println(bean.getStudentid());
				exam2.setStudentId(bean.getStudentid());
				exam2.setStudentName(getName());
				exam2.setPaperId(paperId.intValue());
				exam2.setIsmark(1);
				exam2.setTotalScore(exam);
				  try {
						result=examService.insert(exam2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
			
		     int examid=examService.idByName(getName());
		for (int i = 0; i < topicnum; i++) {
			examItems[i].setExamId(examid);
		  try {
			result=examItemService.insert(examItems[i]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
		  if(result>0) {
			  JOptionPane.showMessageDialog(null, "成绩已入库。答对"+right+"题，答错"+error+"题，分数为"+exam);
		  }else {
			  JOptionPane.showMessageDialog(null, "答对"+right+"题，答错"+error+"题，分数为"+exam);
		}
		
		}else {
			 JOptionPane.showMessageDialog(null, "不能重复考试。");
		}
		
	}
	public void showQuestion() {//设置试题模块
		jTextArea.setText("");
		jTextArea.append(qList.get(p));//在文本区域显示试题
	}
	public  void  createExam() {//创建考试模块
	    List<PaperItem> list1 = paperItemService.listByPId(paperId.intValue());
	    int i=0;
	    for(PaperItem item:list1) {
	    	Integer num=item.getQuestionId();
	    	Question question=questionService.load(num.longValue());
	    	if(i<3) {
	    		if(i==0) {
	    			qList.add("一."+(i+1)+"."+question.getQuestion()+"\nA:"+question.getItemA()+"\nB:"+question.getItemB()+"\nC:"+question.getItemC()+"\nD:"+question.getItemD());
	    		}
	    	    else {
	    	    	qList.add((i+1)+"."+question.getQuestion()+"\nA:"+question.getItemA()+"\nB:"+question.getItemB()+"\nC:"+question.getItemC()+"\nD:"+question.getItemD());
				}
	    	}else {
	    		if(i==3) {
	    		qList.add("二."+(i-2)+"."+question.getQuestion()+"\nA正确"+"\nB错误");
	    		}else {
	    			qList.add((i-2)+"."+question.getQuestion()+"\nA正确"+"\nB错误");	
				}
			}
	    	examItems[i].setQuestionId(num);
	    	Paper paper = paperService.load(paperId);
	    	examItems[i].setStdScore(paper.getPerScore());
	    	examItems[i].setStdAnswer(question.getAnswer());    	
	    	i++;
	    }   	    
	}
	class ClockDispaly extends Thread{//设置Thread考试倒计时模块
		
		private JLabel lefttimer;
		private int testtime;
		
		public ClockDispaly(JLabel lt,int time) {
			lefttimer = lt;
			testtime = time * 60;
		}
		public void run(){
			NumberFormat numberFormat = NumberFormat.getInstance();//控制时间的显示格式
			numberFormat.setMinimumIntegerDigits(2);//设置数值的整数部分允许的最小位数
			int h,m,s;//定义时分秒
			while (testtime >= 0) {
				h = testtime / 3600;
				m = testtime % 3600 / 60;
				s = testtime % 60;
				StringBuffer stringBuffer = new StringBuffer("");
				stringBuffer.append("考试剩余时间为："+numberFormat.format(h)+":"+numberFormat.format(m)+":"+numberFormat.format(s));
				lefttimer.setText(stringBuffer.toString());
				try {
					Thread.sleep(1000);//延时一秒
				} catch (Exception e) {
					//ignore error
				}
				testtime = testtime - 1; 
			}
			if (testtime <= 0) {
				JOptionPane.showMessageDialog(null, "考试结束");
				System.exit(0);
			}
		}

   }
}
