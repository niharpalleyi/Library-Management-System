import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class nihar1 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JLabel username=new JLabel(" USERNAME:   "),password=new JLabel(" PASSWORD:   "),set=new JLabel("Public use Regd/Empl Id as usernameAndPassword");
	JTextField userna=new JTextField(),pass=new JTextField();
	
	static final String user="SYSTEM";
	static final String pss="sibu";
	static final String driver="oracle.jdbc.driver.OracleDriver";
	static final String url="jdbc:oracle:thin:@localhost:1521:orcl";
	static Scanner sc=new Scanner(System.in);
	public nihar1(){
	JButton sub1=new JButton("SUBMIT");
	sub1.addActionListener(this);
	setLayout(new GridLayout(3,1));
	add(username);
	add(userna);
	add(password);
	add(pass);
	add(set);
	add(sub1);
	setSize(600,200);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(400,300);
	dispose();
	}
	public static void main(String[] args) throws Exception
	{
		new nihar1().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String uss=userna.getText();
		String pss=pass.getText();
		if(uss.equalsIgnoreCase("administrator")&&pss.equals("nihar"))
		{
			JOptionPane.showMessageDialog(this,"Correct\nYou are logged in as Administrator");
			dispose();
			setVisible(false);
			Administrator();
		}
		if(uss.equals(pss))
		{
			JOptionPane.showMessageDialog(this,"You are logged in as Public User");
			dispose();
			setVisible(false);
			publicuser(uss);
		}
		else
			JOptionPane.showMessageDialog(this,"incorrect");
	}
	public static void Administrator()
	{
		System.out.println("-------------------------------Hello-------------------------------");
		System.out.println("1.ADD\n2.MODIFY\n3.DISPLAY\n4.DELETE\n5.SEARCH\n6.STATUS\n7.EXIT");
		System.out.println("Choose the operation you want to perform");
		int option=sc.nextInt();
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pss);
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet set;
		while(option!=7)
		{		
			/////////////////////////////////////ADD
			if(option==1)
			{
				System.out.println("Enter new Data to add");
				System.out.print("Book_id: ");
				int Bookid=sc.nextInt();
				sc.nextLine();
				
				System.out.print("Book_name: ");
				String Bookname=sc.nextLine();
				System.out.print("Book_author: ");
				String Bookauthor=sc.nextLine();
				System.out.print("Book_publisher: ");
				String Bookpublisher=sc.nextLine();
				System.out.print("Book_Available: ");
				int Bookavail=sc.nextInt();
				System.out.print("Book_issued: ");
				int Bookissue=sc.nextInt();
				System.out.print("Book_price: ");
				float Bookprice=sc.nextFloat();
				String init="Insert into library values("+Bookid+",\'"+Bookname+"\',\'"+Bookauthor+"\',\'"+Bookpublisher+"\',"+Bookavail+","+Bookissue+","+Bookprice+")";
				set=st.executeQuery(init);
				System.out.println("DATA ADDED");
			}
			
			///////////////////////////////////////modify
			else if(option==2)
			{
				String quer="select * from library where BOOK_ID=";
				System.out.print("Enter the Book Id: ");
				String enter=sc.next();
				String res=quer+enter;
				set=st.executeQuery(res.trim());

				System.out.println("BOOK ID    BOOK NAME      BOOK AUTHOR       BOOK PUBLISHER    BOOK AVAILABLE    BOOK ISSUED     BOOK PRICE");
				set.next();
				System.out.print(set.getInt(1)+"          ");
				System.out.print(set.getString(2)+"         ");
				System.out.print(set.getString(3)+"          ");
				System.out.print(set.getString(4)+"              ");
				System.out.print(set.getInt(5)+"                ");
				System.out.print(set.getInt(6)+"                ");
				System.out.println(set.getFloat(7));

				String query=("select BOOK_ID,BOOK_TITLE,BOOK_AUTH,BOOK_PUBLISHER,BOOK_AVAIL,BOOK_ISSUED,BOOK_PRICE from library where BOOK_ID=");
				String result=query+enter;

				System.out.println("Enter new Data to update");
				System.out.print("Book_id: ");
				int Bookid=sc.nextInt();
				sc.nextLine();
				System.out.print("Book_name: ");
				String Bookname=sc.nextLine();
				System.out.print("Book_author: ");
				String Bookauthor=sc.nextLine();
				System.out.print("Book_publisher: ");
				String Bookpublisher=sc.nextLine();
				System.out.print("Book_Available: ");
				int Bookavail=sc.nextInt();
				System.out.print("Book_issued: ");
				int Bookissue=sc.nextInt();
				System.out.print("Book_price: ");
				float Bookprice=sc.nextFloat();

				set=st.executeQuery(result);
				set.absolute(1);
				set.updateInt(1,Bookid);
				set.updateString(2,Bookname);
				set.updateString(3,Bookauthor);
				set.updateString(4,Bookpublisher);
				set.updateInt(5,Bookavail);
				set.updateInt(6,Bookissue);
				set.updateFloat(7,Bookprice);
				set.updateRow();
				System.out.println("DATA UPDATED");
			}
			
			/////////////////////////////////display
			else if(option==3)
			{
				set=st.executeQuery("SELECT * FROM library");
				System.out.println("BOOK ID    BOOK NAME     BOOK AUTHOR         BOOK PUBLISHER    BOOK AVAILABLE    BOOK ISSUED         BOOK PRICE");
				while(set.next())
				{
					System.out.print(set.getInt(1)+"          ");
					System.out.print(set.getString(2)+"         ");
					System.out.print(set.getString(3)+"          ");
					System.out.print(set.getString(4)+"              ");
					System.out.print(set.getInt(5)+"                ");
					System.out.print(set.getInt(6)+"                ");
					System.out.println(set.getFloat(7));
				}
			}
			
			///////////////////////////////delete
			else if(option==4)
			{
				System.out.print("Enter the Book Id to Delete: ");
				int id=sc.nextInt();
				String fin="Delete from library where BOOK_ID="+id;
				set=st.executeQuery(fin);
				System.out.println("Data deleted");
			}
			
			/////////////////////////////search
			else if(option==5)
			{
				System.out.println("Enter the 1.Book Id or 2.Publisher Name to search");
				int opt=sc.nextInt();
				if(opt==1)
				{
					try {
						System.out.print("BOOK_ID: ");
						int book=sc.nextInt();
						String sr="select * from library where BOOK_ID="+book;
						set=st.executeQuery(sr.trim());
						set.next();
						
						System.out.println("BOOK ID    BOOK NAME      BOOK AUTHOR      BOOK PUBLISHER      BOOK AVAILABLE   BOOK ISSUED        BOOK PRICE");
						System.out.print(set.getInt(1)+"          ");
						System.out.print(set.getString(2)+"         ");
						System.out.print(set.getString(3)+"          ");
						System.out.print(set.getString(4)+"              ");
						System.out.print(set.getInt(5)+"                ");
						System.out.print(set.getInt(6)+"                ");
						System.out.println(set.getFloat(7));
						if(set.getInt(5)==0)
							System.out.println("Book Unavailable");
						else
							System.out.println("Book Available");
						}
					catch(Exception c)
					{
					System.err.println("BOOK NOT FOUND");
					}
				}
				else if(opt==2)
				{
					try {
						System.out.print("BOOK_PUBLISHER: ");
						String init=sc.next();
						String book=sc.nextLine();
						String sr="select * from library where BOOK_PUBLISHER='"+init+book+"'";
						set=st.executeQuery(sr.trim());
						set.next();
						
						System.out.println("BOOK ID    BOOK NAME    BOOK AUTHOR    BOOK PUBLISHER    BOOK AVAILABLE    BOOK ISSUED    BOOK PRICE");
						System.out.print(set.getInt(1)+"          ");
						System.out.print(set.getString(2)+"         ");
						System.out.print(set.getString(3)+"          ");
						System.out.print(set.getString(4)+"              ");
						System.out.print(set.getInt(5)+"                ");
						System.out.print(set.getInt(6)+"                ");
						System.out.println(set.getFloat(7));
						if(set.getInt(5)==0)
							System.out.println("Book Unavailable");
						else
							System.out.println("Book Available");
						}
					catch(Exception d)
						{
							System.err.println("BOOK NOT FOUND");
						}
				}
				else
					System.out.println("INVALID OPTION");
			}
			//////////////////////////////status
			else if(option==6)
			{
				try {
					File f=new File("Administrator.txt");
					FileWriter fw=new FileWriter(f);
					BufferedWriter bw=new BufferedWriter(fw);
					
					System.out.print("BOOK_ID: ");
					int book=sc.nextInt();
					String sr="select * from library where BOOK_ID="+book;
					set=st.executeQuery(sr.trim());
					set.next();
		
					bw.write("**********************************BOOK STATUS********************************\n");
					bw.write("BOOK ID     BOOK NAME        BOOK AUTHOR       BOOK PUBLISHER      BOOK AVAILABLE    BOOK ISSUED    BOOK PRICE   BOOK ISSUED BY\n");
					bw.write(" "+Integer.toString(set.getInt(1))+"          ");
					bw.write(set.getString(2)+"             ");
					bw.write(set.getString(3)+"            ");
					bw.write(set.getString(4)+"            ");
					bw.write(Integer.toString(set.getInt(5))+"             ");
					bw.write(Integer.toString(set.getInt(6))+"             ");
					bw.write(Float.toString(set.getFloat(7))+"              ");
					
					String tmp="select * from data where ID="+book;
					set=st.executeQuery(tmp.trim());
					while(set.next())
					{
						bw.write("\n                                                                                                                   "+Integer.toString(set.getInt(2)));
					}
					
					System.out.println("Path of status file: "+f.getAbsolutePath());
					bw.close();
					fw.close();
					}
					catch(Exception c)
					{
						System.err.println("BOOK NOT FOUND");
					}
			}
			else
				System.out.println("Invalid option");
			System.out.println("1.ADD\n2.MODIFY\n3.DISPLAY\n4.DELETE\n5.SEARCH\n6.STATUS\n7.EXIT");
			System.out.println("Choose the operation you want to perform");
			option=sc.nextInt();
		}
		System.exit(0);
		st.close();
		con.close();
		}
		catch(Exception a)
		{
			System.err.println(a);
		}
	}
	/////////////////////////////////////////public/////////////////////////////////////////////////////
	public static void publicuser(String uss)
	{
		System.out.println("-------------------------------Hello-------------------------------");
		System.out.println("1.ISSUE BOOK\n2.SEARCH\n3.RETURN BOOK\n4.STATUS\n5.EXIT");
		System.out.println("Choose the operation you want to perform");
		int option=sc.nextInt();
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pss);
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet set;
			while(option!=5)
			{
			////////////////////////////////issue
				if(option==1)
				{
					try {
						int no=Integer.parseInt(uss);
						System.out.print("Enter Book_ID: ");
						int id=sc.nextInt();
						String sr="select * from library where BOOK_ID="+id;
						set=st.executeQuery(sr.trim());
						set.next();
						int avail=set.getInt(5);
						int issu=set.getInt(6);
						if(avail==0)
						{
							System.out.println("BOOK UNAVAILABLE");
						}
						else
						{
							String up="select BOOK_ID,BOOK_TITLE,BOOK_AUTH,BOOK_PUBLISHER,BOOK_AVAIL,BOOK_ISSUED,BOOK_PRICE from library where BOOK_ID="+id;
							set=st.executeQuery(up.trim());
							set.absolute(1);
							set.updateInt(5,avail-1);
							set.updateInt(6,issu+1);
							set.updateRow();
							System.out.println("Book Issued Successfully\nCollect From Counter");
							set=st.executeQuery("insert into data values("+id+","+no+")");
						}
					}
					catch(Exception a)
					{
						System.err.println("BOOK NOT FOUND");
					}
				}
			///////////////////////////////search
				else if(option==2)
				{
						System.out.println("Enter the 1.Book Id or 2.Publisher Name to search");
						int opt=sc.nextInt();
						if(opt==1)
						{
							try {
								System.out.print("BOOK_ID: ");
								int book=sc.nextInt();
								String sr="select * from library where BOOK_ID="+book;
								set=st.executeQuery(sr.trim());
								set.next();
								if(set.getInt(5)==0)
									System.out.println("Book Unavailable");
								else
									System.out.println("Book Available");
								}
							catch(Exception c)
							{
							System.err.println("BOOK NOT FOUND");
							}
						}
						else if(opt==2)
						{
							try {
								System.out.print("BOOK_PUBLISHER: ");
								String init=sc.next();
								String book=sc.nextLine();
								String sr="select * from library where BOOK_PUBLISHER='"+init+book+"'";
								set=st.executeQuery(sr.trim());
								set.next();
								if(set.getInt(5)==0)
									System.out.println("Book Unavailable");
								else
									System.out.println("Book Available");
								}
							catch(Exception d)
								{
									System.err.println("BOOK NOT FOUND");
								}
						}
						else
							System.out.println("INVALID OPTION");
				}
			//////////////////////////////return
				else if(option==3)
				{
					try {
							int no=Integer.parseInt(uss);
							System.out.print("Enter Book_ID: ");
							int id=sc.nextInt();
							String dwn="select ID,IDENTIFICATION from data where ID="+id+" AND IDENTIFICATION="+uss;
							set=st.executeQuery(dwn.trim());
							set.next();
								String up="select BOOK_ID,BOOK_TITLE,BOOK_AUTH,BOOK_PUBLISHER,BOOK_AVAIL,BOOK_ISSUED,BOOK_PRICE from library where BOOK_ID="+id;
								set=st.executeQuery(up.trim());
								
								set.next();
								int avail=set.getInt(5);
								int issu=set.getInt(6);
								set.absolute(1);
								set.updateInt(5,avail+1);
								set.updateInt(6,issu-1);
								set.updateRow();
								System.out.println("Book Returned Successfully\nSubmit at Counter");
							
								set=st.executeQuery("delete from data where ID="+id+"AND IDENTIFICATION="+uss);
						}
						catch(Exception a)
						{
							System.err.println("Book Not Found");
						}
				}
				/////////////////////////////status
				else if(option==4)
				{
					try {
						File f=new File("Public.txt");
						FileWriter fw=new FileWriter(f);
						BufferedWriter bw=new BufferedWriter(fw);
						
						System.out.print("BOOK_ID: ");
						int book=sc.nextInt();
						String sr="select * from library where BOOK_ID="+book;
						set=st.executeQuery(sr.trim());
						set.next();
			
						bw.write("**********************************BOOK STATUS********************************\n");
						bw.write("BOOK ID     BOOK NAME        BOOK AUTHOR       BOOK PUBLISHER      BOOK AVAILABLE      BOOK PRICE\n");
						bw.write(" "+Integer.toString(set.getInt(1))+"          ");
						bw.write(set.getString(2)+"            ");
						bw.write(set.getString(3)+"           ");
						bw.write(set.getString(4)+"              ");
						bw.write(Integer.toString(set.getInt(5))+"                ");
						bw.write(Float.toString(set.getFloat(7)));
						System.out.println("Path of status file: "+f.getAbsolutePath());
						bw.close();
						fw.close();
						}
						catch(Exception c)
						{
							System.err.println("BOOK NOT FOUND");
						}
				}
				else
					System.out.println("INVALID OPTION");
				
				System.out.println("-------------------------------Hello-------------------------------");
				System.out.println("1.ISSUE BOOK\n2.SEARCH\n3.RETURN BOOK\n4.STATUS\n5.EXIT");
				System.out.println("Choose the operation you want to perform");
				option=sc.nextInt();
			}
			System.exit(0);
			st.close();
			con.close();
		}
		catch(Exception b)
		{
			System.err.println(b);
		}
	}
}
