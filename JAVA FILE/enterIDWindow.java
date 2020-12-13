import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.TableView;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class enterIDWindow extends Frame implements ActionListener
{
	public static int ID;
	
	Button but_Enter = new Button("Enter");
	Label lab_AskForID = new Label("Enter user ID");
	Label lab_ErrorMessage = new Label("");
	TextField tf_IDEnter = new TextField();
	ArrayList<Long> exsistingID = new ArrayList<>();
	Button but_Back = new Button("back");
	
	Color gold = new Color(212, 175, 55);
	
	public enterIDWindow()
	{	
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_AskForID);
		lab_AskForID.setBounds(200, 325, 100, 30);
		lab_AskForID.setForeground(gold);
		
		add(tf_IDEnter);
		tf_IDEnter.setBounds(300, 325, 300, 30);
		tf_IDEnter.setForeground(gold);
		tf_IDEnter.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Enter);
		but_Enter.setBounds(650,325,70,30);
		but_Enter.setBackground(gold);
		but_Enter.addActionListener(this);
		
		add(lab_ErrorMessage);
		lab_ErrorMessage.setBounds(300, 375, 300, 30);
		lab_ErrorMessage.setForeground(Color.RED);
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		// connect to database here insted of for loop
		
		for(int i=1;i<=50;i++)
		{
			exsistingID.add((long) i);
		}
		
		setVisible(true);
		setResizable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent aE)
	{
		// TODO Auto-generated method stub
		if(aE.getSource()==but_Back)
		{
			firstWindow fw = new firstWindow();
			fw.setSize(new Dimension(930,660));
			fw.setTitle("Library management system");
			this.setVisible(false);
			fw.setVisible(true);
		}
		
		if(aE.getSource()==but_Enter)
		{
			String iDText = tf_IDEnter.getText();
			
			try
			{
				String id;
				id =iDText;
				int flag=0;
				ID=(int) Long.parseLong(id);
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

			 
			        String sql="SELECT C_id FROM customers WHERE C_id="+id;

			        PreparedStatement st= con.prepareStatement(sql);
			        
			        ResultSet rs=st.executeQuery(sql);
			        
					while(rs.next()) 
					{                                
						flag=1;
					}
			        
						
				}
				catch(Exception ex) {}
				
				if(flag!=0)
				{
					issueSubmitWindow ISW =new issueSubmitWindow(ID);
					System.out.println(ID);
					ISW.setSize(new Dimension(930,660));
					ISW.setTitle("Library management system");
					this.setVisible(false);
					ISW.setVisible(true);
				}
				else
				{
					lab_ErrorMessage.setText("ID not found!");
				}
			}
			catch(Exception e)
			{
				lab_ErrorMessage.setText("Invalid ID! use of characters!");
			}
		}
	}
	
	

	public static void main(String args[])
	{
		/*try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
		
		}
		catch(Exception e) {}*/
		firstWindow FW =new firstWindow();
		FW.setSize(new Dimension(930,660));
		FW.setTitle("Library management system");
		FW.setVisible(true);
	}
}

class firstWindow extends Frame implements ActionListener
{	
	Button but_ExistingUser = new Button("EXISTING USER");
	Button but_NewUser = new Button("NEW USER");
	Button but_GetUserInfo = new Button("USER INFO");
	Button but_AddNewBook = new Button("ADD NEW BOOK");
	Button but_CustomerRecord = new Button("CUSTOMER RECORD");
	Button but_BookRecord = new Button("BOOK RECORD");
	
	Label lab_Welcome = new Label("Welcome to Library of VESIT");
	
	
	Color gold = new Color(212, 175, 55);
	
	public firstWindow()
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
				
		add(lab_Welcome);
		lab_Welcome.setBounds(150, 150, 700, 60);
		lab_Welcome.setBackground(gold);
		lab_Welcome.setForeground(Color.WHITE);
		lab_Welcome.setFont(new Font("Calibari",Font.BOLD,50));
		
		add(but_ExistingUser);
		but_ExistingUser.setBounds(385,275,150,35);
		but_ExistingUser.setBackground(gold);
		but_ExistingUser.addActionListener(this);
		
		add(but_NewUser);
		but_NewUser.setBounds(385,325,150,35);
		but_NewUser.setBackground(gold);
		but_NewUser.addActionListener(this);

		add(but_GetUserInfo);
		but_GetUserInfo.setBounds(385,375,150,35);
		but_GetUserInfo.setBackground(gold);
		but_GetUserInfo.addActionListener(this);
		
