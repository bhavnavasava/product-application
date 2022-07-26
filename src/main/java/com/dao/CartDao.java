package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.CartBean;
import com.bean.CartProductBean;
import com.util.DbConnection;

public class CartDao {

	public void addToCart(CartBean cart) {
		
		try {
			Connection con =DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("insert into cart(userid,pid) values(?,?)");
			pstmt.setInt(1, cart.getUserId());
			pstmt.setInt(2, cart.getpId());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public ArrayList<CartProductBean> getCartItems(int userId) {
		ArrayList<CartProductBean> carts=new ArrayList<CartProductBean>();
		
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("select p.pname,p.price,c.cartid, p.pid ,c.userid from products p,cart c where c.userid=? and c.pid=p.pid ");
			pstmt.setInt(1, userId);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				
				CartProductBean cart=new CartProductBean();
				cart.setCartId(rs.getInt("cartid"));
				cart.setpId(rs.getInt("pid"));
				cart.setUserId(rs.getInt("userid"));
				cart.setpName(rs.getString("pname"));
				cart.setPrice(rs.getInt("price"));
				carts.add(cart);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return carts;
	}


	
	
	public boolean deleteCart(int cartid) {
		boolean flag=false;
		try(
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("delete from cart where cartid=?");){
			pstmt.setInt(1, cartid);
			int deleteRows=pstmt.executeUpdate();
			
			if(deleteRows==1) {
				flag=true;
			}
					
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		return flag;
		}


	public void emptyCart(int userId) {
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from cart where userid = ? ");
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("SMW CartDao::emptyCart()");
			e.printStackTrace();
		}
	}
}
