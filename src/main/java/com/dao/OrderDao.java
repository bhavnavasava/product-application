package com.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.CartProductBean;
import com.bean.OrderBean;
import com.bean.OrderDetailBean;
import com.util.DbConnection;

public class OrderDao {

	public void PlaceOrder(int userId) {
		CartDao cartDao=new CartDao();
		ArrayList<CartProductBean> carts=cartDao.getCartItems(userId);
		int total = 0;
		for(CartProductBean cartProductBean: carts) {
			total=total+cartProductBean.getPrice();			
		}
		
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("insert into order_table (userid,ammount,orderstatus,paymentmode,paymentstatus) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, total);
			pstmt.setString(3, "placed");
			pstmt.setString(4, "COD");
			pstmt.setString(5, "pending");
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();

			rs.next();
			int orderId = rs.getInt("orderid");
			System.out.println("orderid ==> " + orderId);
			
			OrderDatailsDao orderDetailsDao=new OrderDatailsDao();
			// orderdetail -> 7 10 [11 22 33 44 55 ]
			for (CartProductBean cartProductBean : carts) {
				OrderDetailBean orderDetail = new OrderDetailBean();
				orderDetail.setpId(cartProductBean.getpId());
				orderDetail.setPrice(cartProductBean.getPrice());
				orderDetail.setOrderId(orderId);
				orderDetailsDao.addOrderDetails(orderDetail);
				
				cartDao.emptyCart(carts.get(0).getUserId());

			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
		public ArrayList<OrderBean> getMyOrders(int userId) {
			ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
			try {
				Connection con = DbConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement("select * from order_table where userid = ? ");
				pstmt.setInt(1, userId);
				ResultSet rs = pstmt.executeQuery();

				while(rs.next()) {
					OrderBean order = new OrderBean();
					
					order.setAmmount(rs.getInt("ammount"));
					order.setOrderId(rs.getInt("orderid"));
					order.setOrderStatus(rs.getString("orderstatus"));
					order.setPaymentMode(rs.getString("paymentmode"));
					order.setPaymentStatus(rs.getString("paymentstatus"));
					order.setUserId(rs.getInt("userid"));
					orders.add(order);
				}
								
			} catch (Exception e) {
				System.out.println("went wrong in orderDao()::getmyorder()");
				e.printStackTrace();
			}
			return orders;
		}

}