		add(but_AddNewBook);
		but_AddNewBook.setBounds(385,425,150,35);
		but_AddNewBook.setBackground(gold);
		but_AddNewBook.addActionListener(this);
		
		add(but_CustomerRecord);
		but_CustomerRecord.setBounds(385,475,150,35);
		but_CustomerRecord.setBackground(gold);
		but_CustomerRecord.addActionListener(this);
		
		add(but_BookRecord);
		but_BookRecord.setBounds(385,525,150,35);
		but_BookRecord.setBackground(gold);
		but_BookRecord.addActionListener(this);
				
		setVisible(true);
		setResizable(false);
	}	
	
	@Override
	public void actionPerformed(ActionEvent aE)
	{
		// TODO Auto-generated method stub
		
		if(aE.getSource()==but_ExistingUser)
		{
			enterIDWindow EIDW =new enterIDWindow();
			EIDW.setSize(new Dimension(930,660));
			EIDW.setTitle("Library management system");
			this.setVisible(false);
			EIDW.setVisible(true);
		}
		
		else if(aE.getSource()==but_NewUser)
		{
			NewUser nW = new NewUser();
			nW.setSize(new Dimension(930,660));
			nW.setTitle("Library management system");
			this.setVisible(false);
			nW.setVisible(true);
			
		}
		else if(aE.getSource()==but_GetUserInfo)
		{
			GetUserData GUD = new GetUserData();
			GUD.setSize(new Dimension(930,660));
			GUD.setTitle("Library management system");
			this.setVisible(false);
			GUD.setVisible(true);
		}
		else if(aE.getSource()==but_AddNewBook)
		{
			NewBook NB = new NewBook();
			NB.setSize(new Dimension(930,660));
			NB.setTitle("Library management system");
			this.setVisible(false);
			NB.setVisible(true);
		}
		else if(aE.getSource()==but_CustomerRecord)
		{
			customerRecord CR = new customerRecord();
			CR.setSize(new Dimension(930,660));
			CR.setTitle("Library management system");
			this.setVisible(false);
			CR.setVisible(true);
		}
		else if(aE.getSource()==but_BookRecord)
		{
			bookRecord BR = new bookRecord();
			BR.setSize(new Dimension(930,660));
			BR.setTitle("Library management system");
			this.setVisible(false);
			BR.setVisible(true);
		}
	}
}


class issueSubmitWindow extends Frame implements ActionListener
{
	Button but_Submit = new Button("Submit book");
	Button but_Issue = new Button("Issue book");
	Button but_ReIssue = new Button("Reissue book");
	Button but_GetHistory = new Button("Get record");
	
	Button but_Back = new Button("back");
	
	int uID;
	
	Label lbl_Choice = new Label("What Would You Like To Do Today?");
	
	Color gold = new Color(212, 175, 55);
	
	public issueSubmitWindow(int Id)
	{
		uID = Id;
		
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(but_Submit);
		but_Submit.setBounds(385,275,150,35);
		but_Submit.setBackground(gold);
		but_Submit.addActionListener(this);
		
		add(but_Issue);
		but_Issue.setBounds(385,325,150,35);
		but_Issue.setBackground(gold);
		but_Issue.addActionListener(this);
		
		add(but_ReIssue);
		but_ReIssue.setBounds(385,375,150,35);
		but_ReIssue.setBackground(gold);
		but_ReIssue.addActionListener(this);
		
		add(lbl_Choice);
		lbl_Choice.setBounds(35, 150, 875, 60);
		lbl_Choice.setForeground(gold);
		lbl_Choice.setFont(new Font("Calibari",Font.BOLD,50));
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);

		add(but_GetHistory);
		but_GetHistory.setBounds(385,425,150,35);
		but_GetHistory.setBackground(gold);
		but_GetHistory.addActionListener(this);
		
