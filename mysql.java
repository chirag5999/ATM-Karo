package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {
	
	final String user="root";
	final String pass="634852";
	final String url="jdbc:mysql://localhost:3306/atm";
	
	static int uid;
	
	public boolean check(String id, String pin)
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			ResultSet rs=st.executeQuery("select * from user_info where user_id="+id+" and pin="+pin);
			if(rs.next())
			{
				return true;
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addData(String uid, String name, String dob, String mob, int pin)
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			
			String s="insert into user_info values("+uid+","+pin+",'"+name+"','"+dob+"','"+mob+"',0)";
			st.executeUpdate(s);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getBalance(String uid) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			ResultSet rs=st.executeQuery("select balance from user_info where user_id="+uid);
			rs.next();
			return rs.getString(1);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public boolean checkBalance(int amount,String uid) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			
			ResultSet rs=st.executeQuery("select balance from user_info where user_id="+uid);
			rs.next();
			int balance=rs.getInt(1);
			if(amount<=balance)
			{
				st.executeUpdate("update user_info set balance="+(balance-amount)+" where user_id="+uid);
				return true;
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateBalance(int amount, String uid) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			
			ResultSet rs=st.executeQuery("select balance from user_info where user_id="+uid);
			rs.next();
			int balance=rs.getInt(1);
			st.executeUpdate("update user_info set balance="+(balance+amount)+" where user_id="+uid);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkPin(String uid, String expin) {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			
			ResultSet rs=st.executeQuery("select pin from user_info where user_id="+uid);
			rs.next();
			String pin=rs.getString(1);
			if(pin.equals(expin))
			{
				return true;
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void changePin(String uid, String newpin) {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			
			st.executeUpdate("update user_info set pin="+newpin+" where user_id="+uid);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int generateUid() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,user,pass);
			Statement st=(Statement)con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from user_info");
			rs.next();
			setUid(1001+rs.getInt(1));
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return getUid();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int userid) {
		uid=userid;
	}
	
}
