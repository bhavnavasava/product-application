package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDao;
@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet{
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cartId=Integer.parseInt(request.getParameter("cartid"));
		CartDao cartDao=new CartDao();
		
		if (cartDao.deleteCart(cartId)) {
			request.setAttribute("message", "deleted succecfully");
			
			
		} else {
			request.setAttribute("message", "some error occurs");
		}
		
		request.getRequestDispatcher("ViewCartController").forward(request, response);	
	}

}