		setVisible(true);
		setResizable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent aE)
	{
		// TODO Auto-generated method stub
		if(aE.getSource()==but_Back)
		{
			enterIDWindow EIDW = new enterIDWindow();
			EIDW.setSize(new Dimension(930,660));
			EIDW.setTitle("Library management system");
			this.setVisible(false);
			EIDW.setVisible(true);
		}
		
		if(aE.getSource()==but_Submit)
		{
			SubmitBookWindow ISW =new SubmitBookWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
		else if(aE.getSource()==but_Issue)
		{
			IssueBookWindow ISW =new IssueBookWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
		else if(aE.getSource()==but_ReIssue)
		{
			ReIssueBookWindow ISW =new ReIssueBookWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
		else if(aE.getSource()==but_GetHistory)
		{
			getHistory GH =new getHistory(uID);
			GH.setSize(new Dimension(930,660));
			GH.setTitle("Library management system");
			this.setVisible(false);
			GH.setVisible(true);
		}
	}
}

//************************SUBMIT WINDOW******************

class SubmitBookWindow extends Frame implements ActionListener
{
	Button but_Submit = new Button("Submit");
	Label lab_AskForID = new Label("Enter book ID");
	Label lab_AskForRate = new Label("Enter book Rating");
	Label lab_Message = new Label("");
	TextField tf_IDEnter = new TextField("");
	TextField tf_BookRating = new TextField("");
	
	Button but_Back = new Button("back");

	int uID;
	
	Color gold = new Color(212, 175, 55);
	
	public SubmitBookWindow(int userId)
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_AskForID);
		lab_AskForID.setBounds(200, 325, 100, 30);
		lab_AskForID.setForeground(gold);
		
		add(lab_AskForRate);
		lab_AskForRate.setBounds(200, 400, 100, 30);
		lab_AskForRate.setForeground(gold);
		
		add(tf_IDEnter);
		tf_IDEnter.setBounds(300, 325, 300, 30);
		tf_IDEnter.setForeground(gold);
		tf_IDEnter.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_BookRating);
		tf_BookRating.setBounds(300, 400, 300, 30);
		tf_BookRating.setForeground(gold);
		tf_BookRating.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Submit);
		but_Submit.setBounds(650,325,70,30);
		but_Submit.setBackground(gold);
		but_Submit.addActionListener(this);
		
		add(lab_Message);
		lab_Message.setBounds(300, 375, 300, 30);
		lab_Message.setForeground(Color.RED);
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		// connect to database here insted of for loop
		
		uID=userId;
		
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent aE) 
	{
		if(aE.getSource()==but_Back)
		{
			issueSubmitWindow ISW =new issueSubmitWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
		
		if(aE.getSource()==but_Submit)
		{
			String iDText = tf_IDEnter.getText();
			
			try
			{
				int book_ID = (int)Long.parseLong(iDText);
				float rate= Float.parseFloat(tf_BookRating.getText());  
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
					String book=Integer.toString(book_ID);
					String customer=Integer.toString(uID);
					String sql="INSERT INTO history(B_id,C_id,action) VALUES (?,?,?)";
					String sql2="DELETE FROM current where B_id="+book+" and C_id="+customer ;
					String sql3="INSERT INTO ratings (B_id,C_id,rate) VALUES (?,?,?)";
					
					
					PreparedStatement ps=con.prepareStatement(sql);
					PreparedStatement ps2=con.prepareStatement(sql2);
					PreparedStatement ps3=con.prepareStatement(sql3);
					
					String act="Submitted";
					
					ps.setInt(1, book_ID);
					ps.setInt(2, uID);
					ps.setString(3,act);
					
					ps3.setInt(1, book_ID);
					ps3.setInt(2, uID);
					ps3.setFloat(3, rate);
					
					
					ps.executeUpdate();
					ps2.executeUpdate();
					ps3.executeUpdate();
				}
				catch(Exception ex) {}
				
				
			}
			catch(Exception e)
			{
				lab_Message.setText("Invalid ID! use of characters!");
			}
			
			SubmitBookWindow ISW =new SubmitBookWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
	}
}



//************************ISSUE WINDOW*********************************


class IssueBookWindow extends Frame implements ActionListener
{
	Button but_Issue = new Button("Issue");
	Label lab_AskForID = new Label("Enter book ID");
	Label lab_Message = new Label("");
	TextField tf_IDEnter = new TextField("");

	Button but_Back = new Button("back");
	
	int userId;
	Color gold = new Color(212, 175, 55);
	
