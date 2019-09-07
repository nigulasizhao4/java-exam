package edu.prj.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import com.liuvei.common.SysFun;
import edu.prj.bean.*;
import edu.prj.dao.PersonDao;
import edu.prj.dao.impl.PersonDaoImpl;
import edu.prj.service.ManagerService;
import edu.prj.service.impl.*;

public class ChangeFrm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container con=null;
	private JLabel lblUserPass;
	private JLabel lblUserPass1;
	private JLabel lblUserPass2;

	
	private JLabel lblMsg;

	private JTextField txtUserPass;
	private JTextField txtUserPass1;
	private JTextField txtUserPass2;
	private JButton btnSubmit;
	private JButton btnReset;

   ManagerMainFrm managerMainFrm = null;
   private String name;
   private Person person;
   
	public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}

	public ChangeFrm(String name , Person person){
		if(person instanceof Manager) {
			Manager manager = new Manager();
			setName(name);
			setPerson(manager);
			initUI();
			bindEvent();
		}
		if(person instanceof Student) {
			Student student = new Student();
			setName(name);
			setPerson(student);
			initUI();
			bindEvent();
		}
		if(person instanceof Teacher) {
			Teacher teacher = new Teacher();
			setName(name);
			setPerson(teacher);
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
		if(managerMainFrm != null) {
			managerMainFrm.setVisible(true);
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
          
          lblUserPass = new JLabel();
          lblUserPass.setText("用户原密码");
          lblUserPass.setBounds(50, 80, 80, 30);
          con.add(lblUserPass);
          
          txtUserPass= new JTextField();
          txtUserPass.setText("");
          txtUserPass.setBounds(140, 80, 120, 30);
          con.add(txtUserPass);
          
          lblUserPass2 = new JLabel();
          lblUserPass2.setText("新密码");
          lblUserPass2.setBounds(50, 130, 80, 30);
          con.add(lblUserPass2);
          
          txtUserPass2= new JTextField();
          txtUserPass2.setText("");
          txtUserPass2.setBounds(140, 130, 120, 30);
          con.add(txtUserPass2);
          
          lblUserPass1 = new JLabel();
          lblUserPass1.setText("确认密码");
          lblUserPass1.setBounds(50, 180, 80, 30);
          con.add(lblUserPass1);
          
          txtUserPass1= new JTextField();
          txtUserPass1.setText("");
          txtUserPass1.setBounds(140, 180, 120, 30);
          con.add(txtUserPass1);
          
            
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
    	  setSize(425, 400);//设置窗口大小
    	  super.setVisible(true);//设置窗口为可见的
		
	}
	public Long update(String name,Person person ) throws SQLException {
		 PersonDao personDao = new PersonDaoImpl();
		 String userPass=null;	 
		 Long result=0l;
		 userPass = txtUserPass.getText().trim();
		 String userPass1 = txtUserPass1.getText().trim();
		 String userPass2 = txtUserPass2.getText().trim();
		 if(userPass==null||userPass.isEmpty()) {
			 lblMsg.setText("提示：原密码不能为空");
			 return 0l;
		 }	 
		 if(userPass2==null||userPass2.isEmpty()) {
			 lblMsg.setText("提示：新密码不能为空");
			 return 0l;
		 } 
		 if(userPass1==null||userPass1.isEmpty()) {
			 lblMsg.setText("提示：确认密码不能为空");
			 return 0l;
		 }
         if(!userPass1.equals(userPass2)) {
        	 lblMsg.setText("提示：两次密码不一致");
			 return 0l;
         }		
		 person = personDao.loadName(getName(),person);
		 if(person.getLoginpass().equals(userPass)) {
		 person.setLoginpass(userPass1);	
		 person.setLoginname(getName());
		 result = personDao.changePass(person);
		  return result;
		 }else {
			 lblMsg.setText("提示：原密码不正确");
			 return 0l;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnSubmit) {
			
			 Long result=0l;
			 try {
				result=update(name,person);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		 
	 if(result>0) {
		 JOptionPane.showMessageDialog(null,"操作成功");
	 
			 if(managerMainFrm!=null) {
				

				 txtUserPass.setText("");	
				 txtUserPass1.setText("");
				 managerMainFrm.setVisible(true);
			 }		 
		 }else  {
			 JOptionPane.showMessageDialog(null,"操作失败");
		}
	}
		 if (e.getSource() == btnReset) {			
			 txtUserPass.setText("");
			 txtUserPass1.setText("");
			 txtUserPass2.setText("");
    }
  }
}
