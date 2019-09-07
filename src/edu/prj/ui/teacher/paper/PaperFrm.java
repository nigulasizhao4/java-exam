package edu.prj.ui.teacher.paper;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Paper;
import edu.prj.bean.PaperItem;
import edu.prj.bean.Question;
import edu.prj.bean.Subject;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

import java.util.*;
import java.util.List;


public class PaperFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblTitle;
	private JLabel  lblSearch;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnReset;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JPopupMenu tblObjMenu=null;
	private JButton btnInsert;
	private JButton btnShow;
	JMenuItem dMenuItem;
	JMenuItem uMenuItem;
	JMenuItem aMenuItem;
	JMenuItem sMenuItem;
	static List<Integer> quIdList = new ArrayList<Integer>();//题目id
	static List<Question> quList = null;//保存题目题数
	static List<Question> quList1 = null;//保存题目题数
	static List<Integer> indexList = new ArrayList<Integer>();//保存下标
	public PaperFrm(){
		initUI();
		bindEvent();
	}
	private void intiTableUI() {
		tblObj = new JTable();
		pnlTablePane = new JScrollPane(tblObj);
		pnlTablePane.setBounds(10, 100, 780, 300);
		con.add(pnlTablePane);
		
		showListDate();
	}
	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		
		dMenuItem = new JMenuItem("删除");
		tblObjMenu.add(dMenuItem);
		dMenuItem.addActionListener(this);
		
		uMenuItem=new JMenuItem("修改");
		tblObjMenu.add(uMenuItem);
		uMenuItem.addActionListener(this);
		
		aMenuItem=new JMenuItem("生成试卷");
		tblObjMenu.add(aMenuItem);
		aMenuItem.addActionListener(this);
		
		sMenuItem=new JMenuItem("试卷预览");
		tblObjMenu.add(sMenuItem);
		sMenuItem.addActionListener(this);
	}
	
	void showListDate() {
		PaperService paperService = new PaperServiceImpl();
		java.util.List<Paper> list=null;
		 String name=txtSearch.getText();
		 if(name!=null&&!name.isEmpty()) {
			 list=paperService.listByName(name);
		 }else {
			list=paperService.list();
		}
		 
		 ATableModel tableModel = null;
		tableModel = new ATableModel<Paper>(list,10) {

			@Override
			public Object getPropValue(Paper bean, int columnIndex) {
				if(columnIndex==0) {
					return bean.getPaperId();
				}
				if(columnIndex==1) {
					return bean.getPaperName();
				}
				if(columnIndex==2) {
					return bean.getTotalScore();
				}
				if(columnIndex==3) {
					return bean.getPerScore();
				}
				if(columnIndex==4) {
					return bean.getQuestionNum();
				}
				if(columnIndex==5) {
					return bean.getExamMinute();
				}
				if(columnIndex==6) {
					return bean.getStartOn();
				}
				if(columnIndex==7) {
					return bean.getEndOn();
				}
				if(columnIndex==8) {
					return bean.getHasGenerate();
				}
				if(columnIndex==9) {
					return bean.getSubjectId();
				}
			
				
				return null;
			}

			@Override
			public String getTitle(int columnIndex) {
				if(columnIndex==0) {
					return "试卷id";
				}
				if(columnIndex==1) {
					return "试卷名称";
				}
				if(columnIndex==2) {
					return "总分";
				}
				if(columnIndex==3) {
					return "每题分数";
				}
				if(columnIndex==4) {
					return "题目数";
				}
				if(columnIndex==5) {
					return "考试分钟";
				}if(columnIndex==6) {
					return "有效开始日期";
				}
				if(columnIndex==7) {
					return "有效结束日期";
				}
				if(columnIndex==8) {
					return "是否已生产";
				}
				if(columnIndex==9) {
					return "科目id";
				}
				return "无";
			}
			
		};
		
		
		tblObj.setModel(tableModel);
		
		
	}
	private void bindEvent() {
		tblObj.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					int focuseRowIndex=tblObj.rowAtPoint(e.getPoint());
					if(focuseRowIndex== -1) {
						return;
					}
					tblObj.setRowSelectionInterval(focuseRowIndex, focuseRowIndex);
					tblObjMenu.show(tblObj,e.getX(),e.getY());
				}
			}
		});
		
		
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
          
          lblTitle=new JLabel();
          lblTitle.setText("试卷信息列表");
          lblTitle.setBounds(285, 5, 300, 80);
          lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
          con.add(lblTitle);
          
          lblSearch=new JLabel();
          lblSearch.setText("名称");
          lblSearch.setBounds(20,460,40,30);
          con.add(lblSearch);
          
          txtSearch = new JTextField();
          txtSearch.setBounds(70,460,180,30);
          con.add(txtSearch);
          
          btnSearch=new JButton();
          btnSearch.setText("查询");
          btnSearch.setBounds(260, 460, 70, 30);
          con.add(btnSearch);
          
          btnReset=new JButton();
          btnReset.setText("重置");
          btnReset.setBounds(340, 460, 70, 30);
          con.add(btnReset);
          
          btnInsert = new JButton();
          btnInsert.setText("添加");
          btnInsert.setBounds(420, 460, 70, 30);
          con.add(btnInsert);
          
          btnShow = new JButton();
          btnShow .setText("试卷查询");
          btnShow.setBounds(500, 460, 140, 30);
          con.add(btnShow );
          
          btnSearch.addActionListener(this);
          btnReset.addActionListener(this);
          btnInsert.addActionListener(this);
          btnShow.addActionListener(this);
          
          intiTableUI();
          createTblObjMenu();        
    
    	  super.setBackground(Color.BLUE);
    	  setSize(850, 600);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dMenuItem) {
			int index=tblObj.getSelectedRow();
			if(index!=-1) {
				TableModel model = tblObj.getModel();
				Object object=model.getValueAt(index, 0);
				Long pk=Long.valueOf(""+object);
				
				String title="系统提示";
				String message="请确认是否删除选中的数据";
				int option=JOptionPane.YES_NO_OPTION;
				int buttonValue=JOptionPane.showConfirmDialog(null, message,title,option);
				
				if(buttonValue==JOptionPane.YES_OPTION) {
					PaperService paperService = new PaperServiceImpl();
					Long result=paperService.delete(pk);
					if(result>0) {
						JOptionPane.showMessageDialog(null,"删除成功");
						showListDate();
					}else {
						JOptionPane.showMessageDialog(null,"删除失败");
					}
				}
				
			}
		}
        if (e.getSource() == uMenuItem) {
        	int index=tblObj.getSelectedRow();
			if(index!=-1) {
					TableModel model = tblObj.getModel();
					Object object=model.getValueAt(index, 0);
					Long pk=Long.valueOf(object.toString());
					if(pk>0) {
	        	    PaperUpdateFrm paperUpdateFrm = new PaperUpdateFrm();
	        	    
	        	   paperUpdateFrm.pk=pk;
	        	   paperUpdateFrm.loadData();
	        	   
	        	   paperUpdateFrm.paperFrm=this;	
        	       paperUpdateFrm.setVisible(true);
        	       this.setVisible(false);
				}
			}			
		}
        if (e.getSource() == aMenuItem) {
        	int index=tblObj.getSelectedRow();
			if(index!=-1) {
					TableModel model = tblObj.getModel();
					Object object=model.getValueAt(index, 0);
					Long pk=Long.valueOf(object.toString());
					PaperService paperService = new PaperServiceImpl();
					PaperItemService paperItemService = new PaperItemServiceImpl();
					Long result=0l;
					int count=0;
				if(pk>0) {     	 					
        	        	Paper bean= paperService.load(pk);
        	       if(bean.getHasGenerate()==0) {
        	        	PaperItem paperItem = new PaperItem();
        	        	Question question = new Question();
        	        	QuestionService questionService = new QuestionServiceImpl();      	      
        	        	generate(pk.intValue(),bean.getQuestionNum());
        	         for(int i=0;i<quIdList.size();i++) {      	    	  
        	           question=questionService.load(quIdList.get(i).longValue());
        	        	PaperItem paperItem1 = new PaperItem();
        	        	if(question!=null) {
        	        		paperItem1.setPaperId(pk.intValue());
        	        		paperItem1.setQuestionId(quIdList.get(i));
        	        		paperItem1.setAnswer(question.getAnswer());
        	        		paperItem1.setScore(bean.getPerScore());
        	        		try {
             	        		result=paperItemService.insert(paperItem1);	
             	        	if(result>0) {
             	        		   count++;
             	        		}
     						} catch (SQLException e1) {
     							// TODO Auto-generated catch block
     							e1.printStackTrace();
     						}
        	        	  } 
        	             else {
        	        		  continue;
        	             }
        	      }
        	       if(count==bean.getQuestionNum()) {
        	    	   
						JOptionPane.showMessageDialog(null,"生成成功");
						paperService.update1(pk.intValue());
						showListDate();
					}else {
						JOptionPane.showMessageDialog(null,"生成失败");
					}
				}
        	      else {
        	    	   JOptionPane.showMessageDialog(null,"以生成过试卷生成失败");
			    	}
				}
			}			 
        }
        if (e.getSource() == sMenuItem) {	
        	int index=tblObj.getSelectedRow();
			if(index!=-1) {
					TableModel model = tblObj.getModel();
					Object object=model.getValueAt(index, 0);
					Long pk=Long.valueOf(object.toString());
				if(pk>0) {     	 					
        	        	edu.prj.ui.teacher.paper.Paper paper = new edu.prj.ui.teacher.paper.Paper(pk.intValue());
        	        	
			 
	       }
		}
      }
		 if (e.getSource() == btnSearch) {	
			 showListDate();
	       }
		 if (e.getSource() == btnReset) {
			 txtSearch.setText("");
			 showListDate();		 
	        }
		 if (e.getSource() == btnInsert) {
			 PaperInsertFrm paperInsertFrm = new PaperInsertFrm();
			 paperInsertFrm.paperFrm=this;			 
			 paperInsertFrm.setVisible(true);
			 this.setVisible(false);		 
	        }
		 if (e.getSource() == btnShow) {
			 PaperItemFrm paperItem = new PaperItemFrm();
			 paperItem.paperFrm=this;	
			 paperItem.setVisible(true);
			 this.setVisible(false);
        }
		
	}
	public static boolean generate(Integer paperId, Integer quNum) {
		boolean isOK = false;
		QuestionService questionService = new QuestionServiceImpl();
       Paper bean=null;
       PaperService paperService=new PaperServiceImpl();
       bean=paperService.load(paperId.longValue());

       quList = questionService.listByQtype(2,bean.getSubjectId());//保存数学试卷的选择题题目题数
       quList1 = questionService.listByQtype(1,bean.getSubjectId());

		Integer randMax = quList.size();
		Integer randMax1 = quList1.size();
		while (indexList.size() <quNum) {
			if(indexList.size() <3) {

			 Integer rand = (int) (Math.random() * randMax);
			boolean isExists = false;
			for (int j = 0; j < indexList.size(); j++) {
				if (rand == indexList.get(j)) {
					isExists = true;
					break;
				}
			}			
			if(!isExists) {
				indexList.add(rand);
			}
		   }else {
				Integer rand = (int) (Math.random() * randMax1);
				boolean isExists = false;
				for (int j = 0; j < indexList.size(); j++) {
					if (rand == indexList.get(j)) {
						isExists = true;
						break;
					}
				}			
				if(!isExists) {
					indexList.add(rand);
				}		
		}
		}		
		for(int i=0; i< indexList.size();i++) {
			if(i<3) {
			quIdList.add(quList.get(indexList.get(i)).getQuestionId());
			}
			else {
				quIdList.add(quList1.get(indexList.get(i)).getQuestionId());
			}
		}

		return isOK;
	}

}