	public IssueBookWindow(int UserId)
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_AskForID);
		lab_AskForID.setBounds(200, 325, 100, 30);
		lab_AskForID.setForeground(gold);
		
		add(tf_IDEnter);
		tf_IDEnter.setBounds(300, 325, 300, 30);
		tf_IDEnter.setForeground(gold);
		tf_IDEnter.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Issue);
		but_Issue.setBounds(650,325,70,30);
		but_Issue.setBackground(gold);
		but_Issue.addActionListener(this);
		
		add(lab_Message);
		lab_Message.setBounds(300, 375, 300, 30);
		lab_Message.setForeground(Color.RED);
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		// connect to database here insted of for loop
		
		
		
		userId=UserId;
		
		//System.out.println(userId+ UserId+"here");
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent aE) 
	{
		if(aE.getSource()==but_Back)
		{
			issueSubmitWindow ISW =new issueSubmitWindow(userId);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}

		if(aE.getSource()==but_Issue)
		{
			String B_id = tf_IDEnter.getText();
			
			try
			{
				//BOOK ID CONVERTED
				 int book_ID = (int)Long.parseLong(B_id);
				
				
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
				
				String sql="INSERT INTO history(B_id,C_id,action) VALUES (?,?,?)";
				String sql2="INSERT INTO current(B_id,C_id) VALUES (?,?)";
				PreparedStatement ps=con.prepareStatement(sql);
				PreparedStatement ps2=con.prepareStatement(sql2);
				String act="Issued";
				
				ps.setInt(1, book_ID);
				ps.setInt(2, userId);
				ps.setString(3,act);
				ps.executeUpdate();
				
				ps2.setInt(1,book_ID);
				ps2.setInt(2, userId);
				ps2.executeUpdate();
				}
				
			catch(Exception ex) {}


				
			}
			catch(Exception e)
			{
				lab_Message.setText("Invalid ID! use of characters!");
			}
			
			IssueBookWindow IBW =new IssueBookWindow(userId);
			IBW.setSize(new Dimension(930,660));
			IBW.setTitle("Library management system");
			this.setVisible(false);
			IBW.setVisible(true);
		}
	}
}

class ReIssueBookWindow extends Frame implements ActionListener
{
	ArrayList<Long> availableBooks = new ArrayList<>();
	ArrayList<Long> booksUserHas = new ArrayList<>();
	
	Button but_Issue = new Button("re Issue");
	Label lab_AskForID = new Label("Enter book ID");
	Label lab_Message = new Label("");
	TextField tf_IDEnter = new TextField("");
	
	Button but_Back = new Button("back");

	int userID;
	
	Color gold = new Color(212, 175, 55);
	
	public ReIssueBookWindow(int uId)
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_AskForID);
		lab_AskForID.setBounds(200, 325, 100, 30);
		lab_AskForID.setForeground(gold);
		
		add(tf_IDEnter);
		tf_IDEnter.setBounds(300, 325, 300, 30);
		tf_IDEnter.setForeground(gold);
		tf_IDEnter.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Issue);
		but_Issue.setBounds(650,325,70,30);
		but_Issue.setBackground(gold);
		but_Issue.addActionListener(this);
		
		add(lab_Message);
		lab_Message.setBounds(300, 375, 300, 30);
		lab_Message.setForeground(Color.RED);
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		// connect to database here insted of for loop

		userID=uId;
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent aE) 
	{
		if(aE.getSource()==but_Back)
		{
			issueSubmitWindow ISW =new issueSubmitWindow(userID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}

		if(aE.getSource()==but_Issue)
		{
			String iDText = tf_IDEnter.getText();
			
			try
			{
				int bookID = (int) Long.parseLong(iDText);
				
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
				
				String book=Integer.toString(bookID);
				String customer=Integer.toString(userID);
				
				String sql="INSERT INTO history(B_id,C_id,action) VALUES (?,?,?)";
				String sql2="INSERT INTO current(B_id,C_id) VALUES (?,?)";
				String sql3="DELETE FROM current where B_id="+iDText+" and C_id="+customer ;
				PreparedStatement ps=con.prepareStatement(sql);
				PreparedStatement ps2=con.prepareStatement(sql2);
				PreparedStatement ps3=con.prepareStatement(sql3);
				
				String act="Re-Issued";
				
				ps.setInt(1, bookID);
				ps.setInt(2, userID);
				ps.setString(3,act);
				ps.executeUpdate();
				ps3.executeUpdate();
				ps2.setInt(1,bookID);
				ps2.setInt(2, userID);
				ps2.executeUpdate();
				
				
				}
				
				catch(Exception ex) {}
				
				
			}
			catch(Exception e)
			{
				lab_Message.setText("Invalid ID! use of characters!");
			}
			
			ReIssueBookWindow RIBW =new ReIssueBookWindow(userID);
			RIBW.setSize(new Dimension(930,660));
			RIBW.setTitle("Library management system");
			this.setVisible(false);
			RIBW.setVisible(true);
		}
	}
}

