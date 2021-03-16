package com.sportaccessories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RegisterModel {
	
	public int register(String c_id, String name,String email,String pword,String dno,String street, String city, String state, String zip){
		PreparedStatement stmt=null;
			int x=0;
			Connection con=null;
			try{
				con=DBUtil.getMySqlConnection();	
				stmt=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");  
				stmt.setString(1, c_id);
				stmt.setString(2, name);
				stmt.setString(3, email);
				stmt.setString(4, pword);
				stmt.setString(5, dno);
				stmt.setString(6, street);
				stmt.setString(7, city);
				stmt.setString(8, state);
				stmt.setString(9, zip);
				x=stmt.executeUpdate();
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					DBUtil.cleanup(con,stmt);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return x;
		}

	public String login(String email){
		
		Connection con=null;
		PreparedStatement ps=null;
		String pword=null;
		try{
			con=DBUtil.getMySqlConnection();
			ps=con.prepareStatement("select Password from customer where Email_ID=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				 pword=rs.getString(1);			
			}
			rs.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return pword;
	}
	public ArrayList<String> search(String pname){
		
		Connection con=null;
		PreparedStatement ps=null;
		
		ArrayList<String> al =new ArrayList();
		try{
			con=DBUtil.getMySqlConnection();
			ps=con.prepareStatement("select * from item where I_Name= ? ");
			
			ps.setString(1,pname);
			ResultSet rs1=ps.executeQuery();
			while(rs1.next()){
			    
				al.add(rs1.getString(1));
				al.add(rs1.getString(2));
				al.add(rs1.getString(3));
				al.add(rs1.getString(4));
				
			}
			
			rs1.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}

}
