package edu.prj.ui.teacher.qtype;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Qtype;
import edu.prj.service.QtypeService;
import edu.prj.service.impl.QtypeServiceImpl;

public class QtypeUpdateFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;

	private JLabel lblQtypeName;
	private JLabel lblMsg;

	private JTextField txtQtypeName;
	
	private JButton btnSubmit;
	private JButton btnReset;

	QtypeFrm qtypeFrm= null;
	public Long pk;
	public QtypeUpdateFrm(){
		initUI();
		bindEvent();
	}
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null, "主键数据为空，加载数据失败");
			return;
		}
		QtypeService qtypeService = new QtypeServiceImpl();
		Qtype bean = qtypeService.load(pk);
		
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "主键对应数据为空，加载数据失败");
			return;
		}
		this.setTitle(this.getTitle()+"正在修改ID为"+pk+"的题型信息");
		if(bean!=null) {
			txtQtypeName.setText(bean.getQtypeName());
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
		if(qtypeFrm != null) {
			qtypeFrm.setVisible(true);
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
          
           lblQtypeName=new JLabel();
           lblQtypeName.setText("题型名称");
           lblQtypeName.setBounds(50, 150, 80, 30);
           con.add(lblQtypeName);
           
           txtQtypeName=new JTextField();
           txtQtypeName.setText("");
           txtQtypeName.setBounds(140, 150, 120, 30);
           con.add(txtQtypeName);       
          
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
	QtypeService qtypeService =  new QtypeServiceImpl();
	public Long update() throws SQLException {
		 QtypeService qtypeService =new QtypeServiceImpl(); 
		 String qtypeName = txtQtypeName.getText().trim();
		 Long result=0l;
		 if(qtypeName==null||qtypeName.isEmpty()) {
			 lblMsg.setText("提示：类型名称不能为空");
			 return 0l;
		 }
		 Qtype bean1 = new Qtype();
		 bean1.setQtypeId(pk.intValue());
	     bean1.setQtypeName(qtypeName);  
		 String errMsg=null;	  
		result = qtypeService.update(bean1);
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
				 
					   if(qtypeFrm!=null) {
							 txtQtypeName.setText("");
							 qtypeFrm.showListDate();
							 qtypeFrm.setVisible(true);
							 setVisible(false);
						 }		 
					 }else  {
						 JOptionPane.showMessageDialog(null,"操作失败");
					}
				}
					 if (e.getSource() == btnReset) {

						 txtQtypeName.setText("");

			    }
			  }
}