class NewUser extends Frame implements ActionListener
{
	String name;
	String phoneNumber;
	String address;
	String DOB;
	
	Label lbl_Choice = new Label("Welcome Welcome!");
	Label lab_EnterName = new Label("Name : ");
	Label lab_PhoneNumber = new Label("Phone number : ");
	Label lab_Address = new Label("Address : ");
	Label lab_DOB = new Label("D.O.B. : ");
	
	TextField tf_EnterName = new TextField("");
	TextField tf_PhoneNumber = new TextField("");
	TextField tf_Address = new TextField("");
	TextField tf_DOB = new TextField("");
	
	Button but_Add = new Button("Add user");
	
	Button but_Back = new Button("back");
	
	Color gold = new Color(212, 175, 55);
	
	public NewUser()
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lbl_Choice);
		lbl_Choice.setBounds(250, 75, 450, 60);
		lbl_Choice.setForeground(gold);
		lbl_Choice.setFont(new Font("Calibari",Font.PLAIN,50));

		add(lab_EnterName);
		lab_EnterName.setBounds(200, 175, 100, 30);
		lab_EnterName.setForeground(gold);
		lab_EnterName.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_EnterName);
		tf_EnterName.setBounds(350, 175, 300, 30);
		tf_EnterName.setForeground(gold);
		tf_EnterName.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_PhoneNumber);
		lab_PhoneNumber.setBounds(200, 225, 150, 30);
		lab_PhoneNumber.setForeground(gold);
		lab_PhoneNumber.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_PhoneNumber);
		tf_PhoneNumber.setBounds(350, 225, 300, 30);
		tf_PhoneNumber.setForeground(gold);
		tf_PhoneNumber.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Address);
		lab_Address.setBounds(200, 275, 100, 30);
		lab_Address.setForeground(gold);
		lab_Address.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Address);
		tf_Address.setBounds(350, 275, 300, 30);
		tf_Address.setForeground(gold);
		tf_Address.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_DOB);
		lab_DOB.setBounds(200, 325, 100, 30);
		lab_DOB.setForeground(gold);
		lab_DOB.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_DOB);
		tf_DOB.setBounds(350, 325, 300, 30);
		tf_DOB.setForeground(gold);
		tf_DOB.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Add);
		but_Add.setBounds(385,500,130,30);
		but_Add.setBackground(gold);
		but_Add.addActionListener(this);
		
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		setVisible(true);
		setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==but_Back)
		{
			firstWindow FW =new firstWindow();
			FW.setSize(new Dimension(930,660));
			FW.setTitle("Library management system");
			this.setVisible(false);
			FW.setVisible(true);
		}
		else if(e.getSource()==but_Add) {
			

			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
			
			String name = tf_EnterName.getText();
			String ph =tf_PhoneNumber.getText();
			String adr=tf_Address.getText();
			String dob=tf_DOB.getText();
			
			String sql="INSERT INTO customers(Cname,phone,address,dob) VALUES (?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,ph);
			ps.setString(3,adr);
			ps.setString(4, dob);
			ps.executeUpdate();
			
			}
			catch(Exception ex) {}
	
			NewUser NU =new NewUser();
			NU.setSize(new Dimension(930,660));
			NU.setTitle("Library management system");
			this.setVisible(false);
			NU.setVisible(true);
		}
	}
	
}


class GetUserData extends Frame implements ActionListener
{
	long uID;
	
	String name;
	String phoneNumber;
	String address;
	String DOB;
	
	TextField tf_EnterID = new TextField("");
	Label lab_ErrorMessage = new Label("");
	Label lab_AskForID = new Label("Enter your ID");
	
	Button but_GetInfo = new Button("Get info");
	Button but_DeleteUser = new Button("Remove user");
	
	Label lab_EnterName = new Label("Name : ");
	Label lab_PhoneNumber = new Label("Phone number : ");
	Label lab_Address = new Label("Address : ");
	Label lab_DOB = new Label("D.O.B. : ");
	
	Button but_Back = new Button("back");
	
	Color gold = new Color(212, 175, 55);
	
	ArrayList<Long> exsistingID = new ArrayList<>();
	
	public GetUserData()
	{	
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_AskForID);
		lab_AskForID.setBounds(200, 125, 100, 30);
		lab_AskForID.setForeground(gold);
		
