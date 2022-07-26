package com.dao;

import java.sql.Connection;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.UserBean;
import com.util.DbConnection;

public class UserDao {

	
	public void insertUser(UserBean userBean) {

		try {
			Connection con = DbConnection.getConnection();

			PreparedStatement pstmt = con.prepareStatement(
					"insert into users (firstname,lastname,email,password,gender,usertype) values (?,?,?,?,?,?)");

			pstmt.setString(1, userBean.getFirstName());
			pstmt.setString(2, userBean.getLastName());
			pstmt.setString(3, userBean.getEmail());
			pstmt.setString(4, userBean.getPassword());
			pstmt.setString(5, userBean.getGender());
			pstmt.setString(6, userBean.getUserType());

			// insert update delete
			int records = pstmt.executeUpdate();

			System.out.println(records + " inserted...........");
		} catch (Exception e) {
			System.out.println("SMW in insertUser() ");
			e.printStackTrace();
		}

	}

	public ArrayList<UserBean> getAllUsers() {
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from users");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next() == true) { 
				int userId = rs.getInt("userid");// db column name
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				String userType = rs.getString("userType");

				UserBean user = new UserBean();
				user.setUserId(userId);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setPassword(password);
				user.setGender(gender);
				user.setUserType(userType);
				users.add(user);
			}
			
		} catch (Exception e) {
			System.out.println("SMW in UserDAO::getAllUsers()");
			e.printStackTrace();
		}

		return users;

	}

	public UserBean login(String email, String password) {
		UserBean user = null;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from users where email = ? and password = ? ");
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			// read select
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new UserBean();
				user.setUserId(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setUserType(rs.getString("usertype"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			
			}

		} catch (Exception e) {
			System.out.println("SMW in login Dao ");
			e.printStackTrace();
		}
		return user;
	}

	public boolean deleteUser(int userid) {
		boolean flag=false;
		try(
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("delete from users where userid=?");){
			pstmt.setInt(1, userid);
			int deleteRows=pstmt.executeUpdate();
			
			if(deleteRows==1) {
				flag=true;
			}
					
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return flag;
		}

	public UserBean getUserById(int userId) {
		UserBean user=null;
		try(Connection con=DbConnection.getConnection();
				PreparedStatement pstmt=con.prepareStatement("select * from users where userid=?");
				
				) {
			pstmt.setInt(1, userId);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				user=new UserBean();
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setGender(rs.getString("gender"));
				user.setLastName(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				user.setUserId(userId);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(UserBean user) {
		boolean flag = false;
		try(Connection con = DbConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("update users set firstname = ?, lastname=?,gender=?,password=? where userid=?");
			)
		{
			psmt.setString(1, user.getFirstName());
			psmt.setString(2, user.getLastName());
			psmt.setString(3, user.getGender());
			psmt.setString(4, user.getPassword());
			psmt.setInt(5, user.getUserId());
			int updateCount = psmt.executeUpdate();
			if(updateCount == 1) {
				flag = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
