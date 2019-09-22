package registration_form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class Demo extends JFrame implements ActionListener
{
	JTextArea display;
	JCheckBox term;
	JLabel[] lable;
	JLabel head;
	JTextField name,email,mob,college;
	JTextField searchb,deleteb;
	JPasswordField pass;
	JComboBox date,year,Jmonth;
	JRadioButton male,female;
	
	JButton submit,reset,delete,search,showData;
	public Demo() 
	{
		super.setTitle("Registration Form");
		super.setBounds(100,50,700, 700);
		Color c = new Color(162, 189, 232);
		super.getContentPane().setBackground(c);
		/////////////////////////////////////////////
		head=new JLabel("Registration Form");
		head.setBounds(100,10,200,40);
		super.add(head);
		
		String[] lname= {"Name", "College", "Mobile No ","Email ID","Password","DOB","Gender"};
		lable=new JLabel[lname.length];
		int ycor=60;
		for(int i=0;i<lname.length;i++)
		{
			lable[i]=new JLabel(lname[i]);
			lable[i].setBounds(10,ycor,80,30);
			ycor=ycor+40;
			super.add(lable[i]);
		}
		
		name=new JTextField();
		name.setBounds(100,60,200,30);
		super.add(name);
		college=new JTextField();
		college.setBounds(100,100,200,30);
		super.add(college);
		mob=new JTextField();
		mob.setBounds(100,140,200,30);
		super.add(mob);
		email=new JTextField();
		email.setBounds(100,180,200,30);
		super.add(email);
		pass=new JPasswordField();
		pass.setBounds(100,220,200,30);
		super.add(pass);
		String[] month= {"January","February","March","April","May","June","July"
				,"August","September","October","November","December"};
		Jmonth=new JComboBox(month);
		Jmonth.setBounds(160,260,70, 30);
		super.add(Jmonth);		
		
		String[] daylist=new String[31];
		for(int i=1;i<=31;i++)
		{
			daylist[i-1]=Integer.toString(i);
		}
		date=new JComboBox(daylist);
		date.setBounds(100,260,50,30);
		super.add(date);
		
		String[] yearlist=new String[71];
		for(int i=1950;i<=2020;i++)
		{
			yearlist[i-1950]=Integer.toString(i);
		}
		year=new JComboBox(yearlist);
		year.setBounds(240,260,60,30);
		super.add(year);
		
		male=new JRadioButton("Male",true);
		male.setBounds(100,300,100,30);
		super.add(male);
		female=new JRadioButton("Female");
		female.setBounds(210,300,100,30);
		super.add(female);
		ButtonGroup group = new ButtonGroup();
		group.add(male); group.add(female);
		
		term=new JCheckBox("Agree to terms and conditions");
		term.setBounds(60,340,200,30);
		super.add(term);
		
		submit =new JButton("Submit");
		submit.setBounds(100, 380,100,30);
		super.add(submit);
		
		reset =new JButton("RESET");
		reset.setBounds(220, 380,100,30);
		super.add(reset);
		
		showData =new JButton("Show Data");
		showData.setBounds(165, 420,100,30);
		super.add(showData);
		
		searchb =new JTextField();
		searchb.setBounds(115,460,200,30);
		super.add(searchb);
		
		search =new JButton("SEARCH");
		search.setBounds(165, 500,100,30);
		super.add(search);
		
		
		deleteb =new JTextField();
		deleteb.setBounds(115,540,200,30);
		super.add(deleteb);
		
		delete =new JButton("DELETE");
		delete.setBounds(165, 580,100,30);
		super.add(delete);
		
		display =new JTextArea();
		display.setBounds(360, 60, 300,520);
		super.add(display);
		
		
		submit.addActionListener(this);
		reset.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);
		showData.addActionListener(this);
		
super.setLayout(null);
super.setResizable(false);
super.setVisible(true);
super.setDefaultCloseOperation(3);
		
	}
	public static void main(String[] args) 
	{
		Demo demo =new Demo();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String c=e.getActionCommand();
		if(c=="Submit")
		{
			if(term.isSelected())
			{
				display.setText("submit clicked");
				String sname=name.getText();
				String semail=email.getText();
				String smob=mob.getText();
				String spass=pass.getText();
				String scollege=college.getText();
				String sgender="male";
				if(female.isSelected())
				{
				 sgender="female";
				}
				String sdate=(String) date.getSelectedItem();
				String syear=(String) year.getSelectedItem();
				String mon=(String) Jmonth.getSelectedItem();
				
				String sDOB=syear+"-"+mon+"-"+sdate;
				//coding of databse 
				try 
				{
					//display.setText("hello");
					Class.forName("com.mysql.jdbc.Driver");
					display.setText("Driver okkk");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/reg?user=root&password=root");  //complete it 
					
					String sql="insert into formdata(name,college,Mobile,Email,Password,DOB,Gender) values(?,?,?,?,?,?,?) ";          //complete it 
					PreparedStatement st=con.prepareStatement(sql);
					st.setString(1,sname);
					st.setString(2,scollege);
					st.setString(3,smob);
					st.setString(4,semail);
					st.setString(5,spass);
					st.setString(6,sDOB);
					st.setString(7,sgender);
					
					
					
					
					st.executeUpdate();
					display.setText("Registration Successfull............");
					
				} 
				catch (Exception e2) 
				{
					System.out.println(e2);
					display.setText("Something went Wrong");
				}
			}
			else
			{
				display.setText("Please Agree Our Term and Conditions");
			}
		}
		else if(c=="Show Data")
		{
			try 
			{
				//display.setText("hello");
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/reg?user=root&password=root");  //complete it 
				
				String sql="select * from formdata";
				Statement stm=con.createStatement();
				ResultSet r=stm.executeQuery(sql);
				while(r.next())
				{
					String name=r.getString("name");
					String email=r.getString("Email");
					String pass=r.getString("Password");
					String Mobile=r.getString("Mobile");
					String college=r.getString("college");
					String dob=r.getString("DOB");
					String gen=r.getString("gender");
					String t="\nName-"+name+"\nEmail--"+email+"\nPassword--"+pass+"\nMobile--"+Mobile+"\n"
							+ "college--"+college+"\nDOB--"+dob+"\nGender--"+gen;
					display.setText(t);
					
				}
				
				
				
			} 
			catch (Exception e2) 
			{
				System.out.println(e2);
				display.setText("Something went Wrong");
			}
		}
		else if(c=="SEARCH")
		{
			try 
			{
				//display.setText("hello");
				Class.forName("com.mysql.jdbc.Driver");
				display.setText("Driver okkk");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/reg?user=root&password=root");  //complete it 
				String s=searchb.getText();
				String sql="select * from formdata where name=?";
				PreparedStatement st=con.prepareStatement(sql);
				st.setString(1,s);
				//Statement stm=con.createStatement();
				ResultSet r=st.executeQuery();
				while(r.next())
				{
					String name=r.getString("name");
					String email=r.getString("Email");
					String pass=r.getString("Password");
					String Mobile=r.getString("Mobile");
					String college=r.getString("college");
					String dob=r.getString("DOB");
					String gen=r.getString("gender");
					String t="\nName-"+name+"\nEmail--"+email+"\nPassword--"+pass+"\nMobile--"+Mobile+"\n"
							+ "college--"+college+"\nDOB--"+dob+"\nGender--"+gen;
					display.setText(t);
					
				}
				
			} 
			catch (Exception e2) 
			{
				System.out.println(e2);
				display.setText("Something went Wrong");
			}
		}
		else if(c=="DELETE") 
		{
			try 
			{
				//display.setText("hello");
				Class.forName("com.mysql.jdbc.Driver");
				display.setText("Driver okkk");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/reg?user=root&password=root");  //complete it 
				String s=deleteb.getText();
				String sql="delete from formdata where name=?";
				PreparedStatement st=con.prepareStatement(sql);
				st.setString(1,s);
				st.executeUpdate();
				display.setText("deleted successfully...............");
			} 
			catch (Exception e2) 
			{
				System.out.println(e2);
				display.setText("Something went Wrong");
			}
		}
		else
		{
			name.setText(null);
			email.setText(null);
			college.setText(null);
			mob.setText(null);
			pass.setText(null);
			searchb.setText(null);
			deleteb.setText(null);
			display.setText(null);
			
			
			
		}
	}
}