		add(tf_EnterID);
		tf_EnterID.setBounds(300, 125, 300, 30);
		tf_EnterID.setForeground(gold);
		tf_EnterID.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_GetInfo);
		but_GetInfo.setBounds(650,125,70,30);
		but_GetInfo.setBackground(gold);
		but_GetInfo.addActionListener(this);
		
		add(but_DeleteUser);
		but_DeleteUser.setBounds(400,550,90,30);
		but_DeleteUser.setBackground(gold);
		but_DeleteUser.addActionListener(this);
		
		add(lab_ErrorMessage);
		lab_ErrorMessage.setBounds(300, 150, 300, 30);
		lab_ErrorMessage.setForeground(Color.RED);
		
		add(lab_EnterName);
		lab_EnterName.setBounds(200, 275, 400, 30);
		lab_EnterName.setForeground(gold);
		lab_EnterName.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_PhoneNumber);
		lab_PhoneNumber.setBounds(200, 325, 350, 30);
		lab_PhoneNumber.setForeground(gold);
		lab_PhoneNumber.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Address);
		lab_Address.setBounds(200, 375, 1000, 30);
		lab_Address.setForeground(gold);
		lab_Address.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_DOB);
		lab_DOB.setBounds(200, 425, 500, 30);
		lab_DOB.setForeground(gold);
		lab_DOB.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		//connect to database here
		
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub

		if(e.getSource()==but_Back)
		{
			firstWindow FW =new firstWindow();
			FW.setSize(new Dimension(930,660));
			FW.setTitle("Library management system");
			this.setVisible(false);
			FW.setVisible(true);
		}
		else if(e.getSource()==but_DeleteUser)
		{
			try
			{
				//connect here
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

					String sql=" DELETE FROM customers WHERE C_id="+tf_EnterID.getText();
					Statement st=con.createStatement();
					
					st.execute(sql);
				}
				catch(Exception ex)
				{}
				
				
				
				uID = Integer.parseInt(tf_EnterID.getText());
				
				GetUserData GUD = new GetUserData();
				GUD.setSize(new Dimension(930,660));
				GUD.setTitle("Library management system");
				this.setVisible(false);
				GUD.setVisible(true);
			}
			catch(Exception ex)
			{
				tf_EnterID.setText("First enter ID here");
			}
		}
		
		if(e.getSource()==but_GetInfo)
		{
			try
			{
				uID = Long.parseLong(tf_EnterID.getText());
				
				
				String id=tf_EnterID.getText();
				
				try
				{

					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");
					
					
					String sql="SELECT* FROM customers WHERE C_id="+id;
					Statement st=con.createStatement();
					System.out.println(id);
					
					ResultSet rs=st.executeQuery(sql);
					
					while(rs.next())
					{
						System.out.println(rs.getString(2));
						System.out.println(rs.getString(3));
						System.out.println(rs.getString(4));
						System.out.println(rs.getString(5));
						name=rs.getString(2);
						phoneNumber=rs.getString(3);
						address=rs.getString(4);
						DOB=rs.getString(5);
					}
					
					/*name=rs.getString(2);
					phoneNumber=rs.getString(3);
					address=rs.getString(4);
					DOB=rs.getString(5);*/
					
					
					lab_EnterName.setText  ("Name                : "+ name);
					lab_PhoneNumber.setText("Phone number  : " + phoneNumber);
					lab_Address.setText    ("Address            : " + address);
					lab_DOB.setText    ("D.O.B.              : " + DOB);
				
					
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				
					/*name = "Sample name";
					phoneNumber = "01234567";
					address = "Mumbai";
					DOB = "30 Febuary 2001";*/
					
				
				

			}catch(Exception ex)
			{
				lab_ErrorMessage.setText("Invalid ID");
			}
		}
		
		// connect database here
		
	}	
}

class NewBook extends Frame implements ActionListener
{

	
	Label lbl_Choice = new Label("New Books Hurray!");
	
	Label lab_Name = new Label("Name : ");
	Label lab_Author = new Label("Author : ");
	Label lab_Publisher = new Label("Publisher : ");
	Label lab_Category = new Label("Category : ");
	Label lab_Units = new Label("Units : ");
	
	TextField tf_Name = new TextField("");
	TextField tf_Author = new TextField("");
	TextField tf_Publisher = new TextField("");
	TextField tf_Category = new TextField("");
	TextField tf_Units = new TextField("");
	
	Button but_Add = new Button("add Book");
	
	Button but_Back = new Button("back");

	Color gold = new Color(212, 175, 55);

	
	public NewBook()
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
				
