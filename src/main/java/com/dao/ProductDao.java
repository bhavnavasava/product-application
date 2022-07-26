package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.ProductBean;
import com.util.DbConnection;

public class ProductDao {
	public ArrayList<ProductBean> getAllProducts(){
		
		ArrayList<ProductBean> products=new ArrayList<>();
		
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("select * from products");
			
			ResultSet rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ProductBean  p=new ProductBean();
				p.setpId(rs.getInt("pid"));
				p.setpName(rs.getString("pname"));
				p.setPrice(rs.getInt("price"));
				p.setQuantity(rs.getInt("quantity"));
				p.setImgUrl(rs.getString("imgurl"));
				
				products.add(p);
			}
			
		} catch (Exception e) {
			System.out.println("SMW in ProductDao::getAllProducts()");
			e.printStackTrace();
		}				
		return products;
	}
}
