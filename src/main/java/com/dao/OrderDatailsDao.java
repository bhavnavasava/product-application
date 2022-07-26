package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.OrderDetailBean;
import com.bean.OrderDetailProductBean;
import com.util.DbConnection;

public class OrderDatailsDao {
	public void addOrderDetails(OrderDetailBean orderDetailBean) {
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("insert into orderdetails (orderid,pid,price) values (?,?,?)");
			pstmt.setInt(1, orderDetailBean.getOrderId());
			pstmt.setInt(2, orderDetailBean.getpId());
			pstmt.setInt(3, orderDetailBean.getPrice());
			System.out.println("");

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("SMW OrderDetailDao :: addOrderDetail()");
			e.printStackTrace();
		}
	}

	public ArrayList<OrderDetailProductBean> getOrderDetails(int orderId) {
		ArrayList<OrderDetailProductBean> details = new ArrayList<OrderDetailProductBean>();
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select p.imgurl,p.pname,od.orderdetailid,od.orderid,od.price,p.pid from orderdetails od , products p where p.pid = od.pid and od.orderid = ? ");
			pstmt.setInt(1, orderId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDetailProductBean bean = new OrderDetailProductBean();
				bean.setImgUrl(rs.getString("imgurl"));
				bean.setpName(rs.getString("pname"));
				bean.setOrderDatailId(rs.getInt("orderdetailid"));
				bean.setPrice(rs.getInt("price"));
				bean.setpId(rs.getInt("pid"));

				details.add(bean);
			}
		} catch (Exception e) {
			System.out.println("SMW OrderDetailDao :: getOrderDetails()");
			e.printStackTrace();
		}
		return details;
	}
}

//immutable