		add(lbl_Choice);
		lbl_Choice.setBounds(250, 75, 450, 60);
		lbl_Choice.setForeground(gold);
		lbl_Choice.setFont(new Font("Calibari",Font.PLAIN,50));
		
		add(lab_Name);
		lab_Name.setBounds(200, 175, 100, 30);
		lab_Name.setForeground(gold);
		lab_Name.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Name);
		tf_Name.setBounds(350, 175, 300, 30);
		tf_Name.setForeground(gold);
		tf_Name.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Author);
		lab_Author.setBounds(200, 225, 100, 30);
		lab_Author.setForeground(gold);
		lab_Author.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Author);
		tf_Author.setBounds(350, 225, 300, 30);
		tf_Author.setForeground(gold);
		tf_Author.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Publisher);
		lab_Publisher.setBounds(200, 275, 100, 30);
		lab_Publisher.setForeground(gold);
		lab_Publisher.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Publisher);
		tf_Publisher.setBounds(350, 275, 300, 30);
		tf_Publisher.setForeground(gold);
		tf_Publisher.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Category);
		lab_Category.setBounds(200, 325, 100, 30);
		lab_Category.setForeground(gold);
		lab_Category.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Category);
		tf_Category.setBounds(350, 325, 300, 30);
		tf_Category.setForeground(gold);
		tf_Category.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(lab_Units);
		lab_Units.setBounds(200, 375, 100, 30);
		lab_Units.setForeground(gold);
		lab_Units.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(tf_Units);
		tf_Units.setBounds(350, 375, 300, 30);
		tf_Units.setForeground(gold);
		tf_Units.setFont(new Font("Calibari",Font.PLAIN,18));
		
		add(but_Add);
		but_Add.setBounds(385,500,130,30);
		but_Add.setBackground(gold);
		but_Add.addActionListener(this);
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
		

		String name;
		String author;
		String publisher;
		String category;
		int units = 0;
		
		if(e.getSource()==but_Back)
		{
			firstWindow FW =new firstWindow();
			FW.setSize(new Dimension(930,660));
			FW.setTitle("Library management system");
			this.setVisible(false);
			FW.setVisible(true);
		}
		
		else if(e.getSource()==but_Add) 
		{
			

			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

			name = tf_Name.getText();
			author = tf_Author.getText();
			publisher = tf_Publisher.getText();
			category = tf_Category.getText();
			try
			{
				units =  (int)Long.parseLong(tf_Units.getText());
			}
			catch(Exception ex)
			{
				System.out.println("err is here");
			}
			
			
			String sql="INSERT INTO books(Author,Publisher,Category,Units,Bname) VALUES (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1,author);
			ps.setString(2,publisher);
			ps.setString(3, category);
			ps.setInt(4, units);
			ps.setString(5,name);
			ps.executeUpdate();
			
			}
			
		catch(Exception ex) {}

			NewBook NB =new NewBook();
			NB.setSize(new Dimension(930,660));
			NB.setTitle("Library management system");
			this.setVisible(false);
			NB.setVisible(true);
	}	
}
}



//*********************HISTORY*************************

class getHistory extends Frame implements ActionListener
{
	
	Color gold = new Color(212, 175, 55);     
	Button but_Back = new Button("back");
	int uID;
	
	Label lab_ID = new Label("Book ID");
	Label lab_Action = new Label("Action");
	Label lab_Date = new Label("Date of transaction");
	
	public getHistory(int ID)
	{
		uID = ID;
		
		JTable jt=new JTable(4,5);
		
		
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.black);
		
		String id=Integer.toString(ID) ;
		
		add(lab_ID);
		lab_ID.setBounds(150, 175, 100, 10);
		lab_ID.setForeground(gold);
		
		add(lab_Action);
		lab_Action.setBounds(375, 175, 100, 10);
		lab_Action.setForeground(gold);
		
		add(lab_Date);
		lab_Date.setBounds(550, 175, 150, 10);
		lab_Date.setForeground(gold);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

			String sql="SELECT B_id,ACTION,tdate FROM history WHERE C_id="+id;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
		
			jt.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(Exception ex)
		{}
    	
    	jt.setForeground(gold);
    	jt.setBackground(Color.BLACK);
    	jt.setFont(new Font("Calibari",Font.PLAIN,18));
    	jt.setBounds(100,200,600,1000);          
    	JScrollPane sp=new JScrollPane(jt);    
        
    	jt.setGridColor(gold);
    	
    	add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
    	add(jt);
    	add(sp);
	}
	@Override
	public void actionPerformed(ActionEvent aE) 
	{
		// TODO Auto-generated method stub
		if(aE.getSource()==but_Back)
		{
			issueSubmitWindow ISW = new issueSubmitWindow(uID);
			ISW.setSize(new Dimension(930,660));
			ISW.setTitle("Library management system");
			this.setVisible(false);
			ISW.setVisible(true);
		}
	}
	
}

//*********************CUSTOMER RECORD*************************


class customerRecord extends Frame implements ActionListener
{
	Color gold = new Color(212, 175, 55);
	Button but_Back = new Button("back");
	JTable jt=new JTable(4,4);
	
	Label lab_ID = new Label("ID");
	Label lab_Name = new Label("Name");
	Label lab_Number = new Label("Phone no.");
	Label lab_Date = new Label("Date of reg");
	
	customerRecord()
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.black);
		
		add(lab_ID);
		lab_ID.setBounds(150, 175, 100, 10);
		lab_ID.setForeground(gold);
		
		add(lab_Name);
		lab_Name.setBounds(275, 175, 100, 10);
		lab_Name.setForeground(gold);
		
		add(lab_Number);
		lab_Number.setBounds(400, 175, 100, 10);
		lab_Number.setForeground(gold);
		
		add(lab_Date);
		lab_Date.setBounds(550, 175, 100, 10);
		lab_Date.setForeground(gold);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

			String sql="SELECT C_id,Cname,phone,DOJ FROM customers";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			do
			{
				
				jt.setModel(DbUtils.resultSetToTableModel(rs));
			}while(rs.next());
		
			
			
		}
		catch(Exception ex)
		{}
    	
    	jt.setForeground(gold);
    	jt.setBackground(Color.BLACK);
    	jt.setFont(new Font("Calibari",Font.PLAIN,18));
    	jt.setBounds(100,200,600,2000);          
    	JScrollPane sp=new JScrollPane(jt);    
        
    	jt.setGridColor(gold);
    	
    	add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
    	add(jt);
    	add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent aE) 
	{
		// TODO Auto-generated method stub
		if(aE.getSource()==but_Back)
		{
			firstWindow fw = new firstWindow();
			fw.setSize(new Dimension(930,660));
			fw.setTitle("Library management system");
			this.setVisible(false);
			fw.setVisible(true);
		}
	}
	
}




//*********************BOOK RECORD*************************


class bookRecord extends Frame implements ActionListener
{
	Color gold = new Color(212, 175, 55);
	Button but_Back = new Button("back");
	JTable jt=new JTable(4,5);
	
	Label lab_ID = new Label("ID");
	Label lab_Name = new Label("Name");
	Label lab_Author = new Label("Author");
	Label lab_Units = new Label("Units");
	
	bookRecord()
	{
		addWindowListener(new myWindowAdapter());
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(lab_ID);
		lab_ID.setBounds(150, 175, 100, 10);
		lab_ID.setForeground(gold);
		
		add(lab_Name);
		lab_Name.setBounds(275, 175, 100, 10);
		lab_Name.setForeground(gold);
		
		add(lab_Author);
		lab_Author.setBounds(400, 175, 100, 10);
		lab_Author.setForeground(gold);
		
		add(lab_Units);
		lab_Units.setBounds(550, 175, 50, 10);
		lab_Units.setForeground(gold);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","bhavya");

			String sql="SELECT B_id,Bname,Author,Units FROM books";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			do
			{
				
				jt.setModel(DbUtils.resultSetToTableModel(rs));
			}while(rs.next());
		
			
			
		}
		catch(Exception ex)
		{}
    	
    	jt.setForeground(gold);
    	jt.setBackground(Color.BLACK);
    	jt.setFont(new Font("Calibari",Font.PLAIN,18));
    	jt.setBounds(100,200,600,1100);          
    	JScrollPane sp=new JScrollPane(jt);    
        
    	jt.setGridColor(gold);
		
		
		
		add(but_Back);
		but_Back.setBounds(40,70,70,30);
		but_Back.setBackground(gold);
		but_Back.addActionListener(this);
		
		

    	add(jt);
    	add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent aE) {
		// TODO Auto-generated method stub
		if(aE.getSource()==but_Back)
		{
			firstWindow fw = new firstWindow();
			fw.setSize(new Dimension(930,660));
			fw.setTitle("Library management system");
			this.setVisible(false);
			fw.setVisible(true);
		}
	}
	
}

class myWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
}
